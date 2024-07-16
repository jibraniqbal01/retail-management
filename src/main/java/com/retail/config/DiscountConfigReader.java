package com.retail.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "discount")
@Configuration
public class DiscountConfigReader {
    private int amountCutOff;
    private int discountedAmount;
    private int employeeDiscount;
    private int affiliatedDiscount;
    private int valuedCustomerDiscount;
}
