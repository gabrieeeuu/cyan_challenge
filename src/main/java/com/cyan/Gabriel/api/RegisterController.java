package com.cyan.Gabriel.api;

import com.cyan.Gabriel.model.*;
import com.cyan.Gabriel.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @PostMapping(value="/user")
    @ResponseBody
    public ResponseEntity<User> registerUser(@RequestBody User user){

        User newUser = userService.saveUser(user);

        if (newUser == null){
            throw new InternalError ("Something went wrong. User is Null.");
        }

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value="/user")
    @ResponseBody
    public ResponseEntity<Response> getUserName(@RequestHeader("Authorization") String token) 
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        User authUser = userService.findUserByEmail(userEmail);

        Response response = new Response(authUser.getName());

        return new ResponseEntity<Response>(response, HttpStatus.CREATED);
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
            throw new InternalError("Token does not belong to a registered User");
        }

        Mill newMill = millService.saveMill(mill);

        if (newMill == null){
            throw new InternalError ("Something went wrong. Mill is Null.");
        }

        return new ResponseEntity<Mill>(newMill, HttpStatus.CREATED);
    }

    @PutMapping(value="/mill/harv/{name}/{code}")
    @ResponseBody
    public ResponseEntity<Mill> addHarvestToMill(@PathVariable("name") String millName,
                                                 @PathVariable("code") String harvestCode,
                                                 @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Mill mill = millService.findByName(millName);

        if (mill == null){
            throw new InternalError ("Something went wrong. This Mill is not Registered.");
        }

        Harvest harvest = harvestService.findByCode(harvestCode);

        if (harvest == null){
            throw new InternalError ("Something went wrong. This Harvest is not Registered.");
        }

        Mill newMill = millService.addHarvest(mill, harvest);

        if (newMill == null){
            throw new InternalError ("Something went wrong. Mill is Null.");
        }

        return new ResponseEntity<Mill>(newMill, HttpStatus.OK);
    }

    @GetMapping(value="/mills")
    @ResponseBody
    public ResponseEntity<List<Mill>> getAllMill(@RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        List<Mill> mills = millService.findAllMills();
        return new ResponseEntity<List<Mill>>(mills, HttpStatus.OK);
    }

    @GetMapping(value="/mill/{name}")
    @ResponseBody
    public ResponseEntity<Mill> findMillByName(@PathVariable("name") String name,
                                               @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Mill mill = millService.findByName(name);

        if (mill == null){
            throw new InternalError ("Something went wrong. This Mill is not registered.");
        }

        return new ResponseEntity<Mill>(mill, HttpStatus.FOUND);
    }

    @DeleteMapping(value="/mill/{name}")
    @Transactional
    public void deleteMill(@PathVariable("name") String name,
                           @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
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
            throw new InternalError("Token does not belong to a registered User");
        }

        Harvest newHarvest = harvestService.saveHarvest(harvest);

        if (newHarvest == null){
            throw new InternalError ("Something went wrong. Harvest is Null.");
        }
        return new ResponseEntity<Harvest>(newHarvest, HttpStatus.CREATED);
    }

    @PutMapping(value="/harv/farm/{codeh}/{codef}")
    @ResponseBody
    public ResponseEntity<Harvest> addFarmToHarvest(@PathVariable("codeh") String codeh,
                                                    @PathVariable("codef") String condef,
                                                    @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

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
    public ResponseEntity<List<Harvest>> getAllHarvest(@RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        List<Harvest> harvests = harvestService.findAllHarvests();
        return new ResponseEntity<List<Harvest>>(harvests, HttpStatus.OK);
    }

    @GetMapping(value="/harv/{code}")
    @ResponseBody
    public ResponseEntity<Harvest> findHarvByCode(@PathVariable("code") String code,
                                               @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Harvest harv = harvestService.findByCode(code);

        if (harv == null){
            throw new InternalError ("Something went wrong. This Harvest is not registered.");
        }

        return new ResponseEntity<Harvest>(harv, HttpStatus.FOUND);
    }

    @PostMapping(value="/farm")
    @ResponseBody
    public ResponseEntity<Farm> registerFarm(@RequestBody Farm farm,
                                             @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Farm newFarm = farmService.saveFarm(farm);

        if (newFarm == null){
            throw new InternalError ("Something went wrong. Farm is Null.");
        }
        return new ResponseEntity<Farm>(newFarm, HttpStatus.CREATED);
    }

    @PutMapping(value="/farm/field/{codefa}/{codefi}")
    @ResponseBody
    public ResponseEntity<Farm> addFieldToFarm(@PathVariable("codefa") String codefa,
                                               @PathVariable("codefi") String codefi,
                                               @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

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
    public ResponseEntity<List<Farm>> getAllFarm(@RequestHeader("Authorization") String token) throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        List<Farm> farms = farmService.findAllFarms();
        return new ResponseEntity<List<Farm>>(farms, HttpStatus.OK);
    }

    @GetMapping(value="/farm/{code}")
    @ResponseBody
    public ResponseEntity<Farm> findFarmByCode(@PathVariable("code") String code,
                                                  @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Farm farm = farmService.findByCode(code);

        if (farm == null){
            throw new InternalError ("Something went wrong. This Farm is not registered.");
        }

        return new ResponseEntity<Farm>(farm, HttpStatus.FOUND);
    }

    @PostMapping(value="/field")
    @ResponseBody
    public ResponseEntity<Field> registerField(@RequestBody Field field,
                                               @RequestHeader("Authorization") String token) throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Field newField = fieldService.saveField(field);

        if (newField == null){
            throw new InternalError ("Something went wrong. Field is Null.");
        }
        return new ResponseEntity<Field>(newField, HttpStatus.CREATED);
    }

    @GetMapping(value="/fields")
    @ResponseBody
    public ResponseEntity<List<Field>> getAllField(@RequestHeader("Authorization") String token) throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        List<Field> fields = fieldService.findAllFields();
        return new ResponseEntity<List<Field>>(fields, HttpStatus.OK);
    }

    @GetMapping(value="/field/{code}")
    @ResponseBody
    public ResponseEntity<Field> findFieldByCode(@PathVariable("code") String code,
                                                  @RequestHeader("Authorization") String token)
            throws ServletException {

        String userEmail = tokenFilter.getLogin(token);

        if(userService.findUserByEmail(userEmail) == null){
            throw new InternalError("Token does not belong to a registered User");
        }

        Field field = fieldService.findByCode(code);

        if (field == null){
            throw new InternalError ("Something went wrong. This Field is not registered.");
        }

        return new ResponseEntity<Field>(field, HttpStatus.FOUND);
    }

}

