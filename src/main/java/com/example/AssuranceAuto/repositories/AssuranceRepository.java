package com.example.AssuranceAuto.repositories;

import com.example.AssuranceAuto.entities.Assurance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Assurance Repository */
@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
  List<Assurance> findByAssuranceNumber(String assuranceNumber);
}
