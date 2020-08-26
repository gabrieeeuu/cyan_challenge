package com.cyan.Gabriel.dao;

import com.cyan.Gabriel.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDAO extends JpaRepository<Field, Long> {

    Field save(Field field);

    Field findById(long id);

    Field findByLatitudeAndLongitude(Double lat, Double log);

    List<Field> findAll();

    void deleteById(long id);
}
