package com.example.AssuranceAuto.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.AssuranceAuto.controllers.requests.AssuranceRequest;
import com.example.AssuranceAuto.controllers.responses.AssuranceResponse;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.exceptions.InternalException;
import com.example.AssuranceAuto.servicesImpl.AssuranceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

/** Assurance Controller Test */
public class AssuranceControllerTest extends AbstractTest {

  @MockBean AssuranceServiceImpl assuranceService;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllAssurancesTestWhenAssuranceExist() throws Exception {
    // Given
    final String uri = "/assurances";
    final AssuranceDTO assuranceDTO = new AssuranceDTO("assuranceNumber");
    final AssuranceDTO assuranceDTO1 = new AssuranceDTO("assuranceNumber1");
    final List<AssuranceDTO> assuranceDTOList = List.of(assuranceDTO, assuranceDTO1);

    // When
    when(assuranceService.getAllAssurances()).thenReturn(assuranceDTOList);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AssuranceResponse assurances = super.mapFromJson(content, AssuranceResponse.class);
    assertEquals(2, assurances.getResult().size());
  }

  @Test
  public void getAllAssurancesTestThenThrowInternalException() throws Exception {
    // Given
    final String uri = "/assurances";

    // When
    when(assuranceService.getAllAssurances())
        .thenThrow(new InternalException("Internal exception"));
    mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isInternalServerError())
        .andExpect(
            result -> {
              assertInstanceOf(InternalException.class, result.getResolvedException());
            })
        .andExpect(
            result -> {
              assertEquals(
                  "Internal exception",
                  Objects.requireNonNull(result.getResolvedException()).getMessage());
            });
  }

  @Test
  public void getAllAssurancesTestWhenNoAssuranceExist() throws Exception {
    // Given
    final String uri = "/assurances";
    final List<AssuranceDTO> assuranceDTOList = List.of();

    // When
    when(assuranceService.getAllAssurances()).thenReturn(assuranceDTOList);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AssuranceResponse assurances = super.mapFromJson(content, AssuranceResponse.class);
    assertEquals(0, assurances.getResult().size());
  }

  @Test
  public void getAllAssurancesTestWrongPath() throws Exception {
    // Given
    final String uri = "/assurancess";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchAssurancesTestWhenKeywordIsNull() throws Exception {
    // Given
    final String uri = "/assurances";

    // When
    when(assuranceService.searchAssuranceByAssuranceNumber(null)).thenReturn(List.of());
    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.get(uri)
                    .param("keyword", (String) null)
                    .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AssuranceResponse assurances = super.mapFromJson(content, AssuranceResponse.class);
    assertEquals(0, assurances.getResult().size());
  }

  @Test
  public void getAssurancesWithEmptyKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/assurances";
    AssuranceRequest companyRequest = new AssuranceRequest();
    companyRequest.setKeyword("");
    mvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyRequest.getKeyword())))
        .andExpect(
            result -> {
              assertInstanceOf(
                  MethodArgumentNotValidException.class, result.getResolvedException());
            })
        .andExpect(status().isBadRequest());
  }

  @Test
  public void addAssuranceTest() throws Exception {

    // Given
    final String uri = "/assurances";
    Assurance assurance = new Assurance();
    assurance.setAssuranceNumber("assuranceNumber");
    String inputJson = new ObjectMapper().writeValueAsString(assurance);
    final AssuranceDTO assuranceDTO = new AssuranceDTO("assuranceNumber");

    // When
    when(assuranceService.addAssurance(any(Assurance.class))).thenReturn(assuranceDTO);
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
    assertEquals(assuranceDTO.getAssuranceNumber(), result.getAssuranceNumber());
  }

  @Test
  public void updateAssurance() throws Exception {
    // Given
    final String uri = "/assurances";
    Assurance assurance = new Assurance();
    assurance.setAssuranceNumber("assuranceNumber");
    String inputJson = new ObjectMapper().writeValueAsString(assurance);
    final AssuranceDTO assuranceDTO = new AssuranceDTO("assuranceNumber");

    // When
    when(assuranceService.updateAssurance(any(Assurance.class))).thenReturn(assuranceDTO);
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
    assertEquals(assuranceDTO.getAssuranceNumber(), result.getAssuranceNumber());
  }
}
