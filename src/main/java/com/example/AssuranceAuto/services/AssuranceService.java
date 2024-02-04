package com.example.AssuranceAuto.services;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.entities.Assurance;

import java.util.List;

/*** Assurance Service Interface ***/
public interface AssuranceService {
    AssuranceDTO addAssurance(Assurance assurance );
    AssuranceDTO updateAuto(Assurance assurance);
    List<AssuranceDTO> getAllAssurances();
    List<AssuranceDTO> searchAssuranceByAssuranceNumber(String assuranceNumber);
}
