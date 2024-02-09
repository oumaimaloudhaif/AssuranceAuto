package com.example.AssuranceAuto.dtos.mappers;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.entities.Auto;
import org.springframework.stereotype.Component;

@Component
public class FromDOToDTO {
    public AutoDTO MapAuto(Auto auto) {
        AutoDTO autoDTO = new AutoDTO();
        autoDTO.setModel(auto.getModel());
        autoDTO.setRegistrationNumber(auto.getRegistrationNumber());
        return autoDTO;
    }
    public AssuranceDTO MapAssurance(Assurance assurance) {
        AssuranceDTO assuranceDTO = new AssuranceDTO();
        assuranceDTO.setAssuranceNumber(assurance.getAssuranceNumber());
        return assuranceDTO;
    }
}
