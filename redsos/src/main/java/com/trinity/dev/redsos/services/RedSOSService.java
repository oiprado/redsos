/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.services;

import com.trinity.dev.redsos.dto.Person;
import com.trinity.dev.redsos.dto.Service;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oiprado
 */
public interface RedSOSService {
    
    public void createService(Service service, Person person);
    
    public List<com.trinity.dev.redsos.domain.Service> availableServices();
    
    public void attendService(Service service, Person person, Date deliveryDate, String timeRange);
    
    public void deliveredService(Service service);
    
    public void acceptedService(Service service);
    
    public void cancelAttend(Service service, Person person);
    
    public void cancelService(Service service, Person person);
    
}
