package com.bosssoft.trainee.tempTest.mapper;

import com.bosssoft.trainee.nontax.entity.dto.UserDTO;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        List<Role> rolesById = userMapper.getRolesById(1L);
        rolesById.forEach(System.out::println);
    }

    @Test
    public void test2() {
        UserDTO admin = userMapper.login("admin", "123abc");
        System.out.println(admin);
    }
}
