package com.example.AssuranceAuto.servicesImpl;

import com.example.AssuranceAuto.dtos.AssuranceDTO;
import com.example.AssuranceAuto.dtos.mappers.FromDOToDTO;
import com.example.AssuranceAuto.entities.Assurance;
import com.example.AssuranceAuto.repositories.AssuranceRepository;
import com.example.AssuranceAuto.services.AssuranceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** * Assurance Service ** */
@Service
public class AssuranceServiceImpl implements AssuranceService {
  @Autowired private AssuranceRepository assuranceRepository;
  @Autowired private FromDOToDTO fromDoToDTO;

  /**
   * @param assurance the assurance object to be added
   * @return AssuranceDTO
  */
  @Override
  public AssuranceDTO addAssurance(Assurance assurance) {
    final Assurance savedAssurance = assuranceRepository.save(assurance);

    return fromDoToDTO.mapAssurance(savedAssurance);
  }

  /**
   * @param assurance the assurance object to be updated
   * @return AssuranceDTO
  */
  @Override
  public AssuranceDTO updateAssurance(Assurance assurance) {
    final Assurance updatedAssurance = assuranceRepository.save(assurance);

    return fromDoToDTO.mapAssurance(updatedAssurance);
  }

  /**
   *
   * @return List<AssuranceDTO>
  */
  public List<AssuranceDTO> getAllAssurances() {
    List<Assurance> assurances = assuranceRepository.findAll();
    List<AssuranceDTO> assuranceDTOs = new ArrayList<>();
    assurances.forEach(
        assurance -> {
          AssuranceDTO assurancesDTO = fromDoToDTO.mapAssurance(assurance);
          assuranceDTOs.add(assurancesDTO);
        });

    return assuranceDTOs;
  }

  /**
   * @param assuranceNumber to search for assurances
   * @return List<AssuranceDTO>
  */
  @Override
  public List<AssuranceDTO> searchAssuranceByAssuranceNumber(String assuranceNumber) {
    final List<Assurance> assurances = assuranceRepository.findByAssuranceNumber(assuranceNumber);
    List<AssuranceDTO> assuranceDTOS = new ArrayList<>();
    assurances.forEach(
        assurance -> {
          AssuranceDTO assuranceDTO = fromDoToDTO.mapAssurance(assurance);
          assuranceDTOS.add(assuranceDTO);
        });

    return assuranceDTOS;
  }

    /**
     * Retrieves an assurance by its ID.
     *
     * @param assuranceId the ID of the assurance to retrieve
     * @return the AssuranceDTO corresponding to the assurance, or null if the assurance does not exist
     */

    @Override
    public AssuranceDTO getAssuranceById(Long assuranceId) {
        final Assurance assurance = assuranceRepository.findById(assuranceId).orElse(null);
        if (assurance != null) {

            return fromDoToDTO.mapAssurance(assurance);
        } else {

            return null;
        }
    }

    /**
     * Deletes an assurance by its ID.
     *
     * @param assuranceId the ID of the assurance to delete
     * @return true if the assurance was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteAssuranceById(Long assuranceId) {
        final Assurance assurance = assuranceRepository.findById(assuranceId).orElse(null);
        if (assurance != null) {
            assuranceRepository.delete(assurance);

            return true;
        } else {

            return false;
        }
    }
}
