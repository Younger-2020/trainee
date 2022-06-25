package com.bosssoft.trainee.juitTest.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.Charset;
import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserManagementControllerTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * 获取所有用户信息
     *
     * @throws Exception
     */
    @Test
    @Order(1)
    void getList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user-management/list")
                .param("page", "1")
                .param("limit", "20")
                .param("sort", "+id")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = (JSONObject) JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))).get("data");
        // 对data中的数据进行断言
        Assert.notNull(jsonObject.get("total"));
        Assert.notNull(jsonObject.get("items"));
    }

    /**
     * 获取所有用户信息，携带过滤条件
     *
     * @throws Exception
     */
    @Test
    @Order(2)
    void getListWithCondition() throws Exception {
        MultiValueMap<String, String> stringStringMultiValueMap = new LinkedMultiValueMap<>();
        stringStringMultiValueMap.add("page", "1");
        stringStringMultiValueMap.add("limit", "20");
        stringStringMultiValueMap.add("sort", "+id");
        stringStringMultiValueMap.add("user_name", "ad");
        stringStringMultiValueMap.add("phone_num", "123");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user-management/list")
                .params(stringStringMultiValueMap)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = (JSONObject) JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))).get("data");
        // 对data中的数据进行断言
        Assert.notNull(jsonObject.get("total"));
        Assert.notNull(jsonObject.get("items"));
    }

    /**
     * 当该用户不存在时创建用户
     *
     * @throws Exception
     */
    @Test
    @Order(3)
    void createUserNotExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("user_name", "user_createUserNotExist");
        stringObjectHashMap.put("password", "123456");
        stringObjectHashMap.put("sex", "男");
        stringObjectHashMap.put("phone_num", "15555589800");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user-management/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("创建用户成功".equals(jsonObject.get("message")));
    }

    /**
     * 当用户存在时创建用户
     *
     * @throws Exception
     */
    @Test
    @Order(4)
    void createUserExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("user_name", "user_createUserNotExist");
        stringObjectHashMap.put("password", "123456");
        stringObjectHashMap.put("sex", "男");
        stringObjectHashMap.put("phone_num", "15555589800");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user-management/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("用户已存在".equals(jsonObject.get("message")));
    }

    /**
     * 修改用户信息
     *
     * @throws Exception
     */
    @Test
    @Order(5)
    void updateUser() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539799573135360003");
        stringObjectHashMap.put("user_name", "user_update");
        stringObjectHashMap.put("password", "123456");
        stringObjectHashMap.put("sex", "男");
        stringObjectHashMap.put("phone_num", "15555589800");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user-management/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("用户信息修改成功".equals(jsonObject.get("message")));
    }

    /**
     * 当修改用户时由于传递过来的参数异常而导致修改失败
     *
     * @throws Exception
     */
    @Test
    @Order(6)
    void updateUserWrong() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539799573545360003");
        stringObjectHashMap.put("user_name", "user_update");
        stringObjectHashMap.put("password", "123456");
        stringObjectHashMap.put("sex", "男");
        stringObjectHashMap.put("phone_num", "15555589800");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user-management/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("用户信息修改失败".equals(jsonObject.get("message")));
    }

    /**
     * 删除用户
     *
     * @throws Exception
     */
    @Test
    @Order(7)
    void deleteUser() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539799573135360004");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user-management/delete")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("用户删除成功".equals(jsonObject.get("message")));
    }
}
