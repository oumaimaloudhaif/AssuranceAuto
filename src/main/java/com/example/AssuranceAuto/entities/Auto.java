package com.example.AssuranceAuto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Auto Entity */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class Auto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long auto_Id;

  private String registrationNumber;
  private String model;

  @ManyToOne
  @JoinColumn(name = "assurance_Id")
  private Assurance assurance;
  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;
  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;
}
