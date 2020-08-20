package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.Mill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MillDAO extends JpaRepository<Mill, String> {

    Mill save (Mill mill);

    Mill findByName (String name);

    List<Mill> findAll();
}
