package com.retail.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Customer {

    private int id;
    private String name;
    private LocalDate dateOfJoining;
    private Boolean isEmployee;
    private Boolean isAffiliated;
}
