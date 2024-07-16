package com.retail.process.factory;


import com.retail.process.discount.AmountBasedDiscountStrategy;
import com.retail.process.discount.DiscountStrategy;
import com.retail.process.discount.PercentageBasedDiscountStrategy;

public class DiscountFactory {
	private static PercentageBasedDiscountStrategy percentageBasedInstance;
	private static AmountBasedDiscountStrategy amountBasedInstance = null;
	
	private DiscountFactory() {}
	
	public static DiscountStrategy getPercentageBasedInstance() {
		synchronized (DiscountFactory.class) {
			if(percentageBasedInstance == null) {
				percentageBasedInstance = new PercentageBasedDiscountStrategy();
			}
		}
		return percentageBasedInstance;
	}
	

	
	public static DiscountStrategy getAmountBasedInstance(float amountCutOff) {
		synchronized (DiscountFactory.class) {
			if(amountBasedInstance == null) {
				amountBasedInstance = new AmountBasedDiscountStrategy(amountCutOff);
			}			
		}
		return amountBasedInstance;
	}

}
