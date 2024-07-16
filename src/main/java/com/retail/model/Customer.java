package com.retail.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer {
    private CustomerType customerType;
    private List<Item> itemList;
}
