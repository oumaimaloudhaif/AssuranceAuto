package com.example.AssuranceAuto.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.AssuranceAuto.controllers.requests.AutoRequest;
import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.example.AssuranceAuto.exceptions.InternalException;
import com.example.AssuranceAuto.servicesImpl.AutoServiceImpl;
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

/** Auto Controller Test */
public class AutoControllerTest extends AbstractTest {

  @MockBean AutoServiceImpl autoServiceImpl;
  @Autowired private ObjectMapper objectMapper;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getAllAutosTestWhenAutoExist() throws Exception {
    // Given
    final String uri = "/autos";
    final AutoDTO autoDTO = new AutoDTO("registrationNumber", "model");
    final AutoDTO autoDTO1 = new AutoDTO("registrationNumber1", "model1");
    final List<AutoDTO> autoDTOList = List.of(autoDTO, autoDTO1);

    // When
    when(autoServiceImpl.getAllAutos()).thenReturn(autoDTOList);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoResponse autos = super.mapFromJson(content, AutoResponse.class);
    assertEquals(2, autos.getResult().size());
    assertEquals("registrationNumber", autos.getResult().get(0).getRegistrationNumber());
    assertEquals("model", autos.getResult().get(0).getModel());
    assertEquals("registrationNumber1", autos.getResult().get(1).getRegistrationNumber());
    assertEquals("model1", autos.getResult().get(1).getModel());
  }

  @Test
  public void getAllAutosTestWhenNoAutoExist() throws Exception {
    // Given
    final String uri = "/autos";
    final List<AutoDTO> autoDTOList = List.of();

    // When
    when(autoServiceImpl.getAllAutos()).thenReturn(autoDTOList);
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(200, status);
    String content = mvcResult.getResponse().getContentAsString();
    AutoResponse autos = super.mapFromJson(content, AutoResponse.class);
    assertEquals(0, autos.getResult().size());
  }

  @Test
  public void getAllAutosTestWrongPath() throws Exception {
    // Given
    final String uri = "/autoss";

    // When
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();

    // Then
    assertEquals(404, status);
  }

  @Test
  public void searchAutosTestWhenKeywordIsNull() throws Exception {
    // Given
    final String uri = "/autos";

    // When
    when(autoServiceImpl.searchRegistrationNumber(null)).thenReturn(List.of());
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
    AutoResponse autos = super.mapFromJson(content, AutoResponse.class);
    assertEquals(0, autos.getResult().size());
  }

  @Test
  public void getAutosWithNullKeywordReturnsEmptyList() throws Exception {
    // Given
    final String uri = "/autos";
    AutoRequest autoRequest = new AutoRequest();
    autoRequest.setKeyword("");

    // When
    mvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(autoRequest.getKeyword())))
        .andExpect(
            result -> assertInstanceOf(
                MethodArgumentNotValidException.class, result.getResolvedException()))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void addAutoTest() throws Exception {
    // Given
    final String uri = "/autos";
    Auto auto = new Auto();
    auto.setRegistrationNumber("assuranceNumber");
    String inputJson = new ObjectMapper().writeValueAsString(auto);
    final AutoDTO autoDTO = new AutoDTO("assuranceNumber", "model");

    // When
    when(autoServiceImpl.addAuto(any(Auto.class))).thenReturn(autoDTO);
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
    assertEquals(autoDTO.getRegistrationNumber(), result.getRegistrationNumber());
    assertEquals(autoDTO.getModel(), result.getModel());

  }

  @Test
  public void updateAuto() throws Exception {
    // Given
    final String uri = "/autos";
    Auto auto = new Auto();
    auto.setRegistrationNumber("assuranceNumber");
    String inputJson = new ObjectMapper().writeValueAsString(auto);
    final AutoDTO autoDTO = new AutoDTO("assuranceNumber", "model");

    // When
    when(autoServiceImpl.updateAuto(any(Auto.class))).thenReturn(autoDTO);
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
    assertEquals(autoDTO.getRegistrationNumber(), result.getRegistrationNumber());
    assertEquals(autoDTO.getModel(), result.getModel());
  }
  @Test
  public void getAllAssurancesTestThenThrowInternalException() throws Exception {
    // Given
    final String uri = "/autos";

    // When
    when(autoServiceImpl.getAllAutos())
            .thenThrow(new InternalException("Internal exception"));
    mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isInternalServerError())
            .andExpect(
                    result -> assertInstanceOf(InternalException.class, result.getResolvedException()))
            .andExpect(
                    result -> assertEquals(
                            "Internal exception",
                            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }
}
