package com.example.AssuranceAuto.tools;

import com.example.AssuranceAuto.entities.Auto;
import org.springframework.stereotype.Component;

/** Auto Tools */
@Component
public class AutoTools {
  public static Auto createAuto(Long id, String RegistrationNumber, String model) {

    return new Auto().withAuto_Id(id).withRegistrationNumber(RegistrationNumber).withModel(model);
  }
}
