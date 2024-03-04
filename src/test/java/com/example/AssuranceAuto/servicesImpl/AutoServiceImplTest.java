package com.example.AssuranceAuto.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.AssuranceAuto.AssuranceAutoApplication;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.example.AssuranceAuto.repositories.AutoRepository;
import com.example.AssuranceAuto.services.AutoService;
import com.example.AssuranceAuto.tools.AutoDTOTools;
import com.example.AssuranceAuto.tools.AutoTools;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/** Assurance Service Impl Test */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = AssuranceAutoApplication.class)
@AutoConfigureMockMvc
public class AutoServiceImplTest {
  @MockBean private AutoRepository autoRepository;
  @Autowired private AutoService autoService;
  @MockBean private FromDOToDTO fromDoToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void addAssuranceTest() {
    // Given
    final Auto auto = AutoTools.createAuto(1L, "registrationNumber", "model");
    final AutoDTO autoDTO = AutoDTOTools.createAutoDTO("registrationNumber", "model");

    // When
    when(autoRepository.save(auto)).thenReturn(auto);
    when(fromDoToDTO.mapAuto(auto)).thenReturn(autoDTO);
    final AutoDTO resultAuto = autoService.addAuto(auto);

    // Then
    assertEquals(autoDTO.getRegistrationNumber(), resultAuto.getRegistrationNumber());
    assertEquals(autoDTO.getModel(), resultAuto.getModel());
  }

  @Test
  public void updateAssuranceTest() {
    // Given
    final Auto auto = AutoTools.createAuto(1L, "registrationNumber", "model");
    final AutoDTO autoDTO = AutoDTOTools.createAutoDTO("registrationNumber", "model");

    // When
    when(autoRepository.save(auto)).thenReturn(auto);
    when(fromDoToDTO.mapAuto(auto)).thenReturn(autoDTO);
    final AutoDTO resultAuto = autoService.updateAuto(auto);

    // Then
    assertEquals(autoDTO.getRegistrationNumber(), resultAuto.getRegistrationNumber());
    assertEquals(autoDTO.getModel(), resultAuto.getModel());
  }

  @Test
  public void getAllAssurancesTest() {
    // Given
    final Auto auto = AutoTools.createAuto(1L, "registrationNumber", "model");
    final Auto auto1 = AutoTools.createAuto(1L, "registrationNumber1", "model1");
    final List<Auto> autoListMock = List.of(auto, auto1);
    final AutoDTO autoDTO = AutoDTOTools.createAutoDTO("registrationNumber", "model");
    final AutoDTO autoDTO1 = AutoDTOTools.createAutoDTO("registrationNumber1", "model1");

    // When
    when(autoRepository.findAll()).thenReturn(autoListMock);
    when(fromDoToDTO.mapAuto(auto)).thenReturn(autoDTO);
    when(fromDoToDTO.mapAuto(auto1)).thenReturn(autoDTO1);
    final List<AutoDTO> resultAutoList = autoService.getAllAutos();

    // Then
    assertEquals(autoListMock.size(), resultAutoList.size());
    assertEquals("model", resultAutoList.get(0).getModel());
    assertEquals("registrationNumber", resultAutoList.get(0).getRegistrationNumber());
    assertEquals("model1", resultAutoList.get(1).getModel());
    assertEquals("registrationNumber1", resultAutoList.get(1).getRegistrationNumber());
  }

  @Test
  public void testSearchAuto() {
    // Given
    final String keyword = "kia";
    final Auto auto1 = AutoTools.createAuto(1L, "registrationnumber", "kia");
    final Auto auto2 = AutoTools.createAuto(2L, "registrationnumber1", "kia");
    final List<Auto> mockedAutos = Arrays.asList(auto1, auto2);
    final AutoDTO autoDTO1 = AutoDTOTools.createAutoDTO("registrationnumber", "kia");
    final AutoDTO autoDTO2 = AutoDTOTools.createAutoDTO("registrationnumber1", "kia");

    // When
    when(autoRepository.findByRegistrationNumber(keyword)).thenReturn(mockedAutos);
    when(fromDoToDTO.mapAuto(auto1)).thenReturn(autoDTO1);
    when(fromDoToDTO.mapAuto(auto2)).thenReturn(autoDTO2);
    final List<AutoDTO> autos = autoService.searchRegistrationNumber(keyword);

    // Then
    assertEquals(autos.size(), mockedAutos.size());
  }
}
