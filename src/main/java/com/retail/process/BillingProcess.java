package com.retail.process;


import com.retail.config.DiscountConfigReader;
import com.retail.model.Bill;
import com.retail.model.Item;
import com.retail.model.ItemType;
import com.retail.process.discount.DiscountStrategy;
import com.retail.process.factory.DiscountFactory;
import com.retail.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BillingProcess {

	@Autowired
	private DiscountConfigReader reader;
	
	private Bill bill;

	
	public Bill generateBill(float discountPercent, List<Item> itemList) {
		bill = new Bill();
		float nonGroceriesAmountAfterDiscount;
		float totalAmount;
		float netPayableAmount;
		calcualteBillAmount(itemList);
		nonGroceriesAmountAfterDiscount = getNonGroceriesAmtAfterDiscount(discountPercent);
		totalAmount = nonGroceriesAmountAfterDiscount + bill.getGroceriesAmount();
		netPayableAmount = getNetPayableAmount(totalAmount);
		bill.setPayableAmount(Utility.format(netPayableAmount));
		bill.setTotalItems(itemList.size());
        log.info("Final Bill::{}", bill.getPayableAmount());
		return bill;
	}

	private float getNonGroceriesAmtAfterDiscount(float discountPercent) {
		DiscountStrategy discountStrategy = DiscountFactory.getPercentageBasedInstance();
		return discountStrategy.getAmount(bill.getNonGroceriesAmount(), discountPercent);
	}

	
	private float getNetPayableAmount(float totalAmount) {
		DiscountStrategy discountStrategy = DiscountFactory.getAmountBasedInstance(reader.getAmountCutOff());
		return discountStrategy.getAmount(totalAmount, reader.getDiscountedAmount());
	}
	
	/**
	 *
	 * @param itemList
	 * This method will calculate the total amount for Groceries and non Groceries items.
	 */
	
	private void calcualteBillAmount(List<Item> itemList) {
		float nonGroceriesTotalAmount = 0.0f;
		float groceriesTotalAmount = 0.0f;
		for(Item item: itemList) {
			if(item.getItemType() == ItemType.NON_GROCERY) {
				nonGroceriesTotalAmount += item.getAmount();	
			}else {
				groceriesTotalAmount += item.getAmount();
			}
		}
		bill.setGroceriesAmount(Utility.format(groceriesTotalAmount));
		bill.setNonGroceriesAmount(Utility.format(nonGroceriesTotalAmount));
	}
	
}
