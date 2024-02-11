package com.example.AssuranceAuto.controllers.responses;

import com.example.AssuranceAuto.dtos.AutoDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Auto Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutoResponse {
  List<AutoDTO> result;
}
