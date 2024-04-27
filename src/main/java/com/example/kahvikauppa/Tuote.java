package com.example.kahvikauppa;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
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
    private String kuvaus;
    private BigDecimal hinta;
    private String tuotekuva;

    @ManyToOne
    private Osasto osasto;
    @ManyToOne
    private Toimittaja toimittaja;
    @ManyToOne
    private Valmistaja valmistaja;

    public void setToimittaja(Optional<Toimittaja> toimittaja2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setToimittaja'");
    }
}
