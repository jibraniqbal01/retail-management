package com.retail.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private float groceriesAmount;
    private float nonGroceriesAmount;
    private float payableAmount;
    private int totalItems;
}
