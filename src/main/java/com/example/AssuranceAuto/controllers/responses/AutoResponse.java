package com.example.AssuranceAuto.controllers.responses;


import com.example.AssuranceAuto.dtos.AutoDTO;
import lombok.*;

import java.util.List;
/*** Auto Response  ***/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutoResponse {
    List<AutoDTO> result;
}
