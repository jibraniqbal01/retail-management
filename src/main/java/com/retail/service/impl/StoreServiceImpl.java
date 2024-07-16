package com.retail.service.impl;


import com.retail.config.DiscountConfigReader;
import com.retail.model.Bill;
import com.retail.model.Customer;
import com.retail.process.BillingProcess;
import com.retail.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private DiscountConfigReader reader;

    @Autowired
    private BillingProcess billingProcess;

    @Override
    public Bill generateBill(Customer customer) {
        log.info("Calculating bill..");
        return switch (customer.getCustomerType()) {
            case EMPLOYEE -> billingProcess.generateBill(reader.getEmployeeDiscount(), customer.getItemList());
            case AFFILIATED -> billingProcess.generateBill(reader.getAffiliatedDiscount(), customer.getItemList());
            case VALUED_CUSTOMER ->
                    billingProcess.generateBill(reader.getValuedCustomerDiscount(), customer.getItemList());
            default -> billingProcess.generateBill(0.0f, customer.getItemList());
        };
    }

}
