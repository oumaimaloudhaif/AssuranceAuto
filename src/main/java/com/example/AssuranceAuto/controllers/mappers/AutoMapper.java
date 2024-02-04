package com.example.AssuranceAuto.controllers.mappers;

import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/*** Auto Mapper ***/
@Component
public class AutoMapper {
    public AutoResponse fromAutoDTOToAuto(List<AutoDTO> autoDTOS){
        AutoResponse autoResponse =new AutoResponse();
        autoResponse.setResult(autoDTOS);
        return autoResponse;
    }
}
