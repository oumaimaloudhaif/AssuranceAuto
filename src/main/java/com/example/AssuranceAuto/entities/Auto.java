package com.example.AssuranceAuto.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auto_Id;
    private String registrationNumber;
    private String model;
    @ManyToOne
    @JoinColumn(name = "assurance_id")
    private Assurance assurance;
    // This annotation of Data JPA allows to insert the Date of creation of the data
    @CreatedDate
    private Date created;
    // This annotation of Data JPA allows to insert the last Date of modification of the data
}