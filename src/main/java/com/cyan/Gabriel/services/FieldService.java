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

        if(fieldDAO.findByLatitudeAndLongitude(field.getLatitude(), field.getLongitude()) != null) {
            throw new InternalError("Field already registered.");
        }

        return fieldDAO.save(field);
    }

    public Field findById(long id){
        return fieldDAO.findById(id);
    }

    public List<Field> findAllFields(){
        return fieldDAO.findAll();
    }

    public void deleteById(long id){
        fieldDAO.deleteById(id);
    }
}
