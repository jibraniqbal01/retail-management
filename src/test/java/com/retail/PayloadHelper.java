package com.retail;

import com.retail.model.ApiRequest;
import com.retail.model.Item;
import com.retail.model.ItemType;

import java.util.ArrayList;
import java.util.List;

public class PayloadHelper {

    public static ApiRequest getRequest(){
        Item item1 = new Item();
        item1.setAmount(10.99f);
        item1.setItemType(ItemType.GROCERY);
        item1.setName("Chicken");

        Item item2 = new Item();
        item2.setAmount(15.95f);
        item2.setItemType(ItemType.GROCERY);
        item2.setName("Butter");

        Item item3 = new Item();
        item3.setAmount(250.00f);
        item3.setItemType(ItemType.NON_GROCERY);
        item3.setName("Shoes");

        Item item4 = new Item();
        item4.setAmount(88.23f);
        item4.setItemType(ItemType.NON_GROCERY);
        item4.setName("Shirt");

        Item item5 = new Item();
        item5.setAmount(20.50f);
        item5.setItemType(ItemType.NON_GROCERY);
        item5.setName("Belt");

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setItemList(itemList);
        return apiRequest;



    }
}
