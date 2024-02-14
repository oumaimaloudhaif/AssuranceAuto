package com.example.AssuranceAuto.controllers;

import com.example.AssuranceAuto.AssuranceAutoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AssuranceAutoApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

  protected MockMvc mvc;
  @Autowired WebApplicationContext webApplicationContext;

  protected void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, clazz);
  }
}
