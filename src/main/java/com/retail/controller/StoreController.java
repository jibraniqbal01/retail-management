package com.retail.controller;

import com.retail.model.Bill;
import com.retail.model.Customer;
import com.retail.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping("/store/bill")
    public Bill generateBill(@RequestBody Customer customer) {

        log.info("Request received:{}", customer.getCustomerType());
        return service.generateBill(customer);
    }
}
