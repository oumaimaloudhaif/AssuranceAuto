package com.example.AssuranceAuto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AssuranceAutoApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssuranceAutoApplication.class, args);
  }
}
