package com.example.AssuranceAuto.UseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.AssuranceAuto.controllers.AbstractTest;
import com.example.AssuranceAuto.controllers.requests.AssuranceRequest;
import com.example.AssuranceAuto.controllers.responses.AssuranceResponse;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssuranceUseCase extends AbstractTest {

  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
  public void getAllAssurancesTestWhenAssuranceExist() throws Exception {
    // Given
    final String uri = "/assurances";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AssuranceResponse workCalendars = super.mapFromJson(content, AssuranceResponse.class);
    assertEquals(2, workCalendars.getResult().size());
  }

  @Test
  @Order(2)
  public void getAllAssurancesTestWrongPath() throws Exception {
    // Given
    final String uri = "/assurancess";

    // when
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  @Order(3)
  public void fetchAssuranceWithNonNullKeyword() throws Exception {
    // Given
    final String uri = "/assurances";
    AssuranceRequest assuranceRequest = new AssuranceRequest();
    assuranceRequest.setKeyword("Assurance1");

    // When
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(assuranceRequest.getKeyword())))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AssuranceResponse assuranceResponse = objectMapper.readValue(content, AssuranceResponse.class);
    assertEquals(1, assuranceResponse.getResult().size());
  }

  @Test
  @Order(4)
  public void addAssuranceTest() throws Exception {

    // Given
    final String uri = "/assurances";
    Assurance assurance = new Assurance();
    assurance.setAssuranceNumber("assuranceNumber3");
    String inputJson = new ObjectMapper().writeValueAsString(assurance);

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
    AssuranceDTO result = objectMapper.readValue(content, AssuranceDTO.class);
    assertEquals("assuranceNumber3", result.getAssuranceNumber());
  }

  @Test
  @Order(5)
  public void updateAssuranceTest() throws Exception {
    // Given
    final String uri = "/assurances";

    Assurance assurance = new Assurance();
    assurance.setAssurance_Id(3L);
    assurance.setAssuranceNumber("assuranceNumber3");
    String inputJson = new ObjectMapper().writeValueAsString(assurance);

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
    AssuranceDTO result = objectMapper.readValue(content, AssuranceDTO.class);
    assertEquals("assuranceNumber3", result.getAssuranceNumber());
  }

  @Test
  @Order(6)
  public void findAssuranceById() throws Exception {
    // Given
    final String uri = "/assurances/3";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AssuranceDTO result = objectMapper.readValue(content, AssuranceDTO.class);
    assertEquals("assuranceNumber3", result.getAssuranceNumber());
  }

  @Test
  @Order(7)
  public void deleteAssuranceNotExistTest() throws Exception {
    // Given
    String uri = "/assurances/118";

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
  @Order(8)
  public void deleteAssuranceExistTest() throws Exception {
    // Given
    String uri = "/assurances/1";

    // when
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    Boolean actualValue = Boolean.valueOf(content);
    assertEquals(true, actualValue);
  }
  @Test
  @Order(9)
  public void searchAssuranceTestWhenKeywordIsNull() throws Exception {
    // Given
    final String uri = "/assurances";

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
    AssuranceResponse assuranceResponse = super.mapFromJson(content, AssuranceResponse.class);
    assertEquals(2, assuranceResponse.getResult().size());
  }
}
