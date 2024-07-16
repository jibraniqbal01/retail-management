package com.retail.process.discount;

public class AmountBasedDiscountStrategy implements DiscountStrategy {
	private final float amountCutOff;
	
	public AmountBasedDiscountStrategy(float amountCutOff) {
		this.amountCutOff = amountCutOff;
	}
	
	@Override
	public float getAmount(float totalAmount, float discountedAmount) {
		return totalAmount - ((int) (totalAmount / amountCutOff) * discountedAmount);
	}


}
