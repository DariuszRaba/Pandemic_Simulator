package com.draba.demo.util;

import com.draba.demo.dto.PopulationDTO;
import com.draba.demo.model.Population;

public class PopulationToPopulationDTO {
    public PopulationDTO convert(Population population){
        return new PopulationDTO(population.getInfected(),
                population.getExposedPersons(),
                population.getDeadPersons(),
                population.getHealers());

    }
}
