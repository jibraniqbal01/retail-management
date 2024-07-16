package com.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.retail.model.Item;
import com.retail.model.ItemType;
import com.retail.process.BillingProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;



@SpringBootTest
class StoreBillingProcessTest {

    @Autowired
    private BillingProcess billingProcess;
    @Test
    void generateBill_test() {
        assertEquals(268.05d, billingProcess.generateBill(30.0f, getItemList()).getPayableAmount());
        assertEquals(5, billingProcess.generateBill(30.0f, getItemList()).getTotalItems());

    }

    private List<Item> getItemList(){
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Chicken", 10.99f, ItemType.GROCERY));
        itemList.add(new Item("Butter", 15.95f, ItemType.GROCERY));
        itemList.add(new Item("Shoes", 250.0f, ItemType.NON_GROCERY));
        itemList.add(new Item("Shirt", 88.23f, ItemType.NON_GROCERY));
        itemList.add(new Item("Belt", 20.50f, ItemType.NON_GROCERY));
        return itemList;
    }

}
