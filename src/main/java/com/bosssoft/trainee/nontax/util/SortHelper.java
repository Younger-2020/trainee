package com.bosssoft.trainee.nontax.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class SortHelper {

    private SortHelper(){}

    public static List<OrderItem> getOrderItem(String sort) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        boolean asc = sort.charAt(0) == '+';
        String colName = sort.substring(1);
        OrderItem orderItem = new OrderItem(colName, asc);
        orderItems.add(orderItem);
        return orderItems;
    }
}
