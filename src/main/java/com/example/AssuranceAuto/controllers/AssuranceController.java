package com.example.AssuranceAuto.controllers;

import com.example.AssuranceAuto.controllers.mappers.AssuranceMapper;
import com.example.AssuranceAuto.controllers.requests.AssuranceRequest;
import com.example.AssuranceAuto.controllers.responses.AssuranceResponse;
import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.servicesImpl.AssuranceServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** Assurance Controller */
@Validated
@RestController
public class AssuranceController {
  @Autowired AssuranceServiceImpl assuranceServiceImpl;
  @Autowired AssuranceMapper assuranceMapper;

  @CrossOrigin("*")
  @GetMapping("/assurances")
  public AssuranceResponse getAssurances(
      @RequestBody(required = false) @Valid AssuranceRequest assuranceRequest) {
    List<AssuranceDTO> assuranceDTOS;
    if (assuranceRequest != null && !(assuranceRequest.getKeyword().isBlank())) {
      assuranceDTOS = assuranceServiceImpl.searchAssuranceByAssuranceNumber(assuranceRequest.getKeyword());
    } else {
      assuranceDTOS = assuranceServiceImpl.getAllAssurances();
    }

    return assuranceMapper.fromAssuranceDTOToAssurance(assuranceDTOS);
  }

  @CrossOrigin("*")
  @PostMapping("/assurances")
  public AssuranceDTO addAssurance(@RequestBody @Valid Assurance assurance) {

    return assuranceServiceImpl.addAssurance(assurance);
  }

  @PutMapping("/assurances")
  public AssuranceDTO updateAssurance(@RequestBody @Valid Assurance assurance) {

    return assuranceServiceImpl.updateAssurance(assurance);
  }
  @CrossOrigin("*")
  @GetMapping("/assurances/{id}")
  public AssuranceDTO findAutoById(@PathVariable("id") Long autoId) {

    return assuranceServiceImpl.getAssuranceById(autoId);
  }

  @CrossOrigin("*")
  @DeleteMapping("/assurances/{id}")
  public boolean deleteAuto(@PathVariable("id") Long id) {

    return assuranceServiceImpl.deleteAssuranceById(id);
  }
}
