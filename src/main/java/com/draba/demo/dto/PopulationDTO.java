package com.draba.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PopulationDTO {
    private int infected;
    private int exposedPersons;
    private int deadPersons;
    private int healers;
}
