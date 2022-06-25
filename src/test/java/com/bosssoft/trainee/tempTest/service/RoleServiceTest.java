package com.bosssoft.trainee.tempTest.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void test1() {
        Page page = new Page(1,20);
        OrderItem name = new OrderItem("name", true);
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(name);
        page.setOrders(orderItems);
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        String query = null;
        roleQueryWrapper.like(null!=query,"name", query);
        Page page1 = roleService.page(page, roleQueryWrapper);
        List<Role> list = page1.getRecords();
        list.forEach(System.out::println);
    }

}
