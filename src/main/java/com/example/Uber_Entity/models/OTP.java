package com.example.Uber_Entity.models;


import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OTP extends  BaseModel{

    private  String code;
    private String sendToNumber;

    public static OTP make(String phoneNumber){
        Random random= new Random();
        int code= random.nextInt(9000)+1000;
        return  OTP.builder()
                .code(Integer.toString(code))
                .sendToNumber(phoneNumber)
                .build();
    }
}
