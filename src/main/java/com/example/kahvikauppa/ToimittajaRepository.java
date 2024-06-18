package com.example.kahvikauppa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToimittajaRepository extends JpaRepository<Toimittaja, Long> {
    Toimittaja findByNimi(String nimi);
}
