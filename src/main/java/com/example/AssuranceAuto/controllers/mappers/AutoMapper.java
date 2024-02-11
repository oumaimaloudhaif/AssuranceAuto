package com.example.AssuranceAuto.controllers.mappers;

import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/** Auto Mapper */
@Component
public class AutoMapper {
  public AutoResponse fromAutoDTOToAuto(List<AutoDTO> autoDTOS) {
    AutoResponse autoResponse = new AutoResponse();
    autoResponse.setResult(autoDTOS);
    return autoResponse;
  }
}
