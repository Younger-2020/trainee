package com.bosssoft.trainee.tempTest.mapper;

import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.mapper.RoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class roleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void test1() {
        List<Role> roles = roleMapper.selectList(null);
        System.out.println(roles);
        roles.forEach(System.out::println);
    }

    @Test
    public void test2() {
        int i = roleMapper.deleteById(4);
        System.out.println(i);
    }
}
