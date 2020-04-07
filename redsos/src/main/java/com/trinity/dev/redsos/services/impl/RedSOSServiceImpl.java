/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.services.impl;

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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        
        return create;
    }

    @Override
    public Service attendService(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person, Date deliveryDate, String timeRange) {
        Service create = getServiceById(service.getGuid());
        Person attendBy = getPersonById(person.getGuid());

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
        return create;
    }

    private Person getPersonById(String guid) {
        return util.getPersonByGuid(guid);
    }

    private Service getServiceById(String guid) {
        return util.getServiceByGuid(guid);
    }

    @Override
    public Service deliveredService(com.trinity.dev.redsos.dto.Service service) {
        Service create = getServiceById(service.getGuid());
        create.setStatus("DELIVERED");
        serviceRepository.save(create);
        return create;
    }

    @Override
    public Service acceptedService(com.trinity.dev.redsos.dto.Service service) {
        Service create = getServiceById(service.getGuid());
        create.setStatus("ACCEPTED");
        serviceRepository.save(create);
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
        return create;
    }

    @Override
    public Service cancelService(com.trinity.dev.redsos.dto.Service service, com.trinity.dev.redsos.dto.Person person) {

        Service create = getServiceById(service.getGuid());
        create.setStatus("CANCELLED");

        serviceRepository.save(create);
        createRelationshipRepository.cancelRelationship(
            service.getGuid(),
            person.getGuid()
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

}
