package com.example.AssuranceAuto.services;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.entities.Assurance;
import java.util.List;

/** Assurance Service Interface */
public interface AssuranceService {
  AssuranceDTO addAssurance(Assurance assurance);

  AssuranceDTO updateAssurance(Assurance assurance);

  List<AssuranceDTO> getAllAssurances();

  List<AssuranceDTO> searchAssuranceByAssuranceNumber(String assuranceNumber);
  AssuranceDTO getAssuranceById(Long assuranceId) ;
  boolean deleteAssuranceById(Long assuranceId);

  }
