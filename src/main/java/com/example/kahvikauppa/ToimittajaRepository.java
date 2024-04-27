package com.example.kahvikauppa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ToimittajaRepository extends JpaRepository<Toimittaja, Long> {

}
