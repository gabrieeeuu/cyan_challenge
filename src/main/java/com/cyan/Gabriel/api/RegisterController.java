package com.cyan.Gabriel.api;

import com.cyan.Gabriel.model.*;
import com.cyan.Gabriel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;

@RequestMapping("/api")
@RestController
public class RegisterController {

    private final UserService userService;
    private final MillService millService;
    private final HarvestService harvestService;
    private final FarmService farmService;
    private final FieldService fieldService;

    private TokenFilter tokenFilter = new TokenFilter();

    @Autowired
    public RegisterController(UserService userService, MillService millService, HarvestService harvestService, FarmService farmService, FieldService fieldService) {
        this.userService = userService;
        this.millService = millService;
        this.harvestService = harvestService;
        this.farmService = farmService;
        this.fieldService = fieldService;
    }

    @PostMapping(value="/user")
    @ResponseBody
    public ResponseEntity<User> registerUser(@RequestBody User user){

        User newUser = userService.saveUser(user);

        if (newUser == null){
            throw new InternalError ("User couldn't be registered.");
        }

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value="/auth/user")
    @ResponseBody
    public ResponseEntity<Response> getUserName(@RequestHeader("Authorization") String token) 
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        User authUser = userService.findUserByEmail(userEmail);

        Response response = new Response(authUser.getName());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private class Response {
        private String name;

        public Response(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
    }

    @PostMapping(value="/mill")
    @ResponseBody
    public ResponseEntity<Mill> registerMill(@RequestBody Mill mill,
                                             @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Mill newMill = millService.saveMill(mill);

        if (newMill == null){
            throw new InternalError ("Mill couldn't be registered.");
        }

        return new ResponseEntity<>(newMill, HttpStatus.CREATED);
    }

    @PutMapping(value="/mill/harv/{name}/{id}")
    @ResponseBody
    public ResponseEntity<Mill> addHarvestToMill(@PathVariable("name") String millName,
                                                 @PathVariable("id") long harvestId,
                                                 @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Mill mill = millService.findByName(millName);

        if (mill == null){
            throw new InternalError ("This Mill is not Registered.");
        }

        Harvest harvest = harvestService.findById(harvestId);

        if (harvest == null){
            throw new InternalError ("This Harvest is not Registered.");
        }

        Mill newMill = millService.addHarvest(mill, harvest);

        if (newMill == null){
            throw new InternalError ("Mill couldn't be updated.");
        }

        return new ResponseEntity<>(newMill, HttpStatus.OK);
    }

    @GetMapping(value="/mills")
    @ResponseBody
    public ResponseEntity<List<Mill>> getAllMill(@RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        List<Mill> mills = millService.findAllMills();
        return new ResponseEntity<>(mills, HttpStatus.OK);
    }

    @GetMapping(value="/mill/{name}")
    @ResponseBody
    public ResponseEntity<Mill> findMillByName(@PathVariable("name") String name,
                                               @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Mill mill = millService.findByName(name);

        if (mill == null){
            throw new InternalError ("This Mill is not registered.");
        }

        return new ResponseEntity<>(mill, HttpStatus.FOUND);
    }

    @DeleteMapping(value="/mill/{name}")
    @Transactional
    public void deleteMill(@PathVariable("name") String name,
                           @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        millService.deleteMill(name);
    }

    @PostMapping(value="/harv")
    @ResponseBody
    public ResponseEntity<Harvest> registerHarvest(@RequestBody Harvest harvest,
                                                   @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Harvest newHarvest = harvestService.saveHarvest(harvest);

        if (newHarvest == null){
            throw new InternalError ("Harvest couldn't be registered.");
        }
        return new ResponseEntity<>(newHarvest, HttpStatus.CREATED);
    }

    @PutMapping(value="/harv/farm/{idH}/{idF}")
    @ResponseBody
    public ResponseEntity<Harvest> addFarmToHarvest(@PathVariable("idH") long idH,
                                                    @PathVariable("idF") long idF,
                                                    @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Harvest harvest = harvestService.findById(idH);

        if(harvest == null){
            throw new InternalError("This Harvest is not Registered.");
        }

        Farm farm = farmService.findById(idF);

        if(farm == null){
            throw new InternalError("This Farm is not Registered.");
        }

        Harvest newHarvest = harvestService.addFarm(harvest, farm);

        if (newHarvest == null){
            throw new InternalError ("Harvest couldn't be registered.");
        }
        return new ResponseEntity<>(newHarvest, HttpStatus.CREATED);

    }

    @GetMapping(value="/harvs")
    @ResponseBody
    public ResponseEntity<List<Harvest>> getAllHarvest(@RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        List<Harvest> harvests = harvestService.findAllHarvests();
        return new ResponseEntity<>(harvests, HttpStatus.OK);
    }

    @GetMapping(value="/harv/{id}")
    @ResponseBody
    public ResponseEntity<Harvest> findHarvById(@PathVariable("id") Long id,
                                               @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Harvest harv = harvestService.findById(id);

        if (harv == null){
            throw new InternalError ("This Harvest is not registered.");
        }

        return new ResponseEntity<>(harv, HttpStatus.FOUND);
    }

    @DeleteMapping(value="/harv/{id}")
    @Transactional
    public void deleteHarv(@PathVariable("id") Long id,
                           @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        harvestService.deleteHarv(id);
    }

    @PostMapping(value="/farm")
    @ResponseBody
    public ResponseEntity<Farm> registerFarm(@RequestBody Farm farm,
                                             @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Farm newFarm = farmService.saveFarm(farm);

        if (newFarm == null){
            throw new InternalError ("Farm couldn't be registered.");
        }
        return new ResponseEntity<>(newFarm, HttpStatus.CREATED);
    }

    @PutMapping(value="/farm/field/{idFarm}/{idField}")
    @ResponseBody
    public ResponseEntity<Farm> addFieldToFarm(@PathVariable("idFarm") long idFarm,
                                               @PathVariable("idField") long idField,
                                               @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Farm farm = farmService.findById(idFarm);

        if(farm == null){
            throw new InternalError("This Farm is not Registered.");
        }

        Field field = fieldService.findById(idField);

        if(field == null){
            throw new InternalError("This Field is not Registered.");
        }

        Farm newFarm = farmService.addField(farm, field);

        if (newFarm == null){
            throw new InternalError ("Farm couldn't be registered.");
        }
        return new ResponseEntity<>(newFarm, HttpStatus.CREATED);
    }

    @GetMapping(value="/farms")
    @ResponseBody
    public ResponseEntity<List<Farm>> getAllFarm(@RequestHeader("Authorization") String token) throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        List<Farm> farms = farmService.findAllFarms();
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping(value="/farm/{id}")
    @ResponseBody
    public ResponseEntity<Farm> findFarmByCode(@PathVariable("id") long id,
                                                  @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Farm farm = farmService.findById(id);

        if (farm == null){
            throw new InternalError ("This Farm is not registered.");
        }

        return new ResponseEntity<>(farm, HttpStatus.FOUND);
    }

    @DeleteMapping(value="/farm/{id}")
    @Transactional
    public void deleteFarm(@PathVariable("id") Long id,
                           @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        farmService.deleteById(id);
    }

    @PostMapping(value="/field")
    @ResponseBody
    public ResponseEntity<Field> registerField(@RequestBody Field field,
                                               @RequestHeader("Authorization") String token) throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Field newField = fieldService.saveField(field);

        if (newField == null){
            throw new InternalError ("Field couldn't be registered.");
        }
        return new ResponseEntity<>(newField, HttpStatus.CREATED);
    }

    @GetMapping(value="/fields")
    @ResponseBody
    public ResponseEntity<List<Field>> getAllField(@RequestHeader("Authorization") String token) throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        List<Field> fields = fieldService.findAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping(value="/field/{id}")
    @ResponseBody
    public ResponseEntity<Field> findFieldByCode(@PathVariable("id") long id,
                                                  @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        Field field = fieldService.findById(id);

        if (field == null){
            throw new InternalError ("This Field is not registered.");
        }

        return new ResponseEntity<>(field, HttpStatus.FOUND);
    }

    @DeleteMapping(value="/field/{id}")
    @Transactional
    public void deleteField(@PathVariable("id") Long id,
                           @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("You're not logged as a registered User. Please Logout.");
        }

        fieldService.deleteById(id);
    }

}

