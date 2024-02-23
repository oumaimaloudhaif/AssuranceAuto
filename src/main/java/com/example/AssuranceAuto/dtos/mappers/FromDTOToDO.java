package com.example.AssuranceAuto.dtos.mappers;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.entities.Auto;
import org.springframework.stereotype.Component;

/** From DTO To DO */
@Component
public class FromDTOToDO {
  public Auto mapAutoDTO(AutoDTO autoDto) {
    Auto auto = new Auto();
    auto.setModel(autoDto.getModel());
    auto.setRegistrationNumber(autoDto.getRegistrationNumber());

    return auto;
  }

  public Assurance mapAssuranceDTO(AssuranceDTO assuranceDTO) {
    Assurance assurance = new Assurance();
    assurance.setAssuranceNumber(assuranceDTO.getAssuranceNumber());

    return assurance;
  }
}
