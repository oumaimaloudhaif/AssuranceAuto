package com.example.AssuranceAuto.servicesImpl;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.repositories.AssuranceRepository;
import com.example.AssuranceAuto.services.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*** Assurance Service ***/
@Service
public class AssuranceServiceImpl implements AssuranceService {
    @Autowired private AssuranceRepository assuranceRepository;
    @Autowired private FromDOToDTO fromDoToDTO;
    public AssuranceDTO addAssurance(Assurance assurance ){
        final Assurance savedAssurance = assuranceRepository.save(assurance);
        return fromDoToDTO.MapAssurance(savedAssurance);
    }
    public AssuranceDTO updateAuto(Assurance assurance){
        final Assurance updatedAssurance = assuranceRepository.save(assurance);
        return fromDoToDTO.MapAssurance(updatedAssurance);
    }
    public List<AssuranceDTO> getAllAssurances(){
        List<Assurance> assurances=assuranceRepository.findAll();
        List<AssuranceDTO> assuranceDTOs=new ArrayList<>();
        assurances.forEach(assurance -> {
            AssuranceDTO assurancesDTO=fromDoToDTO.MapAssurance(assurance);
            assuranceDTOs.add(assurancesDTO);
        });
        return assuranceDTOs;
    }
    public List<AssuranceDTO> searchAssuranceByAssuranceNumber(String assuranceNumber){
       final List<Assurance> assurances =assuranceRepository.findByAssuranceNumber(assuranceNumber);
        List<AssuranceDTO> assuranceDTOS = new ArrayList<>();
        assurances.forEach(
                assurance -> {
                    AssuranceDTO assuranceDTO = fromDoToDTO.MapAssurance(assurance);
                    assuranceDTOS.add(assuranceDTO);
                });
        return assuranceDTOS;
    }
}
