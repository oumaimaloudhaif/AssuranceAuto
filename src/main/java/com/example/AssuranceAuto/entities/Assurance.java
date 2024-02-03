package com.example.AssuranceAuto.entities;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assurance_Id;
    private String assuranceNumber;
    @OneToMany(mappedBy = "assurance")
    @JsonIgnore
    private ArrayList<Auto> autos;
    // This annotation of Data JPA allows to insert the Date of creation of the data
    @CreatedDate private Date created;
    // This annotation of Data JPA allows to insert the last Date of modification of the data
    @LastModifiedDate private Date updated;
}