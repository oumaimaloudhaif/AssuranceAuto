package com.example.AssuranceAuto;

import com.example.AssuranceAuto.config.SecretConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AssuranceAutoApplication {

  public static void main(String[] args) {
    SecretConfig.loadEnv();
    SpringApplication.run(AssuranceAutoApplication.class, args);
  }
}
