package com.trackswiftly.client_service.web;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trackswiftly.client_service.dtos.GroupRequest;
import com.trackswiftly.client_service.dtos.GroupResponse;
import com.trackswiftly.client_service.dtos.interfaces.CreateValidationGroup;
import com.trackswiftly.client_service.dtos.interfaces.UpdateValidationGroup;
import com.trackswiftly.client_service.services.GroupService;
import com.trackswiftly.utils.dtos.OperationResult;
import com.trackswiftly.utils.dtos.PageDTO;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/groups")
@Slf4j
@Validated
public class GroupController {


    private GroupService groupService ;

    @Autowired
    GroupController (
        GroupService groupService
    ) {
        this.groupService = groupService ;
    }
    


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DISPATCHER')")
    @Validated(CreateValidationGroup.class)
    public List<GroupResponse> createGroups(
        @RequestBody @Valid List<GroupRequest> groupRequests
    ) {
        List<GroupResponse> responses = groupService.createEntities(groupRequests);

        log.debug("groups {}" , responses);
        
        return responses ;
    }


    @DeleteMapping("/{groupIds}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DISPATCHER')")   
    public ResponseEntity<OperationResult> deleteGroups(
        @Parameter(
            description = "Comma-separated list of group IDs to be deleted",
            required = true,
            example = "1,2,3",
            schema = @Schema(type = "string")
        )
        @PathVariable List<Long> groupIds
    ) {
        OperationResult result = groupService.deleteEntities(groupIds);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{groupIds}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DISPATCHER')")
    public ResponseEntity<List<GroupResponse>> findgroups(
        @Parameter(
            description = "Comma-separated list of group IDs to be fetched",
            required = true,
            example = "1,2,3",
            schema = @Schema(type = "string")
        )
        @PathVariable List<Long> groupIds
    ) {
        List<GroupResponse> groups = groupService.findEntities(groupIds);
        return ResponseEntity.ok(groups);
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DISPATCHER')")
    public ResponseEntity<PageDTO<GroupResponse>> getGroupsWithPagination(
        @RequestParam int page,
        @RequestParam int pageSize
    ) {
        PageDTO<GroupResponse> groups = groupService.pageEntities(page, pageSize);

        return ResponseEntity.ok(groups);
    }


    @PutMapping("/{groupIds}")
    @Validated(UpdateValidationGroup.class)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DISPATCHER')")
    public ResponseEntity<OperationResult> updategroupsInBatch(
        @Parameter(
            description = "Comma-separated list of group IDs to be updated",
            required = true,
            example = "1,2,3",
            schema = @Schema(type = "string")
        )
        @PathVariable List<Long> groupIds,
        @Parameter(
            description = "group object containing the fields to update",
            required = true
        )
        @Valid @RequestBody GroupRequest group
    ) {
        OperationResult result = groupService.updateEntities(groupIds, group);
        return ResponseEntity.ok(result);
    }




    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_DISPATCHER')")
    public ResponseEntity<List<GroupResponse>> search(
        @RequestParam String query
    ) {
        String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
        return ResponseEntity.ok(
            groupService.search(decodedQuery)
        );
    }
}