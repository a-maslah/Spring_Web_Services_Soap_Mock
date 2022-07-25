package com.example.demospringws;

import com.example.demospringws.entites.Compte;
import com.example.demospringws.entites.reponsitory.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DemospringwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemospringwsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository){
        return args -> {
            compteRepository.save(new Compte(null,7000,new Date(),"active"));
            compteRepository.save(new Compte(null,9000,new Date(),"active"));
            compteRepository.save(new Compte(null,5000,new Date(),"blocked"));

        };
    }
}
