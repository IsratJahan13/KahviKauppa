package com.example.kahvikauppa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OsastoRepository extends JpaRepository<Osasto, Long> {
    Osasto findByOsastoIDP(Long osastoIDP);

    Osasto findByNimi(String nimi);
}
