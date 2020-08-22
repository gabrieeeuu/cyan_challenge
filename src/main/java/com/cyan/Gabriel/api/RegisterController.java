package com.cyan.Gabriel.api;

import com.cyan.Gabriel.model.*;
import com.cyan.Gabriel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RegisterController {

    private final MillService millService;
    private final HarvestService harvestService;
    private final FarmService farmService;
    private final FieldService fieldService;

    @Autowired
    public RegisterController(MillService millService, HarvestService harvestService, FarmService farmService, FieldService fieldService) {
        this.millService = millService;
        this.harvestService = harvestService;
        this.farmService = farmService;
        this.fieldService = fieldService;
    }

    @PostMapping(value="/mill")
    @ResponseBody
    public ResponseEntity<Mill> registerMill(@RequestBody Mill mill) {



        Mill newMill = millService.saveMill(mill);

        if (newMill == null){
            throw new InternalError ("Something went wrong. Mill is Null.");
        }
        return new ResponseEntity<Mill>(newMill, HttpStatus.CREATED);
    }

    @PutMapping(value="/mill/harv/{name}/{code}")
    @ResponseBody
    public ResponseEntity<Mill> addHarvestToMill(@PathVariable("name") String millName, @PathVariable("code") String harvestCode){

        Mill mill = millService.findByName(millName);

        Harvest harvest = harvestService.findByCode(harvestCode);

        Mill newMill = millService.addHarvest(mill, harvest);

        if (newMill == null){
            throw new InternalError ("Something went wrong. Mill is Null.");
        }

        return new ResponseEntity<Mill>(newMill, HttpStatus.OK);
    }

    @GetMapping(value="/mills")
    @ResponseBody
    public ResponseEntity<List<Mill>> getAllMill(){
        List<Mill> mills = millService.findAllMills();
        return new ResponseEntity<List<Mill>>(mills, HttpStatus.OK);
    }

    @GetMapping(value="/mill/{name}")
    @ResponseBody
    public ResponseEntity<Mill> findMillByName(@PathVariable("name") String name){

        Mill newMill = millService.findByName(name);

        if (newMill == null){
            throw new InternalError ("Something went wrong. Mill is Null.");
        }

        return new ResponseEntity<Mill>(newMill, HttpStatus.FOUND);
    }

    @DeleteMapping(value="/mill/{name}")
    @Transactional
    public void deleteMill(@PathVariable("name") String name){
        millService.deleteMill(name);
    }

    @PostMapping(value="/harv")
    @ResponseBody
    public ResponseEntity<Harvest> registerHarvest(@RequestBody Harvest harvest){

        Harvest newHarvest = harvestService.saveHarvest(harvest);

        if (newHarvest == null){
            throw new InternalError ("Something went wrong. Harvest is Null.");
        }
        return new ResponseEntity<Harvest>(newHarvest, HttpStatus.CREATED);
    }

    @PutMapping(value="/harv/farm/{codeh}/{codef}")
    @ResponseBody
    public ResponseEntity<Harvest> addFarmToHarvest(@PathVariable("codeh") String codeh, @PathVariable("codef") String condef){

        Harvest harvest = harvestService.findByCode(codeh);

        Farm farm = farmService.findByCode(condef);

        Harvest newHarvest = harvestService.addFarm(harvest, farm);

        if (newHarvest == null){
            throw new InternalError ("Something went wrong. Harvest is Null.");
        }
        return new ResponseEntity<Harvest>(newHarvest, HttpStatus.CREATED);

    }

    @GetMapping(value="/harvs")
    @ResponseBody
    public ResponseEntity<List<Harvest>> getAllHarvest(){
        List<Harvest> harvests = harvestService.findAllHarvests();
        return new ResponseEntity<List<Harvest>>(harvests, HttpStatus.OK);
    }

    @PostMapping(value="/farm")
    @ResponseBody
    public ResponseEntity<Farm> registerFarm(@RequestBody Farm farm){

        Farm newFarm = farmService.saveFarm(farm);

        if (newFarm == null){
            throw new InternalError ("Something went wrong. Farm is Null.");
        }
        return new ResponseEntity<Farm>(newFarm, HttpStatus.CREATED);
    }

    @PutMapping(value="/farm/field/{codefa}/{codefi}")
    @ResponseBody
    public ResponseEntity<Farm> addFieldToFarm(@PathVariable("codefa") String codefa, @PathVariable("codefi") String codefi){

        Farm farm = farmService.findByCode(codefa);

        Field field = fieldService.findByCode(codefi);

        Farm newFarm = farmService.addField(farm, field);

        if (newFarm == null){
            throw new InternalError ("Something went wrong. Farm is Null.");
        }
        return new ResponseEntity<Farm>(newFarm, HttpStatus.CREATED);
    }

    @GetMapping(value="/farms")
    @ResponseBody
    public ResponseEntity<List<Farm>> getAllFarm(){
        List<Farm> farms = farmService.findAllFarms();
        return new ResponseEntity<List<Farm>>(farms, HttpStatus.OK);
    }

    @PostMapping(value="/field")
    @ResponseBody
    public ResponseEntity<Field> registerField(@RequestBody Field field){

        Field newField = fieldService.saveField(field);

        if (newField == null){
            throw new InternalError ("Something went wrong. Field is Null.");
        }
        return new ResponseEntity<Field>(newField, HttpStatus.CREATED);
    }

    @GetMapping(value="/fields")
    @ResponseBody
    public ResponseEntity<List<Field>> getAllField(){
        List<Field> fields = fieldService.findAllFields();
        return new ResponseEntity<List<Field>>(fields, HttpStatus.OK);
    }

}

