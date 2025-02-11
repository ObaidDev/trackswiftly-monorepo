package com.trackswiftly.vehicle_service.entities;


import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(
        name = "vehicle_types" ,
        indexes = {
            @Index(columnList = "tenantId" , name = "vehicle_type_tenantId_idx")
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tenantId" , "name"})
        }
)
@Data  @EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @AllArgsConstructor @Builder
public class VehicleType extends AbstractBaseEntity{
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "vehicle_type_seq")
    @SequenceGenerator(name = "vehicle_type_seq" , sequenceName = "vehicle_type_id_seq"  , allocationSize = 1)
    private Long id ;


    @Column(nullable = false)
    private String name ;

    private String description ;


    @OneToMany(mappedBy = "type")
    @JsonBackReference
    private List<Vehicle> vehicles ;


    @CreationTimestamp
    private Instant createdAt ;

    @UpdateTimestamp
    private Instant updatedAt ;
}
