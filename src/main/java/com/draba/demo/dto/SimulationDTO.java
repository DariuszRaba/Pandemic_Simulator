package com.draba.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimulationDTO {
    private String name;
    private int population;
    private int infected;
    private float virusReproductionRate;
    private float mortalityRate;
    private int recoveryTimeFrame;
    private int deathTimeFrame;
    private int simulationTime;
    private List<PopulationDTO> populationList;
}
