package com.example.AssuranceAuto.controllers.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/*** Auto Request  ***/
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
