package com.trackswiftly.vehicle_service.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO<T> implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private transient List<T> content;          
    private int page;                 
    private int size;
    private long totalElements;
    private int totalPages;
}
