package com.example.AssuranceAuto.controllers.mappers;

import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** Auto Mapper Test */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoMapperTest {
  @Autowired private AutoMapper autoMapper;

  @Test
  public void testToAssuranceResponse() {
    // Given

    final AutoDTO assuranceDTO = new AutoDTO("123", "KIA");
    final AutoDTO assuranceDTO1 = new AutoDTO("133", "RIO");
    List<AutoDTO> AddressDTOs = List.of(assuranceDTO, assuranceDTO1);

    // When
    AutoResponse result = autoMapper.fromAutoDTOToAuto(AddressDTOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
