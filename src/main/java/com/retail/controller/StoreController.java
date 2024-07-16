package com.retail.controller;

import com.retail.model.ApiRequest;
import com.retail.model.Bill;
import com.retail.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping("/store/bill")
    public Bill generateBill(@RequestBody ApiRequest request) {

        log.info("Request received:");
        return service.generateBill(request);
    }
}
