package com.example.AssuranceAuto.servicesImpl;

import com.example.AssuranceAuto.dtos.AutoDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Auto;
import com.example.AssuranceAuto.repositories.AutoRepository;
import com.example.AssuranceAuto.services.AutoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** * Auto Service ** */
@Service
public class AutoServiceImpl implements AutoService {
  @Autowired private AutoRepository autoRepository;
  @Autowired private FromDOToDTO fromDoToDTO;

  /**
   * @param auto the auto object to be added
   * @return AutoDTO
   */
  public AutoDTO addAuto(Auto auto) {
    final Auto savedAuto = autoRepository.save(auto);

    return fromDoToDTO.mapAuto(savedAuto);
  }

  /**
   * @param auto the auto object to be updated
   * @return AutoDTO
   */
  public AutoDTO updateAuto(Auto auto) {
    final Auto updateAuto = autoRepository.save(auto);

    return fromDoToDTO.mapAuto(updateAuto);
  }

  /** @return List<AutoDTO> */
  public List<AutoDTO> getAllAutos() {
    List<Auto> autos = autoRepository.findAll();
    List<AutoDTO> autosDTOs = new ArrayList<>();
    autos.forEach(
        auto -> {
          AutoDTO autoDTO = fromDoToDTO.mapAuto(auto);
          autosDTOs.add(autoDTO);
        });

    return autosDTOs;
  }

  /**
   * @param model to search for autos
   * @return List<AutoDTO>
   */
  public List<AutoDTO> searchAutoByModel(String model) {
    List<Auto> autos = autoRepository.findByModel(model);
    List<AutoDTO> autosDTOs = new ArrayList<>();
    autos.forEach(
        auto -> {
          AutoDTO autoDTO = fromDoToDTO.mapAuto(auto);
          autosDTOs.add(autoDTO);
        });

    return autosDTOs;
  }

  public List<AutoDTO> searchRegistrationNumber(String registrationNumber) {
    List<Auto> autos = autoRepository.findByRegistrationNumber(registrationNumber);
    List<AutoDTO> autosDTOs = new ArrayList<>();
    autos.forEach(
        auto -> {
          AutoDTO autoDTO = fromDoToDTO.mapAuto(auto);
          autosDTOs.add(autoDTO);
        });

    return autosDTOs;
  }

  /**
   * Retrieves an assurance by its ID.
   *
   * @param autoId the ID of the auto to retrieve
   * @return the auto corresponding to the auto, or null if the auto does not exist
   */
  @Override
  public AutoDTO getAutoById(Long autoId) {
    final Auto auto = autoRepository.findById(autoId).orElse(null);
    if (auto != null) {

      return fromDoToDTO.mapAuto(auto);
    } else {

      return null;
    }
  }

  /**
   * Deletes an auto by its ID.
   *
   * @param autoId the ID of the auto to delete
   * @return true if the auto was deleted successfully, false otherwise
   */
  @Override
  public boolean deleteAutoById(Long autoId) {
    final Auto auto = autoRepository.findById(autoId).orElse(null);
    if (auto != null) {
      autoRepository.delete(auto);

      return true;
    } else {

      return false;
    }
  }
}
