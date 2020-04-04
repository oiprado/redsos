/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.util;

import com.trinity.dev.redsos.domain.Person;
import com.trinity.dev.redsos.domain.Service;
import com.trinity.dev.redsos.repository.PersonRepository;
import com.trinity.dev.redsos.repository.ServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author oiprado
 */
@Component
public class Util {
    
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public <T, J> J convertTo(T object, Class clazz) {
        
        J convert = (J) modelMapper.map(object, clazz);
        
        return convert;
    }
    
    public Person getPersonByGuid(String guid) {
        return personRepository.findByGuid(guid);
    }
    
    public Service getServiceByGuid(String guid) {
        return serviceRepository.findByGuid(guid);
    }
    
}
