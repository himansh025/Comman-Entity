package com.example.Uber_Entity.models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "booking_review")
public class Review extends BaseModel {


    // 1:1 mapping btw the booking and review

    // when our booking table is depends on the review then instead of doing the review save first then use hte obj in the booking saved soring cascade done it manually
    // remove if booking is remove review associated is also removed
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking;

    @Override
    public String toString() {
        return "Review{" +
                "booking=" + booking.getId() +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Column(nullable = false)
    private String content;

    private Double rating;


}
