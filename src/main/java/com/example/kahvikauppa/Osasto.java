package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Osasto parent;

    @OneToMany(mappedBy = "osastoIDP")
    private List<Osasto> children = new ArrayList<>();

    @OneToMany(mappedBy = "osasto")
    List<Tuote> tuotteet = new ArrayList<>();
}
