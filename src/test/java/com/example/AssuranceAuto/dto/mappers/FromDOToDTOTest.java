package com.example.AssuranceAuto.dto.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.AssuranceAuto.AssuranceAutoApplication;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.entities.Auto;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** From DO To DTO Test */
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = AssuranceAutoApplication.class)
public class FromDOToDTOTest {

  @Autowired FromDOToDTO fromDoToDTO;

  @BeforeEach
  public void setUp() {}

  @Test
  public void testMapAssurance() {
    final Assurance assurance = new Assurance(1L, "123", List.of(), new Date(), new Date());

    final AssuranceDTO result = fromDoToDTO.MapAssurance(assurance);

    assertEquals("123", result.getAssuranceNumber());
  }

  @Test
  public void testMapAuto() {
    final Assurance assurance = new Assurance(1L, "123", List.of(), new Date(), new Date());

    final Auto auto = new Auto(1L, "123", "KIA", assurance, new Date(), new Date());

    final AutoDTO result = fromDoToDTO.MapAuto(auto);

    assertEquals("KIA", result.getModel());
    assertEquals("123", result.getRegistrationNumber());
  }
}
