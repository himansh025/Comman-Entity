package com.example.Uber_Entity.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bookings"})
public class Driver extends BaseModel {


    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private Car car;

    @Enumerated(EnumType.STRING)
    private DriverApprovalStatus driverApprovalStatus;

    @OneToOne
    private ExactLocation lastKnownLocation;

    @OneToOne
    private ExactLocation home;

    private String activeCity;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "5.00")
    private Double rating;

    private boolean isActive;

    @Column
    private String phoneNumber;
    //  in !:N default fetch mode is eager
    //  fetch = FetchType.LAZY it is default  immediately we don't need booking details to enable it use the app.prop lazy loading =true
    //  fetch = FetchType.EAGER   fetch eager is used to fetch bookings immediately it use the join in driver booking and passenger table bcz they are associated
    //  1:n relationship mapped by is used bcz the spring will confuse to identify the booking is for the user or the driver and hibernate create not identify and create new table driver_booking
    //here we have to define to whom this booking is passenger or driver
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Booking> bookings = new ArrayList<>();

}
