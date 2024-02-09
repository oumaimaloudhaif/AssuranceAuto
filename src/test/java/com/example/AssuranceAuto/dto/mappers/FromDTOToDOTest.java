package com.example.AssuranceAuto.dto.mappers;

import com.example.AssuranceAuto.AssuranceAutoApplication;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDTOToDO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.entities.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AssuranceAutoApplication.class)
public class  FromDTOToDOTest {

    @Autowired
    FromDTOToDO fromDTOToDO;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testMapAssurance() {
        final AssuranceDTO assuranceDTO = new AssuranceDTO("123");

        final Assurance result = fromDTOToDO.MapAssuranceDTO(assuranceDTO);

        assertEquals("123", result.getAssuranceNumber());
    }

    @Test
    public void testMapAuto() {
        final AutoDTO auto = new AutoDTO("123","KIA");

        final Auto result = fromDTOToDO.MapAutoDTO(auto);

        assertEquals("KIA", result.getModel());
        assertEquals("123", result.getRegistrationNumber());
    }
}
