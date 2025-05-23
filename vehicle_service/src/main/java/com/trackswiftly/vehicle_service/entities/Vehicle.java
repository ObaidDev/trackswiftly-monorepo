package com.trackswiftly.vehicle_service.entities;



import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.trackswiftly.vehicle_service.enums.VehicleTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        name = "vehicles" ,
        indexes = {
            @Index(columnList = "tenantId" , name = "vehicle_tenantId_idx")
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tenantId" , "vin"})
        }
)
@Data  @EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicle extends AbstractBaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq" , sequenceName = "vehicle_id_seq"  , allocationSize = 50)
    private Long id ;


    @Column(nullable = false)
    private String vin ;


    private String licensePlate ;


    @Column(nullable = false)
    private int mileage ;


    private Instant purchaseDate ;


    // @ManyToOne
    // @JoinColumn(nullable = false ,  name = "vehicle_type_id")
    // private VehicleType vehicleType ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleTypeEnum vehicleType;

    @ManyToOne
    @JoinColumn(nullable = false , name = "model_id")
    private Model model ;

    @ManyToOne
    @JoinColumn(nullable = false , name = "vehicle_group_id")
    private Group vehicleGroup ;


    @ManyToOne
    @JoinColumn(nullable = true , name = "home_location_id")
    private HomeLocation homeLocation ;



    @CreationTimestamp
    private Instant createdAt ;

    @UpdateTimestamp
    private Instant updatedAt ;


    
}
