package com.retail.repo;

import com.retail.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepository {

    Map<Integer, Customer> customerMap = new HashMap<>();

    /**
     * Just mocking employee as there is no Database.
     */

    @PostConstruct
    public void init() {
        customerMap.put(1, Customer.builder()
                .id(1)
                .name("Dhoni")
                .dateOfJoining(LocalDate.of(2020, Month.JANUARY, 1))
                .isEmployee(false)
                .isAffiliated(true).build());
        customerMap.put(2, Customer.builder()
                .id(2)
                .name("Pathan")
                .dateOfJoining(LocalDate.of(2024, Month.JANUARY, 1))
                .isEmployee(true)
                .isAffiliated(true).build());
        customerMap.put(3, Customer.builder()
                .id(3)
                .name("Bumrah")
                .dateOfJoining(LocalDate.of(2019, Month.JANUARY, 1))
                .isEmployee(false)
                .isAffiliated(false).build());
        customerMap.put(4, Customer.builder()
                .id(4)
                .name("Shami")
                .dateOfJoining(LocalDate.of(2024, Month.JANUARY, 1))
                .isEmployee(false)
                .isAffiliated(false).build());
    }


    public Optional<Customer> findById(int id){
        return Optional.ofNullable(customerMap.get(id));
    }
}
