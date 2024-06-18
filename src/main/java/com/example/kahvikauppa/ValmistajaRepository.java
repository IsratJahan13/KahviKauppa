package com.example.kahvikauppa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ValmistajaRepository extends JpaRepository<Valmistaja, Long> {
    Valmistaja findByNimi(String nimi);
}
