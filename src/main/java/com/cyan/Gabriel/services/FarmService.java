package com.cyan.Gabriel.services;

import com.cyan.Gabriel.dao.FarmDAO;
import com.cyan.Gabriel.model.Farm;
import com.cyan.Gabriel.model.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

    private final FarmDAO farmDAO;

    @Autowired
    public FarmService(FarmDAO farmDAO) {
        this.farmDAO = farmDAO;
    }

    public Farm saveFarm(Farm farm) {

        if(farmDAO.findByCode(farm.getCode()) != null){
            throw new InternalError("Farm already registered.");
        }

        return farmDAO.save(farm);
    }

    public Farm addField(Farm farm, Field field){

        Farm newFarm = farm.addField(field);

        return farmDAO.save(newFarm);
    }

    public Farm findByCode(String code){
        return farmDAO.findByCode(code);
    }

    public List<Farm> findAllFarms(){
        return farmDAO.findAll();
    }
}
