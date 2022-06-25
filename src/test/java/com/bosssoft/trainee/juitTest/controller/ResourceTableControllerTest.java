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
class ResourceTableControllerTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * 获取所有资源信息
     *
     * @throws Exception
     */
    @Test
    @Order(1)
    void getList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/resources-table/list")
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
     * 获取所有资源信息，携带过滤条件
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
        stringStringMultiValueMap.add("resource_name", "add");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/resources-table/list")
                .params(stringStringMultiValueMap)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = (JSONObject) JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))).get("data");
        // 对data中的数据进行断言
        Assert.notNull(jsonObject.get("total"));
        Assert.notNull(jsonObject.get("items"));
    }

    /**
     * 当该资源不存在时创建资源
     *
     * @throws Exception
     */
    @Test
    @Order(3)
    void createResourceNotExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("resource_type", "菜单");
        stringObjectHashMap.put("resource_name", "菜单_createResourceNotExist");
        stringObjectHashMap.put("resource_url", "/view/subsys_test");
        stringObjectHashMap.put("resource_api", "/test");
        stringObjectHashMap.put("resource_remark", "update测试用例");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/resources-table/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("创建资源成功".equals(jsonObject.get("message")));
    }

    /**
     * 当资源存在时创建资源
     *
     * @throws Exception
     */
    @Test
    @Order(4)
    void createResourceExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("resource_type", "菜单");
        stringObjectHashMap.put("resource_name", "菜单_createResourceNotExist");
        stringObjectHashMap.put("resource_url", "/view/subsys_test");
        stringObjectHashMap.put("resource_api", "/test");
        stringObjectHashMap.put("resource_remark", "update测试用例");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/resources-table/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("资源已存在".equals(jsonObject.get("message")));
    }

    /**
     * 修改资源信息
     *
     * @throws Exception
     */
    @Test
    @Order(5)
    void updateResource() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1540477036928729090");
        stringObjectHashMap.put("resource_type", "菜单");
        stringObjectHashMap.put("resource_name", "menu_update");
        stringObjectHashMap.put("resource_url", "/view/subsys_test_update");
        stringObjectHashMap.put("resource_api", "/test");
        stringObjectHashMap.put("resource_remark", "update测试用例");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/resources-table/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("资源信息修改成功".equals(jsonObject.get("message")));
    }

    /**
     * 当修改资源时由于传递过来的参数异常而导致修改失败
     *
     * @throws Exception
     */
    @Test
    @Order(6)
    void updateResourceWrong() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1540477036958729090");
        stringObjectHashMap.put("resource_type", "菜单");
        stringObjectHashMap.put("resource_name", "menu_update");
        stringObjectHashMap.put("resource_url", "/view/subsys_test_update");
        stringObjectHashMap.put("resource_api", "/test");
        stringObjectHashMap.put("resource_remark", "update测试用例");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/resources-table/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("资源信息修改失败".equals(jsonObject.get("message")));
    }

    /**
     * 删除资源
     *
     * @throws Exception
     */
    @Test
    @Order(7)
    void deleteResource() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539792221137014786");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/resources-table/delete")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("资源信息删除成功".equals(jsonObject.get("message")));
    }
}
