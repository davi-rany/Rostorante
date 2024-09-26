package com.example.rostorante.repository;

import com.example.rostorante.model.Garcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarcomRepository extends JpaRepository<Garcom, Long> {

}
