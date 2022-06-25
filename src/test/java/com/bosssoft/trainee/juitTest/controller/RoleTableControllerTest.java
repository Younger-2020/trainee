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
public class RoleTableControllerTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * 获取所有角色信息
     *
     * @throws Exception
     */
    @Test
    @Order(1)
    void getList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/role-table/list")
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
     * 获取所有角色信息，携带过滤条件
     * @throws Exception
     */
    @Test
    @Order(2)
    void getListWithCondition() throws Exception{
        MultiValueMap<String, String> stringStringMultiValueMap = new LinkedMultiValueMap<>();
        stringStringMultiValueMap.add("page","1");
        stringStringMultiValueMap.add("limit","20");
        stringStringMultiValueMap.add("sort","+id");
        stringStringMultiValueMap.add("role_name","e");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/role-table/list")
            .params(stringStringMultiValueMap)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = (JSONObject) JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))).get("data");
        // 对data中的数据进行断言
        Assert.notNull(jsonObject.get("total"));
        Assert.notNull(jsonObject.get("items"));
    }

    /**
     * 当该角色不存在时创建角色
     *
     * @throws Exception
     */
    @Test
    @Order(3)
    void createRoleNotExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("role_name", "管理员_createRoleNotExist");
        stringObjectHashMap.put("role_company", "公司1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/role-table/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("保存成功".equals(jsonObject.get("message")));
    }

    /**
     * 当角色存在时创建角色
     *
     * @throws Exception
     */
    @Test
    @Order(4)
    void createRoleExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("role_name", "管理员_createRoleNotExist");
        stringObjectHashMap.put("role_company", "公司1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/role-table/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("角色名已存在！".equals(jsonObject.get("message")));
    }

    /**
     * 修改角色信息
     * @throws Exception
     */
    @Test
    @Order(5)
    void updateRole() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539625086133809153");
        stringObjectHashMap.put("role_name", "管理员_test");
        stringObjectHashMap.put("role_company", "公司2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/role-table/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("修改成功".equals(jsonObject.get("message")));
    }

    /**
     * 当修改角色时由于传递过来的参数异常而导致修改失败
     * @throws Exception
     */
    @Test
    @Order(6)
    void updateRoleWrong() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539625076133809153");
        stringObjectHashMap.put("role_name", "管理员_test");
        stringObjectHashMap.put("role_company", "公司2");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/role-table/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("修改失败".equals(jsonObject.get("message")));
    }

    /**
     * 删除角色信息
     * @throws Exception
     */
    @Test
    @Order(7)
    void deleteRole() throws Exception{
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539776192197505026");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/role-table/delete")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("删除成功".equals(jsonObject.get("message")));
    }
}
