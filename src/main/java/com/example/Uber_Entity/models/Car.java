package com.example.Uber_Entity.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseModel{

    @Column(unique = true, nullable = false)
    private String plateNumber;

    @ManyToOne
    private Color color;

    private String brand;

    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @OneToOne
    private Driver driver;
}