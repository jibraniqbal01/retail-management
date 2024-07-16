package com.retail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.when;

import com.retail.exception.CustomerNotFoundException;
import com.retail.model.ApiRequest;
import com.retail.model.Bill;
import com.retail.process.BillingProcess;
import com.retail.model.Customer;
import com.retail.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;


@SpringBootTest
@ActiveProfiles("test")
class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @MockBean
    private BillingProcess billingProcess;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void generateBillEmployee_test() {
        when(billingProcess.generateBill(anyFloat(), any())).thenReturn(getBill(362.59f));
        when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(getEmployeeCustomer()));
        Bill bill =  storeService.generateBill( new ApiRequest(getEmployeeCustomer(), null));
        assertEquals(362.59f, bill.getPayableAmount());
    }

    @Test
    void generateBillAffliated_test() {
        when(billingProcess.generateBill(anyFloat(), any())).thenReturn(getBill(367.98f));
        when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(getAffiliatedCustomer()));
        Bill bill =  storeService.generateBill(new ApiRequest(getAffiliatedCustomer(), null));
        assertEquals(367.98f, bill.getPayableAmount());
    }

    @Test
    void generateBillValuedCustomer_test() {
        when(billingProcess.generateBill(anyFloat(), any())).thenReturn(getBill(369.32f));
        when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(getValuedCustomer()));
        Bill bill =  storeService.generateBill(new ApiRequest(getValuedCustomer(), null));
        assertEquals(369.32f, bill.getPayableAmount());
    }

    @Test
    void generateBillNormal_test() {
        when(billingProcess.generateBill(anyFloat(), any())).thenReturn(getBill(370.67f));
        when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(getNormalCustomer()));
        Bill bill =  storeService.generateBill(new ApiRequest(getNormalCustomer(), null));
        assertEquals(370.67f, bill.getPayableAmount());
    }

    @Test
    void generateBillNormal_throws_exception_test() {
        when(billingProcess.generateBill(anyFloat(), any())).thenReturn(getBill(370.67f));
        when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () ->
                storeService.generateBill(new ApiRequest(getEmployeeCustomer(), null)));

        assertEquals("Customer not Found", exception.getMessage());
    }

    private Bill getBill(double payableAmount) {
        Bill bill = new Bill();
        bill.setPayableAmount(payableAmount);
        return bill;
    }

    private Customer getEmployeeCustomer() {
        return Customer.builder().isEmployee(true).build();
    }

    private Customer getAffiliatedCustomer() {
        return Customer.builder().isEmployee(false)
                .isAffiliated(true).build();

    }


    private Customer getValuedCustomer() {
        return Customer.builder()
                .isEmployee(false)
                .isAffiliated(false)
                .dateOfJoining(LocalDate.of(2011, Month.AUGUST, 1)).build();
    }

    private Customer getNormalCustomer() {
        return null;
    }

}
