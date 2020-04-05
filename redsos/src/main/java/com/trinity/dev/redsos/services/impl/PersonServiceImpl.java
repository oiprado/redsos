/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.services.impl;

import com.trinity.dev.redsos.domain.Person;
import com.trinity.dev.redsos.repository.PersonRepository;
import com.trinity.dev.redsos.services.PersonService;
import com.trinity.dev.redsos.util.Util;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author oiprado
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private Util util;
    
    
    @Override
    public Person create(com.trinity.dev.redsos.dto.Person person) {
        Person create = util.convertTo(person, Person.class);
        create.setGuid(UUID.randomUUID().toString());
        personRepository.save(create);
        return create;
    }
    
}
