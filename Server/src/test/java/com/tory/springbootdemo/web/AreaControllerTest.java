package com.tory.springbootdemo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tory.springbootdemo.SpringbootdemoApplication;
import com.tory.springbootdemo.entity.Area;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(classes = SpringbootdemoApplication.class)
class AreaControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;  //获取上下文对象

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAdd() throws Exception {
        Area area = new Area();
//        area.setAreaId(6);
        area.setAreaName("南苑");
        area.setPriority(5);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(area); //将area对象转换为json字符串

        String response = mockMvc.perform(  //发送请求
                    MockMvcRequestBuilders.post("/area/update")
                    .content(jsonStr).contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())       //校验返回的状态码
                .andDo(MockMvcResultHandlers.print())   //执行回调操作，打印HTTP请求与响应信息
                .andReturn().getResponse().getContentAsString();    //获取返回字符串
        System.out.println(response);
    }
}