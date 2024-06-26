package com.example.AssuranceAuto.controllers;

import com.example.AssuranceAuto.controllers.mappers.AutoMapper;
import com.example.AssuranceAuto.controllers.requests.AutoRequest;
import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.example.AssuranceAuto.servicesImpl.AutoServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** Auto Controller */
@Validated
@RestController
public class AutoController {
  @Autowired AutoServiceImpl autoServiceImpl;
  @Autowired AutoMapper autoMapper;

  @CrossOrigin("*")
  @GetMapping("/autos")
  public AutoResponse getAutos(
      @RequestParam(required = false) @Valid String keyword,
      @RequestBody(required = false) @Valid AutoRequest autoRequest) {
    List<AutoDTO> autoDTOS;
    if (keyword != null) {
      autoDTOS = autoServiceImpl.searchAutoByModel(keyword);
    } else {
      if (autoRequest != null && !(autoRequest.getKeyword().isBlank()))
        autoDTOS = autoServiceImpl.searchRegistrationNumber(autoRequest.getKeyword());
      else autoDTOS = autoServiceImpl.getAllAutos();
    }

    return autoMapper.fromAutoDTOToAuto(autoDTOS);
  }

  @CrossOrigin("*")
  @PostMapping("/autos")
  public AutoDTO addAuto(@RequestBody @Valid Auto auto) {


    return autoServiceImpl.addAuto(auto);
  }

  @CrossOrigin("*")
  @PutMapping("/autos")
  public AutoDTO updateAuto(@RequestBody @Valid Auto auto) {

    return autoServiceImpl.updateAuto(auto);
  }
  @GetMapping("/autos/{id}")
  public AutoDTO findAutoById(@PathVariable("id") Long autoId) {

    return autoServiceImpl.getAutoById(autoId);
  }

  @CrossOrigin("*")
  @DeleteMapping("/autos/{id}")
  public boolean deleteAuto(@PathVariable("id") Long id) {

    return autoServiceImpl.deleteAutoById(id);
  }

}
