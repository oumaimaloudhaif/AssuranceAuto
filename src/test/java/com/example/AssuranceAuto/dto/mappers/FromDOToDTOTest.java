package com.example.AssuranceAuto.dto.mappers;

import com.example.AssuranceAuto.AssuranceAutoApplication;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.entities.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AssuranceAutoApplication.class)
public class FromDOToDTOTest {

    @Autowired
    FromDOToDTO fromDOToDTO;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testMapAssurance() {
        final Assurance assurance = new Assurance(1L, "123", List.of(), new Date(),new Date());

        final AssuranceDTO result = fromDOToDTO.MapAssurance(assurance);

        assertEquals("123", result.getAssuranceNumber());
    }

    @Test
    public void testMapAuto() {
        final Assurance assurance = new Assurance(1L, "123", List.of(), new Date(),new Date());

        final Auto auto = new Auto(1L,"123","KIA",assurance,new Date(),new Date());

        final AutoDTO result = fromDOToDTO.MapAuto(auto);

        assertEquals("KIA", result.getModel());
        assertEquals("123", result.getRegistrationNumber());
    }
}
