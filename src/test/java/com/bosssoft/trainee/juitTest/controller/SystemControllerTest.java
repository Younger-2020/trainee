package com.bosssoft.trainee.juitTest.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SystemControllerTest {
    @Autowired
    MockMvc mockMvc;

    private static String token = null;

    /**
     * 登录操作，登录成功
     *
     * @throws Exception
     */
    @Test
    @Order(1)
    void login() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("username", "admin");
        stringObjectHashMap.put("password", "123abc");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        JSONObject data = (JSONObject) jsonObject.get("data");
        token = (String) data.get("token");
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("登录成功".equals(jsonObject.get("message")));
        Assert.notNull(token);
    }

    /**
     * 登录操作，用户名或密码错误
     *
     * @throws Exception
     */
    @Test
    @Order(2)
    void loginWrong() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("username", "admin_wrong");
        stringObjectHashMap.put("password", "123abc");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        JSONObject data = (JSONObject) jsonObject.get("data");
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("用户名或密码错误".equals(jsonObject.get("message")));
        Assert.isNull(data);
    }

    /**
     * 根据token获取用户信息
     *
     * @throws Exception
     */
    @Test
    @Order(3)
    void getInfo() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/info")
                .param("token", token)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        JSONObject data = (JSONObject) jsonObject.get("data");
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("成功".equals(jsonObject.get("message")));
        Assert.notNull(data.get("roles"));
        Assert.notNull(data.get("name"));
    }

    /**
     * 登录使用的token过期
     *
     * @throws Exception
     */
    @Test
    @Order(4)
    void getInfoWithExpiredToken() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/info")
                .param("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDM1tTAzMrQ00lEqLU4t8kwBikGYfom5qUAtiSm5mXlA5UX5OanFSlbRcIGS1OKS1CKl2FoAKhiHWl0AAAA.lR0wichVtdx56_sbSQr8x-xgxnXugqoCLVln66YaBkUOyPF9XIJvvldur7nyeHXve2Bg6Ms4cpU7IN-yKE6e-g")
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        JSONObject data = (JSONObject) jsonObject.get("data");
        System.out.println(jsonObject);
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("token已过期！".equals(jsonObject.get("message")));
        Assert.isNull(data);
    }
}
