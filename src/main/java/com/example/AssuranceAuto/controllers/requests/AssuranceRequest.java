package com.example.AssuranceAuto.controllers.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/*** Assurance Request  ***/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssuranceRequest {
 @NotNull
 @NotEmpty(message = "ASSURANCE NUMBER NOT BE EMPTY")
 String keyword;
}
