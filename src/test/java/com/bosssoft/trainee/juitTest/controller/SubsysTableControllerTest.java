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
class SubsysTableControllerTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * 获取所有子系统信息
     *
     * @throws Exception
     */
    @Test
    @Order(1)
    void getList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/subsys-table/list")
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
     * 获取所有子系统信息，携带过滤条件
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
        stringStringMultiValueMap.add("subsys_name", "s");
        stringStringMultiValueMap.add("subsys_remark", "子");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/subsys-table/list")
                .params(stringStringMultiValueMap)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = (JSONObject) JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"))).get("data");
        // 对data中的数据进行断言
        Assert.notNull(jsonObject.get("total"));
        Assert.notNull(jsonObject.get("items"));
    }

    /**
     * 当该子系统不存在时创建子系统
     *
     * @throws Exception
     */
    @Test
    @Order(3)
    void createSubSysNotExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("subsys_name", "createSubSysNotExist");
        stringObjectHashMap.put("subsys_image_url", "123456");
        stringObjectHashMap.put("subsys_link", "/view/subsys_test");
        stringObjectHashMap.put("subsys_remark", "公司1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/subsys-table/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("创建子系统成功".equals(jsonObject.get("message")));
    }

    /**
     * 当子系统存在时创建子系统
     *
     * @throws Exception
     */
    @Test
    @Order(4)
    void createSubSysExist() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("subsys_name", "createSubSysNotExist");
        stringObjectHashMap.put("subsys_image_url", "123456");
        stringObjectHashMap.put("subsys_link", "/view/subsys_test");
        stringObjectHashMap.put("subsys_remark", "公司1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/subsys-table/create")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("子系统已存在".equals(jsonObject.get("message")));
    }

    /**
     * 修改子系统信息
     *
     * @throws Exception
     */
    @Test
    @Order(5)
    void updateSubSys() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539396540949901313");
        stringObjectHashMap.put("subsys_image_url", "123456");
        stringObjectHashMap.put("subsys_link", "/view/subsys_test");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/subsys-table/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("子系统信息修改成功".equals(jsonObject.get("message")));
    }

    /**
     * 当修改子系统时由于传递过来的参数异常而导致修改失败
     *
     * @throws Exception
     */
    @Test
    @Order(6)
    void updateSubSysWrong() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539396540940901313");
        stringObjectHashMap.put("subsys_image_url", "123456");
        stringObjectHashMap.put("subsys_link", "/view/subsys_test");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/subsys-table/update")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isFalse((boolean) jsonObject.get("ok"));
        Assert.isTrue("子系统信息修改失败".equals(jsonObject.get("message")));
    }

    /**
     * 删除角色信息
     *
     * @throws Exception
     */
    @Test
    @Order(7)
    void deleteSubSys() throws Exception {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", "1539396540949901314");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/subsys-table/delete")
                .contentType("application/json")
                .content(JSON.toJSONString(stringObjectHashMap))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")));
        // 对data中的数据进行断言
        Assert.isTrue((boolean) jsonObject.get("ok"));
        Assert.isTrue("子系统信息删除成功".equals(jsonObject.get("message")));
    }
}
