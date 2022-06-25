package com.bosssoft.trainee.tempTest.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.trainee.nontax.entity.po.SubSystem;
import com.bosssoft.trainee.nontax.mapper.SubSystemMapper;
import com.bosssoft.trainee.nontax.util.SortHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SubsysMapperTest {
    @Autowired
    private SubSystemMapper subSystemMapper;

    @Test
    public void test1() {
        Page<SubSystem> page = new Page<>(1, 20);
        String sort = "-id";
        List<OrderItem> orderItem = SortHelper.getOrderItem(sort);
        page.setOrders(orderItem);
        Page<SubSystem> subSystemPage = subSystemMapper.selectPage(page,new LambdaQueryWrapper<SubSystem>());
        List<SubSystem> records = subSystemPage.getRecords();
        records.forEach(System.out::println);
    }
}
