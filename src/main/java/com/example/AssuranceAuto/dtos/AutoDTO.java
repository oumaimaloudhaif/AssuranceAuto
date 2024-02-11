package com.example.AssuranceAuto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

/** Auto Dto */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@With
public class AutoDTO {
  private String registrationNumber;
  private String model;
}
