package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmDAO extends JpaRepository<Farm, Long> {

    Farm save(Farm farm);

    Farm findById(long id);

    Farm findByName(String name);

    List<Farm> findAll();

    void deleteById(long id);
}
