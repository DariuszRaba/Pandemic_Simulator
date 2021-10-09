package com.draba.demo.controller;

import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.Simulation;
import com.draba.demo.model.UserCreationForm;
import com.draba.demo.service.SimulationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("/simulation")
public class SimulationsController {

    private final SimulationService simulationService;

    @GetMapping(value = "/simulate", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SimulationDTO makeSimulation(@RequestBody UserCreationForm userCreationForm) {
        return simulationService.makeSimulation(userCreationForm);
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestBody
    public void saveSimulation(@RequestBody HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
//        Simulation simulation = mapper.readValue()
//        simulationService.saveSimulation(userCreationForm);
    }

}
