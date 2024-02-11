package com.example.AssuranceAuto.controllers.mappers;

import com.example.AssuranceAuto.controllers.responses.AssuranceResponse;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** Assurance Mapper Test */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssuranceMapperTest {
  @Autowired private AssuranceMapper assuranceMapper;

  @Test
  public void testToAssuranceResponse() {
    // Given

    final AssuranceDTO assuranceDTO = new AssuranceDTO("12KIA");
    final AssuranceDTO assuranceDTO1 = new AssuranceDTO("18KIA");
    List<AssuranceDTO> AddressDTOs = List.of(assuranceDTO, assuranceDTO1);

    // When
    AssuranceResponse result = assuranceMapper.fromAssuranceDTOToAssurance(AddressDTOs);

    // Then
    Assert.assertEquals(result.getResult().size(), 2);
  }
}
