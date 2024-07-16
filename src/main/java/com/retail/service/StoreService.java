package com.retail.service;

import com.retail.model.Bill;
import com.retail.model.Customer;

public interface StoreService {
     Bill generateBill(Customer customer);

}
