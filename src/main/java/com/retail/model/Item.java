package com.retail.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private String name;
	private double amount;
	private ItemType itemType;

}
