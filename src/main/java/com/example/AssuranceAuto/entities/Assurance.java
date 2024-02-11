package com.example.AssuranceAuto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** Assurance Entity */
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
public class Assurance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long assurance_Id;

  private String assuranceNumber;

  @OneToMany(mappedBy = "assurance")
  @JsonIgnore
  private List<Auto> autos;
  // This annotation of Data JPA allows to insert the Date of creation of the data
  @CreatedDate private Date created;
  // This annotation of Data JPA allows to insert the last Date of modification of the data
  @LastModifiedDate private Date updated;
}
