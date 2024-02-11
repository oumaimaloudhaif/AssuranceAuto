package com.example.AssuranceAuto.repositories;

import com.example.AssuranceAuto.entities.Auto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Auto Repository */
@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
  List<Auto> findByModel(String model);

  List<Auto> findByRegistrationNumber(String registrationNumber);
}
