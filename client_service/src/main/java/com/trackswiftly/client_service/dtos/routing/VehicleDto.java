package com.trackswiftly.client_service.dtos.routing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.trackswiftly.client_service.dtos.validators.ValidLocation;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    @NotNull
    private Integer id;


    @Builder.Default
    private String profile = "auto";

    private String description;


    @ValidLocation
    private List<Double> start;


    @JsonProperty("start_index")
    private List<Integer> startIndex;


    @ValidLocation
    private List<Double> end;

    @JsonProperty("end_index")
    private List<Integer> endIndex;


    private List<Integer> capacity;

    private Costs costs;


    private List<Integer> skills;


    @JsonProperty("time_window")
    private TimeWindow timeWindow;


    private List<Break> breaks;


    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "5.0")
    @Digits(integer = 1, fraction = 2)
    @Builder.Default
    private Double speedFactor = 1.0;



    @JsonProperty("max_tasks")
    private Integer maxTasks;


    @JsonProperty("max_travel_time")
    private Integer maxTravelTime;



    @JsonProperty("max_distance")
    private Integer maxDistance;



    private List<VehicleStep> steps;




















    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Costs {
        @Builder.Default
        private Integer fixed = 0;

        @JsonProperty("per_hour")
        @Builder.Default
        private Integer perHour = 3600;

        @JsonProperty("per_km")
        @Builder.Default
        private Integer perKm = 0;
    }
    


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Break {
        @NotNull
        private Integer id;

        @JsonProperty("time_windows")
        private List<TimeWindow> timeWindows;

        @Builder.Default
        private Integer service = 0;

        private String description;

        @JsonProperty("max_load")
        private List<Integer> maxLoad;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VehicleStep {
        private StepType type;
        private Integer id;
        
        @JsonProperty("service_at")
        private Integer serviceAt;
        
        @JsonProperty("service_after")
        private Integer serviceAfter;
        
        @JsonProperty("service_before")
        private Integer serviceBefore;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TimeWindow {
        private Integer start;
        private Integer end;
    }


    public enum StepType {
        START("start"),
        JOB("job"),
        PICKUP("pickup"),
        DELIVERY("delivery"),
        BREAK("break"),
        END("end");

        private final String value;

        StepType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
