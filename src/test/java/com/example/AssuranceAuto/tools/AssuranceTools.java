package com.example.AssuranceAuto.tools;

import com.example.AssuranceAuto.entities.Assurance;
import org.springframework.stereotype.Component;

/** Assurance Tools */
@Component
public class AssuranceTools {
  public static Assurance createAssurance(Long id, String assuranceNumber) {

    return new Assurance().withAssurance_Id(id).withAssuranceNumber(assuranceNumber);
  }
}
