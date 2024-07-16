package com.retail.process.discount;

public class PercentageBasedDiscountStrategy implements DiscountStrategy {
	
	@Override
	public double getAmount(double totalAmount, double discountPercent) {
		  return  totalAmount - ((discountPercent * totalAmount)/100);
	}

}
