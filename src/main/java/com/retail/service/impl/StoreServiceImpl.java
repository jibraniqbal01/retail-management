package com.retail.service.impl;


import com.retail.config.DiscountConfigReader;
import com.retail.exception.CustomerNotFoundException;
import com.retail.model.ApiRequest;
import com.retail.model.Bill;
import com.retail.model.CustomerType;
import com.retail.process.BillingProcess;
import com.retail.model.Customer;
import com.retail.repo.CustomerRepository;
import com.retail.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.retail.model.CustomerType.*;

@Slf4j
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private DiscountConfigReader reader;

    @Autowired
    private BillingProcess billingProcess;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Bill generateBill(ApiRequest request) {
        log.info("StoreServiceImpl.generateBill called");

        return switch (getCustomerType(request)) {
            case  EMPLOYEE-> billingProcess.generateBill(reader.getEmployeeDiscount(), request.getItemList());
            case AFFILIATED -> billingProcess.generateBill(reader.getAffiliatedDiscount(), request.getItemList());
            case VALUED_CUSTOMER ->
                    billingProcess.generateBill(reader.getValuedCustomerDiscount(), request.getItemList());
            default -> billingProcess.generateBill(0.0f, request.getItemList());
        };

    }

    private CustomerType getCustomerType(ApiRequest request) {
        if (request.getCustomer() != null) {
            Customer customer = getCustomer(request.getCustomer().getId());
            if (customer.getIsEmployee()) {
                return EMPLOYEE;
            } else if (customer.getIsAffiliated()) {
                return AFFILIATED;
            } else if(isValued(customer.getDateOfJoining())){
                return VALUED_CUSTOMER;
            }
        }
        return NORMAL;
    }

    private boolean isValued(LocalDate dateOfJoining){
        return dateOfJoining.isBefore(LocalDate.now().minusYears(2));
    }

    private Customer getCustomer(int id){
        return  customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not Found"));
    }
}
