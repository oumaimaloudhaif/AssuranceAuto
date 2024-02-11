package com.example.AssuranceAuto.controllers.mappers;

import com.example.AssuranceAuto.controllers.responses.AssuranceResponse;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** Assurance Mapper */
@Component
public class AssuranceMapper {
  public AssuranceResponse fromAssuranceDTOToAssurance(List<AssuranceDTO> assuranceDTOS) {
    AssuranceResponse assuranceResponse = new AssuranceResponse();
    assuranceResponse.setResult(assuranceDTOS);
    return assuranceResponse;
  }
}
