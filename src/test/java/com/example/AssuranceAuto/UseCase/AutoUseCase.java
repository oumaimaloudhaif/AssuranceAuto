package com.example.AssuranceAuto.UseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.AssuranceAuto.controllers.AbstractTest;
import com.example.AssuranceAuto.controllers.requests.AutoRequest;
import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class AutoUseCase extends AbstractTest {

  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void fetchAllAutosTest() throws Exception {
    // Given
    String url = "/autos";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoResponse projects = objectMapper.readValue(content, AutoResponse.class);
    Assertions.assertEquals(4, projects.getResult().size());
  }

  @Test
  public void getAllAutosTestWrongPathTest() throws Exception {
    // Given
    final String uri = "/autoss";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchAutoTestWhenKeywordIsNullTest() throws Exception {
    // Given
    final String uri = "/autos";

    // when
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .param("keyword", (String) null)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoResponse projects = objectMapper.readValue(content, AutoResponse.class);
    assertEquals(5, projects.getResult().size());
  }

  @Test
  public void getAutosWithNullKeywordReturnsListOfAutosTest() throws Exception {
    // Given
    final String uri = "/autos";
    AutoRequest autoRequest = new AutoRequest();
    autoRequest.setKeyword(" ");

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(autoRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoResponse result = objectMapper.readValue(content, AutoResponse.class);
    assertEquals(5, result.getResult().size());
  }

  @Test
  public void getAutosWithNonNullKeywordTest() throws Exception {
    // Given
    final String uri = "/autos";
    AutoRequest autoRequest = new AutoRequest();
    autoRequest.setKeyword("registration_number");

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(autoRequest.getKeyword())))
            .andExpect(status().isOk())
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoResponse result = objectMapper.readValue(content, AutoResponse.class);
    assertEquals(1, result.getResult().size());
  }

  @Test
  public void addAutoTest() throws Exception {

    // Given
    final String uri = "/autos";
    Auto auto = new Auto();
    auto.setModel("KIA");
    String inputJson = new ObjectMapper().writeValueAsString(auto);

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoDTO result = objectMapper.readValue(content, AutoDTO.class);
    assertEquals("KIA", result.getModel());
  }

  @Test
  public void updateAutoTest() throws Exception {
    // Given
    final String uri = "/autos";
    Auto auto = new Auto();
    auto.setModel("KIA");
    String inputJson = new ObjectMapper().writeValueAsString(auto);

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoDTO result = objectMapper.readValue(content, AutoDTO.class);
    assertEquals("KIA", result.getModel());
  }

  @Test
  public void findAutoByIdTest() throws Exception {
    // Given
    final String uri = "/autos/2";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoDTO result = objectMapper.readValue(content, AutoDTO.class);
    assertEquals("KIA", result.getModel());
  }

  @Test
  public void deleteAutoNotExistTest() throws Exception {
    // Given
    String uri = "/autos/39";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(false, actualValue);
  }

  @Test
  public void deleteAutoExistTest() throws Exception {
    // Given
    String uri = "/autos/2";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(true, actualValue);
  }
}
