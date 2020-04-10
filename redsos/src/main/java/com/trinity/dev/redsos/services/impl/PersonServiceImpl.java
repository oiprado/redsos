/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.services.impl;

import com.trinity.dev.redsos.domain.Device;
import com.trinity.dev.redsos.domain.Person;
import com.trinity.dev.redsos.domain.relationship.UseDeviceRelarionship;
import com.trinity.dev.redsos.repository.DeviceRepository;
import com.trinity.dev.redsos.repository.PersonRepository;
import com.trinity.dev.redsos.repository.relationship.UseDeviceRelationshipRepository;
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
    private DeviceRepository deviceRepository;
    @Autowired
    private UseDeviceRelationshipRepository deviceRelationshipRepository;
    @Autowired
    private Util util;
    
    
    @Override
    public Person create(com.trinity.dev.redsos.dto.Person person) {
        
        Person exist = personRepository.findByProfileName(person.getProfileName());
        Device device = util.convertTo(person.getDevice(), Device.class);
        
        if(exist != null) {
            
            linkDivice(exist, device);
            return exist;
        }
            
        Person create = util.convertTo(person, Person.class);
        
        create.setGuid(UUID.randomUUID().toString());
        
        
        personRepository.save(create);
        deviceRepository.save(device);
        
        linkDivice(create, device);
        
        return create;
    }
    
    private void linkDivice(Person person, Device device) {
        device.setGuid(UUID.randomUUID().toString());
        deviceRelationshipRepository.save(new UseDeviceRelarionship(person, device));
    }
    
}
