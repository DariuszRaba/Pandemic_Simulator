package com.draba.demo.repository;

import com.draba.demo.model.Simulation;
import org.springframework.data.repository.CrudRepository;

public interface SimulationRepository extends CrudRepository<Simulation, Long> {
}
