package com.retail.process.discount;

public class PercentageBasedDiscountStrategy implements DiscountStrategy {
	
	@Override
	public float getAmount(float totalAmount, float discountPercent) {
		  return  totalAmount - ((discountPercent * totalAmount)/100);
	}

}
