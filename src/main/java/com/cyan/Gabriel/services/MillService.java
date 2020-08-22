package com.cyan.Gabriel.services;

import com.cyan.Gabriel.dao.MillDAO;
import com.cyan.Gabriel.model.Harvest;
import com.cyan.Gabriel.model.Mill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MillService {

    private final MillDAO millDAO;

    @Autowired
    public MillService(MillDAO millDAO) {
        this.millDAO = millDAO;
    }

    public Mill saveMill(Mill mill) {

        // Validacoes
        if (millDAO.findByName(mill.getName()) != null) {
            throw new InternalError("Mill already registered.");
        }

        return millDAO.save(mill);
    }

    public Mill addHarvest(Mill mill, Harvest harvest){

        Mill newMill = mill.addHarvest(harvest);

        return millDAO.save(newMill);
    }

    public Mill findByName(String name){
        return millDAO.findByName(name);
    }

    public List<Mill> findAllMills(){
        return millDAO.findAll();
    }

    public void deleteMill(String name){
        millDAO.deleteByName(name);
    }
}
