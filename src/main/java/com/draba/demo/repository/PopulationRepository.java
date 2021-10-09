package com.draba.demo.repository;

import com.draba.demo.model.Population;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends CrudRepository<Population, Long> {
}
