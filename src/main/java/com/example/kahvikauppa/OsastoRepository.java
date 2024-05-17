package com.example.kahvikauppa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OsastoRepository extends JpaRepository<Osasto, Long> {
    Osasto findByOsastoIDP(Long osastoIDP);
}
