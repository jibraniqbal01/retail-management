package com.retail.process.discount;

public interface DiscountStrategy {
	/**
	 * 
	 * @param totalAmount
	 * @param discount
	 * @return
	 * This implementation of this method will apply the discount on total amount and return the amount after deducting the discount
	 * Calculation of discount is based on the implementation
	 */

	double getAmount(double totalAmount, double discount);
}
