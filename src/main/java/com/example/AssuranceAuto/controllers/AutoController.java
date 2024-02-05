package com.example.AssuranceAuto.controllers;

import com.example.AssuranceAuto.controllers.mappers.AutoMapper;
import com.example.AssuranceAuto.controllers.requests.AutoRequest;
import com.example.AssuranceAuto.controllers.responses.AutoResponse;
import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.example.AssuranceAuto.servicesImpl.AutoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*** Auto Controller ***/
@Validated
@RestController
public class AutoController {
    @Autowired AutoServiceImpl autoServiceImpl;
    @Autowired AutoMapper autoMapper;
    @GetMapping("/autos")
    public AutoResponse getAutos(@RequestBody (required = false) AutoRequest autoRequest){
        List<AutoDTO> autoDTOS;
        if(autoRequest!=null && !(autoRequest.getKeyword().isBlank()))
            autoDTOS=autoServiceImpl.searchRegistrationNumber(autoRequest.getKeyword());
        else
            autoDTOS=autoServiceImpl.getAllAutos();
        return autoMapper.fromAutoDTOToAuto(autoDTOS);
    }
    @PostMapping("/autos")
    public AutoDTO addAuto(@RequestBody @Valid Auto auto){
        return autoServiceImpl.addAuto(auto);
    }
    @PutMapping("/autos")
    public AutoDTO updateAuto(@RequestBody @Valid Auto auto){
        return autoServiceImpl.updateAuto(auto);
    }
}
