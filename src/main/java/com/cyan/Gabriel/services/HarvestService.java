package com.cyan.Gabriel.services;

import com.cyan.Gabriel.dao.HarvestDAO;
import com.cyan.Gabriel.model.Farm;
import com.cyan.Gabriel.model.Harvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {

    private final HarvestDAO harvestDAO;

    @Autowired
    public HarvestService(HarvestDAO harvestDAO) {
        this.harvestDAO = harvestDAO;
    }

    public Harvest saveHarvest(Harvest harvest){

        //Validacoes
        if(harvestDAO.findById(harvest.getId()) != null){
            throw new InternalError("Harvest already registered.");
        }

        return harvestDAO.save(harvest);
    }

    public Harvest addFarm(Harvest harvest, Farm farm){

        Harvest newHarvest = harvest.addFarm(farm);

        return harvestDAO.save(newHarvest);
    }

    public Harvest findById(long id){
        return harvestDAO.findById(id);
    }

    public List<Harvest> findAllHarvests(){
        return harvestDAO.findAll();
    }

    public void deleteHarv(long id){
        harvestDAO.deleteById(id);
    }
}
