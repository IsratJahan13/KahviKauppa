package com.example.kahvikauppa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TuoteRepository extends JpaRepository<Tuote, Long> {
    List<Tuote> findByOsastoOsastoIDP(Long osastoIDP);

    List<Tuote> findAllByOsasto(Osasto osasto);

    @Query("SELECT t FROM Tuote t WHERE t.osasto IN :osastot")
    Page<Tuote> findByOsastoIn(@Param("osastot") List<Osasto> osastot, Pageable pageable);

    // New method for search functionality by category
    List<Tuote> findByNimiContainingIgnoreCaseAndOsastoIn(String nimi, List<Osasto> osastot);

    @Query("SELECT t FROM Tuote t WHERE t.osasto IN :osastot ORDER BY "
            + "CASE WHEN :filterHinta = 'asc' THEN t.hinta END ASC, "
            + "CASE WHEN :filterHinta = 'desc' THEN t.hinta END DESC")
    Page<Tuote> findByHintaFilter(@Param("osastot") List<Osasto> osastot,
            @Param("filterHinta") String filterHinta,
            Pageable pageable);
}
