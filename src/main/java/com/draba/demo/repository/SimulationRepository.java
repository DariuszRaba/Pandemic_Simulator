package com.draba.demo.repository;

import com.draba.demo.dto.SimplifiedSimulationDTO;
import com.draba.demo.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {

    @Query(value = "Select new com.draba.demo.dto.SimplifiedSimulationDTO(m.id,m.name) From com.draba.demo.model.Simulation m")
    List<SimplifiedSimulationDTO> getAllSimulationNames();
}
