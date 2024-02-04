package com.example.AssuranceAuto.controllers.mappers;

import com.example.AssuranceAuto.controllers.responses.AssuranceResponse;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
/*** Assurance Mapper ***/
@Component
public class AssuranceMapper {
    public  AssuranceResponse fromAssuranceDTOToAssurance(List<AssuranceDTO> assuranceDTOS){
        AssuranceResponse assuranceResponse =new AssuranceResponse();
        assuranceResponse.setResult(assuranceDTOS);
        return assuranceResponse;
    }
}
