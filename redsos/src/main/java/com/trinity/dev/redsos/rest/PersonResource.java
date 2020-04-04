/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.rest;

import com.trinity.dev.redsos.dto.Person;
import com.trinity.dev.redsos.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Person person) {
        try {
            personService.create(person);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
}
