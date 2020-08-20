package com.cyan.Gabriel.services;

import com.cyan.Gabriel.dao.FieldDAO;
import com.cyan.Gabriel.model.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldDAO fieldDAO;

    @Autowired
    public FieldService(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }

    public Field saveField(Field field){

        if(fieldDAO.findByCode(field.getCode()) != null) {
            throw new InternalError("Field already registered.");
        }

        return fieldDAO.save(field);
    }

    public Field findByCode(String code){
        return fieldDAO.findByCode(code);
    }

    public List<Field> findAllFields(){
        return fieldDAO.findAll();
    }
}
