package com.example.AssuranceAuto.services;

import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Auto;

import java.util.List;

/*** Auto Service Interface ***/
public interface AutoService {
    AutoDTO addAuto(Auto auto);
    AutoDTO updateAuto(Auto auto);
    List<AutoDTO> getAllAutos();
    List<AutoDTO> searchAutoByModel(String model);
    List<AutoDTO> searchRegistrationNumber(String registrationNumber);
}
