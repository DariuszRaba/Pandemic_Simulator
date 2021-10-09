package com.draba.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Population {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int infected;
    private int exposedPersons;
    private int deadPersons;
    private int healers;
    @ManyToOne
    @JoinColumn(name = "simulation_id",nullable = false)
    private Simulation simulation;

    public Population(int infected, int exposedPersons, int deadPersons, int healers) {
        this.infected = infected;
        this.exposedPersons = exposedPersons;
        this.deadPersons = deadPersons;
        this.healers = healers;
    }
}
