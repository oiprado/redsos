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
import java.util.Map;

/**
 *
 * @author oiprado
 */
public interface RedSOSService {
    
    public com.trinity.dev.redsos.domain.Service createService(Service service, Person person);
    
    public List<com.trinity.dev.redsos.domain.Service> availableServices(String user);
    
    public com.trinity.dev.redsos.domain.Service attendService(Service service, Person person, Date deliveryDate, String timeRange);
    
    public com.trinity.dev.redsos.domain.Service deliveredService(Service service);
    
    public com.trinity.dev.redsos.domain.Service acceptedService(Service service);
    
    public com.trinity.dev.redsos.domain.Service cancelAttend(Service service, Person person);
    
    public com.trinity.dev.redsos.domain.Service cancelService(Service service, Person person);
    
    public Map<String,Object> getPermitActions(String serviceGuid, String userGuid);
    
}
