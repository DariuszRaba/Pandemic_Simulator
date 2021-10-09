package com.draba.demo.util;

import com.draba.demo.dto.PopulationDTO;
import com.draba.demo.model.Population;

public class PopulationDtoToPopulation {

    public Population convert(PopulationDTO populationDTO){
        return new Population(populationDTO.getInfected(),
                populationDTO.getExposedPersons(),
                populationDTO.getDeadPersons(),
                populationDTO.getHealers());
    }
}
