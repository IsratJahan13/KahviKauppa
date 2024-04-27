package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Valmistaja extends AbstractPersistable<Long> {
    private String nimi;
    private String url;

    @OneToMany(mappedBy = "valmistaja")
    List<Tuote> tuotteet = new ArrayList<>();
}
