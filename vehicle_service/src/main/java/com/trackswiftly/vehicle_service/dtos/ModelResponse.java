package com.trackswiftly.vehicle_service.dtos;


import java.time.LocalDateTime;
import java.util.Date;

import com.trackswiftly.vehicle_service.enums.Capacity;
import com.trackswiftly.vehicle_service.enums.CapacityType;
import com.trackswiftly.vehicle_service.enums.EngineType;
import com.trackswiftly.vehicle_service.enums.FuelType;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ModelResponse {


    private Long id ;

    private String name ;

    private String make ;

    private Date year ;

    private EngineType engineType ;

    private FuelType fuelType ;

    private String transmission ;

    private CapacityType defaultCapacityType ;

    private Capacity capacity ;

    private LocalDateTime createdAt ;

    private LocalDateTime updatedAt ;
    
}
