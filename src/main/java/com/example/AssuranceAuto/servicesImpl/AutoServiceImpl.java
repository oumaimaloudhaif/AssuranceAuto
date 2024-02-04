package com.example.AssuranceAuto.servicesImpl;

import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDoToDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.example.AssuranceAuto.repositories.AutoRepository;
import com.example.AssuranceAuto.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***  Auto Service ***/
@Service
public class AutoServiceImpl implements AutoService {
    @Autowired private AutoRepository autoRepository;
    @Autowired private FromDoToDTO fromDoToDTO;
    public AutoDTO addAuto(Auto auto){
        final Auto savedAuto = autoRepository.save(auto);
        return fromDoToDTO.MapAuto(savedAuto);
    }
    public AutoDTO updateAuto(Auto auto){
        final Auto updateAuto = autoRepository.save(auto);
        return fromDoToDTO.MapAuto(updateAuto);
    }
    public List<AutoDTO> getAllAutos(){
        List<Auto> autos=autoRepository.findAll();
        List<AutoDTO> autosDTOs=new ArrayList<>();
        autos.forEach(auto -> {
            AutoDTO autoDTO=fromDoToDTO.MapAuto(auto);
            autosDTOs.add(autoDTO);
        });
        return autosDTOs;
    }
    public List<AutoDTO> searchAutoByModel(String model){
        List<Auto> autos=autoRepository.findByModel(model);
        List<AutoDTO> autosDTOs=new ArrayList<>();
        autos.forEach(auto -> {
            AutoDTO autoDTO=fromDoToDTO.MapAuto(auto);
            autosDTOs.add(autoDTO);
        });
        return autosDTOs;
    }
    public List<AutoDTO> searchRegistrationNumber(String registrationNumber){
        List<Auto> autos=autoRepository.findByRegistrationNumber(registrationNumber);
        List<AutoDTO> autosDTOs=new ArrayList<>();
        autos.forEach(auto -> {
            AutoDTO autoDTO=fromDoToDTO.MapAuto(auto);
            autosDTOs.add(autoDTO);
        });
        return autosDTOs;
    }
}
