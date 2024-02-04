package com.example.AssuranceAuto.repositories;

import com.example.AssuranceAuto.entities.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*** Auto Repository ***/
@Repository
public interface AutoRepository extends JpaRepository<Auto,Long> {
    List<Auto> findByModel(String model);

    List<Auto> findByRegistrationNumber(String registrationNumber);
}
