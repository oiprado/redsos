/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.rest;

import com.trinity.dev.redsos.component.NotificationComponent;
import com.trinity.dev.redsos.dto.Person;
import com.trinity.dev.redsos.services.PersonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oiprado
 */
@RestController
@RequestMapping("/api/person")
public class PersonResource {
    
    @Autowired
    private PersonService personService;
    @Autowired
    private NotificationComponent notificationComponent;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Person person) {
        try {
            return new ResponseEntity(personService.create(person), HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity sendMessage(@RequestParam("token") String token){
//        List<String> tokens = new ArrayList<>();
//        tokens.add(token);
//        
//        notificationComponent.send(tokens, "Comida para hoy. Gracias", "Hola soy Oscar necesito yuda", "1");
//        return new ResponseEntity(HttpStatus.OK);
//    }
//    
//    @RequestMapping(value = "batch" ,method = RequestMethod.GET)
//    public ResponseEntity batch(){
//        notificationComponent.sendBatch("Comida para hoy. Gracias", "Hola soy Oscar necesito yuda", "1");
//        return new ResponseEntity(HttpStatus.OK);
//    }
    
}
