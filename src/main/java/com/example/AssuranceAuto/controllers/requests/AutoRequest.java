package com.example.AssuranceAuto.controllers.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** * Auto Request ** */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AutoRequest {
  @NotNull
  @NotEmpty(message = "AUTO REGISTRATION NUMBER NOT BE EMPTY")
  String keyword;
}
