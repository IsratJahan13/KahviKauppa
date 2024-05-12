package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Osasto extends AbstractPersistable<Long> {
    private String nimi;
    private Long osastoIDP;

    @OneToMany(mappedBy = "osasto", cascade = CascadeType.ALL)
    List<Tuote> tuotteet = new ArrayList<>();
}
