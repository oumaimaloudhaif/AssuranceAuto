package com.example.AssuranceAuto.controllers.responses;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Assurance Response */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceResponse {
  List<AssuranceDTO> result;
}
