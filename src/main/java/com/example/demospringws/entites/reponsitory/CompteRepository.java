package com.example.demospringws.entites.reponsitory;

import com.example.demospringws.entites.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Long> {
}
