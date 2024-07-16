package com.retail.service;

import com.retail.model.ApiRequest;
import com.retail.model.Bill;

public interface StoreService {
     Bill generateBill(ApiRequest request);

}
