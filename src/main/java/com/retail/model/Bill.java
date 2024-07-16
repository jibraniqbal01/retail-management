package com.retail.model;

import lombok.*;

@Getter
@Setter
public class Bill {

    private Double groceriesAmount;
    private Double nonGroceriesAmount;
    private Double payableAmount;
    private int totalItems;
}
