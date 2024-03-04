package com.example.AssuranceAuto.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.AssuranceAuto.AssuranceAutoApplication;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.repositories.AssuranceRepository;
import com.example.AssuranceAuto.services.AssuranceService;
import com.example.AssuranceAuto.tools.AssuranceDTOTools;
import com.example.AssuranceAuto.tools.AssuranceTools;
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
public class AssuranceServiceImplTest {
  @MockBean private AssuranceRepository assuranceRepository;
  @Autowired private AssuranceService assuranceService;
  @MockBean private FromDOToDTO fromDoToDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddAssurance() {
    // Given
    final Assurance assurance = AssuranceTools.createAssurance(1L, "assuranceNumber");
    final AssuranceDTO assuranceDTO = AssuranceDTOTools.createAssuranceDTO("assuranceNumber");

    // When
    when(assuranceRepository.save(assurance)).thenReturn(assurance);
    when(fromDoToDTO.mapAssurance(assurance)).thenReturn(assuranceDTO);
    final AssuranceDTO expectedAssurance = assuranceService.addAssurance(assurance);

    // Then
    assertEquals(expectedAssurance.getAssuranceNumber(), assurance.getAssuranceNumber());
  }

  @Test
  public void testUpdateAssurance() {
    // Given
    final Assurance assurance = AssuranceTools.createAssurance(1L, "assuranceNumber");
    final AssuranceDTO assuranceDTO = AssuranceDTOTools.createAssuranceDTO("assuranceNumber");

    // When
    when(assuranceRepository.save(assurance)).thenReturn(assurance);
    when(fromDoToDTO.mapAssurance(assurance)).thenReturn(assuranceDTO);
    final AssuranceDTO expectedAssurance = assuranceService.updateAssurance(assurance);

    // Then
    assertEquals(expectedAssurance.getAssuranceNumber(), assurance.getAssuranceNumber());
  }

  @Test
  public void testGetAllAssuranceTest() {
    // Given
    final Assurance assurance = AssuranceTools.createAssurance(1L, "assuranceNumber");
    final Assurance assurance1 = AssuranceTools.createAssurance(1L, "assuranceNumber1");
    final List<Assurance> assuranceListMock = List.of(assurance, assurance1);
    final AssuranceDTO assuranceDTO = AssuranceDTOTools.createAssuranceDTO("assuranceNumber");
    final AssuranceDTO assuranceDTO1 = AssuranceDTOTools.createAssuranceDTO("assuranceNumber1");

    // When
    when(assuranceRepository.findAll()).thenReturn(assuranceListMock);
    when(fromDoToDTO.mapAssurance(assurance)).thenReturn(assuranceDTO);
    when(fromDoToDTO.mapAssurance(assurance1)).thenReturn(assuranceDTO1);
    final List<AssuranceDTO> resultAssuranceList = assuranceService.getAllAssurances();

    // Then
    assertEquals(assuranceListMock.size(), resultAssuranceList.size());
    assertEquals("assuranceNumber", resultAssuranceList.get(0).getAssuranceNumber());
    assertEquals("assuranceNumber1", resultAssuranceList.get(1).getAssuranceNumber());
  }

  @Test
  public void testSearchAssurance() {
    // Given
    final String keyword = "assuranceNumber";
    final Assurance assurance1 = AssuranceTools.createAssurance(1L, "assuranceNumber");
    final Assurance assurance2 = AssuranceTools.createAssurance(2L, "assuranceNumber");
    final List<Assurance> mockedAssurances = Arrays.asList(assurance1, assurance2);
    final AssuranceDTO assuranceDTO1 = AssuranceDTOTools.createAssuranceDTO("assuranceNumber");
    final AssuranceDTO assuranceDTO2 = AssuranceDTOTools.createAssuranceDTO("assuranceNumber");

    // When
    when(assuranceRepository.findByAssuranceNumber(keyword)).thenReturn(mockedAssurances);
    when(fromDoToDTO.mapAssurance(assurance1)).thenReturn(assuranceDTO1);
    when(fromDoToDTO.mapAssurance(assurance2)).thenReturn(assuranceDTO2);
    final List<AssuranceDTO> assuranceDTOS =
        assuranceService.searchAssuranceByAssuranceNumber(keyword);

    // Then
    assertEquals(assuranceDTOS.size(), mockedAssurances.size());
    assertEquals("assuranceNumber", mockedAssurances.get(0).getAssuranceNumber());
    assertEquals(assuranceDTOS.get(0).getAssuranceNumber(), mockedAssurances.get(0).getAssuranceNumber());
    assertEquals(assuranceDTOS.get(1).getAssuranceNumber(), mockedAssurances.get(1).getAssuranceNumber());
  }
}
