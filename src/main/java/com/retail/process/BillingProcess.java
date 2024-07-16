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
import java.util.Map;
import java.util.stream.Collectors;

import static com.retail.model.ItemType.GROCERY;
import static com.retail.model.ItemType.NON_GROCERY;

@Slf4j
@Component
public class BillingProcess {

	@Autowired
	private DiscountConfigReader reader;
	
	private Bill bill;

	
	public Bill generateBill(float discountPercent, List<Item> itemList) {
		bill = new Bill();
		double nonGroceriesAmountAfterDiscount;
		double totalAmount;
		double netPayableAmount;
		calculateBill(itemList);
		nonGroceriesAmountAfterDiscount = getNonGroceriesAmtAfterDiscount(discountPercent);
		totalAmount = nonGroceriesAmountAfterDiscount + bill.getGroceriesAmount();
		netPayableAmount = getNetPayableAmount(totalAmount);
		bill.setPayableAmount(Utility.format(netPayableAmount));
		bill.setTotalItems(itemList.size());
        log.info("Final Bill::{}", bill.getPayableAmount());
		return bill;
	}

	private double getNonGroceriesAmtAfterDiscount(double discountPercent) {
		DiscountStrategy discountStrategy = DiscountFactory.getPercentageBasedInstance();
		return discountStrategy.getAmount(bill.getNonGroceriesAmount(), discountPercent);
	}

	
	private double getNetPayableAmount(double totalAmount) {
		DiscountStrategy discountStrategy = DiscountFactory.getAmountBasedInstance(reader.getAmountCutOff());
		return discountStrategy.getAmount(totalAmount, reader.getDiscountedAmount());
	}
	
	/**
	 *
	 * @param itemList
	 * This method will calculate the total amount for Groceries and non Groceries items.
	 */
	
	private void calculateBill(List<Item> itemList) {

		Map<ItemType, Double> totalAmounts = itemList.stream()
				.collect(Collectors.groupingBy(Item::getItemType,
						Collectors.summingDouble(Item::getAmount)));

		bill.setGroceriesAmount(Utility.format(totalAmounts.get(GROCERY)));
		bill.setNonGroceriesAmount(Utility.format(totalAmounts.get(NON_GROCERY)));
	}
	
}
