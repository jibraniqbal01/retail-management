package com.retail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.retail.model.Bill;
import com.retail.model.Customer;
import com.retail.model.CustomerType;
import com.retail.process.BillingProcess;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @MockBean
    private BillingProcess billingProcess;

    @Test
    void generateBillEmployee_test() {
        Mockito.when(billingProcess.generateBill(Mockito.anyFloat(), Mockito.any())).thenReturn(getBill(362.59f));
        Bill bill =  storeService.generateBill(getEmployeeCustomer());
        assertEquals(362.59f, bill.getPayableAmount());
    }

    @Test
    void generateBillAffliated_test() {
        Mockito.when(billingProcess.generateBill(Mockito.anyFloat(), Mockito.any())).thenReturn(getBill(367.98f));
        Bill bill =  storeService.generateBill(getAffiliatedCustomer());
        assertEquals(367.98f, bill.getPayableAmount());
    }

    @Test
    void generateBillValuedCustomer_test() {
        Mockito.when(billingProcess.generateBill(Mockito.anyFloat(), Mockito.any())).thenReturn(getBill(369.32f));
        Bill bill =  storeService.generateBill(getValuedCustomer());
        assertEquals(369.32f, bill.getPayableAmount());
    }

    @Test
    void generateBillNormal_test() {
        Mockito.when(billingProcess.generateBill(Mockito.anyFloat(), Mockito.any())).thenReturn(getBill(370.67f));
        Bill bill =  storeService.generateBill(getNormalCustomer());
        assertEquals(370.67f, bill.getPayableAmount());
    }

    private Bill getBill(float payableAmount) {
        Bill bill = new Bill();
        bill.setPayableAmount(payableAmount);
        return bill;
    }

    private Customer getEmployeeCustomer() {
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.EMPLOYEE);
        return customer;
    }

    private Customer getAffiliatedCustomer() {
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.AFFILIATED);
        return customer;
    }


    private Customer getValuedCustomer() {
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.VALUED_CUSTOMER);
        return customer;
    }

    private Customer getNormalCustomer() {
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.NORMAL);
        return customer;
    }



}
