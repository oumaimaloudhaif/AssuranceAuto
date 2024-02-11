package com.example.AssuranceAuto.tools;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import org.springframework.stereotype.Component;

/** Assurance DTO Tools */
@Component
public class AssuranceDTOTools {
  public static AssuranceDTO createAssuranceDTO(String assuranceNumber) {

    return new AssuranceDTO().withAssuranceNumber(assuranceNumber);
  }
}
