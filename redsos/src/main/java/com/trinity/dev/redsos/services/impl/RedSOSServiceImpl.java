/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.services.impl;

import com.trinity.dev.redsos.component.NotificationComponent;
import com.trinity.dev.redsos.domain.Device;
import com.trinity.dev.redsos.domain.Person;
import com.trinity.dev.redsos.domain.Product;
import com.trinity.dev.redsos.domain.Service;
import com.trinity.dev.redsos.domain.relationship.AttendRelationship;
import com.trinity.dev.redsos.domain.relationship.CreateRelationship;
import com.trinity.dev.redsos.domain.relationship.ProductRelationship;
import com.trinity.dev.redsos.repository.PersonRepository;
import com.trinity.dev.redsos.repository.ProductRepository;
import com.trinity.dev.redsos.repository.ServiceRepository;
import com.trinity.dev.redsos.repository.relationship.AttendRelationshipRepository;
import com.trinity.dev.redsos.repository.relationship.CreateRelationshipRepository;
import com.trinity.dev.redsos.repository.relationship.ProductRelationshipRepository;
import com.trinity.dev.redsos.services.RedSOSService;
import com.trinity.dev.redsos.util.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author oiprado
 */
@org.springframework.stereotype.Service
public class RedSOSServiceImpl implements RedSOSService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRelationshipRepository productRelationshipRepository;
    @Autowired
    private AttendRelationshipRepository attendRelationshipRepository;
    @Autowired
    private CreateRelationshipRepository createRelationshipRepository;
    @Autowired
    private NotificationComponent notificationComponent;
    
    @Autowired
    private Util util;

    @Override
    public Service createService(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person) {
        Service create = util.convertTo(service, Service.class);
        Person createBy = getPersonById(person.getGuid());

        create.setGuid(UUID.randomUUID().toString());
        create.setStatus("NEW");

        serviceRepository.save(create);
        createRelationshipRepository.save(
                new CreateRelationship(
                        UUID.randomUUID(),
                        createBy,
                        create
                )
        );

        if (service.getProducts() != null) {
            service.getProducts().forEach(product -> {
                Product item = util.convertTo(product, Product.class);
                productRepository.save(item);
                productRelationshipRepository.save(new ProductRelationship(create, item));
            });
        }
        
        sendBatchMessage(
            String.format("Hola, soy %s, necesito de tu ayuda.", createBy.getName()), 
            create.getMessage(),
            create.getGuid()
        );
        
        return create;
    }

    @Override
    public Service attendService(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person, Date deliveryDate, String timeRange) {
        Service create = getServiceById(service.getGuid());
        
        Person attendBy = getPersonById(person.getGuid());
        
        Set<Person> creates = personRepository.getCreatePersonByService(service.getGuid());
        
        List<String> devices = getDevices(creates.iterator().next().getGuid()).stream().map(x -> x.getToken()).collect(Collectors.toList());
        
        attendRelationshipRepository.save(
                new AttendRelationship(
                        UUID.randomUUID(),
                        attendBy,
                        create,
                        deliveryDate,
                        timeRange
                )
        );

        create.setStatus("ATTENDED");

        serviceRepository.save(create);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        
        sendMessage(
            devices, 
            String.format("Hola, soy %s, quiero ayudarte.", attendBy.getName()), 
            String.format("Te llevo tu solicitud a tu casa el día %s entre %s.", sdf.format(deliveryDate), timeRange), 
            service.getGuid()
        );
        
        return create;
    }

    private Person getPersonById(String guid) {
        return util.getPersonByGuid(guid);
    }
    
    @Override
    public Service getServiceWithChilds(String guid) {
    
        Service service = getServiceById(guid);
        
        service.setAttendPersons(
            personRepository.getAttendPersonByService(service.getGuid())
        );

        service.setCreatePersons(
            personRepository.getCreatePersonByService(service.getGuid())
        );
        
        return service;
        
    }

    
    private Service getServiceById(String guid) {
        return util.getServiceByGuid(guid);
    }

    @Override
    public Service deliveredService(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person) {
        
        Service create = getServiceById(service.getGuid());
        
        Set<Person> creates = personRepository.getCreatePersonByService(service.getGuid());
        
        List<String> devices = getDevices(creates.iterator().next().getGuid()).stream().map(x -> x.getToken()).collect(Collectors.toList());
        
        create.setStatus("DELIVERED");
        serviceRepository.save(create);
        sendMessage(
            devices, 
            String.format("Hola de nuevo."), 
            String.format("Espero poder aliviar un poco tu necesidad con esta atención"), 
            service.getGuid()
        );
        return create;
    }

    @Override
    public Service acceptedService(com.trinity.dev.redsos.dto.Service service) {
        Service create = getServiceById(service.getGuid());
        
        create.setStatus("ACCEPTED");
        serviceRepository.save(create);
        
        Set<Person> creates = personRepository.getAttendPersonByService(service.getGuid());
        
        List<String> devices = getDevices(creates.iterator().next().getGuid()).stream().map(x -> x.getToken()).collect(Collectors.toList());
        
        sendMessage(
            devices,
            String.format("Hola a TODOS."), 
            String.format("Me alegra mucho decir que mi solicitud fue atendida. GRACIAS."), 
            service.getGuid()
        );
        return create;
    }

    @Override
    public Service cancelAttend(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person) {

        Service create = getServiceById(service.getGuid());
        
        create.setStatus("NEW");

        serviceRepository.save(create);
        
        attendRelationshipRepository.cancelRelationship(
                service.getGuid(),
                person.getGuid()
        );
        
        sendBatchMessage( 
            String.format("Hola de nuevo."), 
            String.format("Lamento decirte que no es posible cumplir con esta tarea."), 
            service.getGuid()
        );
        
        return create;
    }

    @Override
    public Service cancelService(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person) {

        Service create = getServiceById(service.getGuid());
        Person user = getPersonById(person.getGuid());
        create.setStatus("CANCELLED");

        serviceRepository.save(create);
        
        createRelationshipRepository.cancelRelationship(
            service.getGuid(),
            person.getGuid()
        );
        
        Set<Person> creates = personRepository.getAttendPersonByService(service.getGuid());
        
        List<String> devices = getDevices(creates.iterator().next().getGuid()).stream().map(x -> x.getToken()).collect(Collectors.toList());
        
        sendMessage(
            devices,
            String.format("Hola soy %s", user.getName()),
            "Estoy muy agradecido con su solidaridad, en éste momento acabo de cancelar mi solicitud. Gracias", 
            create.getGuid()
        );
        
        return create;
    }

    @Override
    public List<Service> availableServices(String user) {

        List<Service> services = serviceRepository.getServicesByStatus("NEW", user);

        services.forEach(service -> {
                service.setAttendPersons(
                    personRepository.getAttendPersonByService(service.getGuid())
                );

                service.setCreatePersons(
                    personRepository.getCreatePersonByService(service.getGuid())
                );
            }
        );

        return services;
    }

    @Override
    public Map<String,Object> getPermitActions(String serviceGuid, String userGuid) {
        return serviceRepository.getPermitActions(serviceGuid, userGuid).iterator().next();
    }
    
    private void sendMessage(List<String> tokens, String title, String body, String guid) {
        notificationComponent.send(
            tokens, 
            body, 
            title, 
            guid
        );
    }
    
    private void sendBatchMessage(String title, String body, String guid) {
        notificationComponent.sendBatch(
            body,
            title,
            guid
        );
    }
    
    private List<Device> getDevices(String profileName){
        return personRepository.getDevicesByProfile(profileName);
    }

}
