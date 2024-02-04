package com.example.AssuranceAuto.controllers.responses;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import lombok.*;

import java.util.List;
/*** Assurance Response  ***/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceResponse {
    List<AssuranceDTO> result;
}
