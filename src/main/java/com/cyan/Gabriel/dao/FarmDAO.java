package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmDAO extends JpaRepository<Farm, String> {

    Farm save(Farm farm);

    Farm findByCode(String code);

    List<Farm> findAll();
}
