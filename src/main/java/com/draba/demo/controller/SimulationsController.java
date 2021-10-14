package com.draba.demo.controller;

import com.draba.demo.dto.SimplifiedSimulationDTO;
import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.UserCreationForm;
import com.draba.demo.service.SimulationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/simulation")
public class SimulationsController {

    private final SimulationService simulationService;

    @PostMapping(value = "/simulate",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SimulationDTO makeSimulation(@RequestBody UserCreationForm userCreationForm) {
        return simulationService.makeSimulation(userCreationForm);
    }

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<SimplifiedSimulationDTO> getAllSimulation(){
        return simulationService.getAllSimulation();
    }

    @GetMapping(value = "/{id}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public SimulationDTO getTheSimulation(@PathVariable String id){
        return simulationService.getSimulationByID(id);
    }


    @PostMapping(value = "/add",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSimulation(@RequestBody UserCreationForm userCreationForm) throws JsonProcessingException {
        simulationService.saveSimulation(userCreationForm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeSimulation(@PathVariable String id){
        simulationService.remove(id);
    }

}
