package com.example.kahvikauppa;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VipAsiakas extends AbstractPersistable<Long> {
    private String etunimi;
    private String sukunimi;
    private String sahkopostiOsoite;
}
