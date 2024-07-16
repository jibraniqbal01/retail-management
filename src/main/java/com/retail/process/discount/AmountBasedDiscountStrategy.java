package com.retail.process.discount;

public class AmountBasedDiscountStrategy implements DiscountStrategy {
	private final double amountCutOff;
	
	public AmountBasedDiscountStrategy(double amountCutOff) {
		this.amountCutOff = amountCutOff;
	}
	
	@Override
	public double getAmount(double totalAmount, double discountedAmount) {
		return totalAmount - ((int) (totalAmount / amountCutOff) * discountedAmount);
	}


}
