package com.bosssoft.trainee.tempTest.service;

import com.bosssoft.trainee.nontax.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void test1() {
        List<String> allRolesById = userService.getAllRolesById(1L);
        System.out.println(allRolesById);
    }
}
