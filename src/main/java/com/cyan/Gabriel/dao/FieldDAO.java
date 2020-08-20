package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDAO extends JpaRepository<Field, String> {

    Field save(Field field);

    Field findByCode(String code);

    List<Field> findAll();
}
