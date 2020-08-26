package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestDAO extends JpaRepository<Harvest, Long> {

    Harvest save(Harvest harvest);

    Harvest findById(long id);

    List<Harvest> findAll();

    void deleteById(Long id);
}
