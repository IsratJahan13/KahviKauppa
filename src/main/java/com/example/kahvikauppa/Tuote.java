package com.example.kahvikauppa;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tuote extends AbstractPersistable<Long> {
    private String nimi;
    private BigDecimal hinta;
    @Lob
    private byte[] kuva;
    private String kuvaus;

    @ManyToOne
    private Osasto osasto;
    @ManyToOne
    private Toimittaja toimittaja;
    @ManyToOne
    private Valmistaja valmistaja;
}
