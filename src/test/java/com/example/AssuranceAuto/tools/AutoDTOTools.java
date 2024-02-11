package com.example.AssuranceAuto.tools;

import com.example.AssuranceAuto.dtos.AutoDTO;
import org.springframework.stereotype.Component;

/** Auto DTO Tools */
@Component
public class AutoDTOTools {
  public static AutoDTO createAutoDTO(String registrationNumber, String model) {

    return new AutoDTO().withRegistrationNumber(registrationNumber).withModel(model);
  }
}
