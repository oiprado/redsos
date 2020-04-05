/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.domain.relationship;

import com.trinity.dev.redsos.config.IdGenerator;
import com.trinity.dev.redsos.domain.Person;
import com.trinity.dev.redsos.domain.Service;
import java.util.Date;
import java.util.UUID;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oiprado
 */
@RelationshipEntity("ATTEND")
public class AttendRelationship {

    @Id
    @GeneratedValue(strategy = IdGenerator.class)
    private UUID id;
    @StartNode
    private Person person;
    @EndNode
    private Service service;
    @Property("deliveryDate")
    private Date deliveryDate;
    @Property("timeRange")
    private String timeRange;
    @Property("status")
    private String status;

    public AttendRelationship(UUID id, Person person, Service service, Date deliveryDate, String timeRange) {
        this.person = person;
        this.service = service;
        this.deliveryDate = deliveryDate;
        this.id = id;
        this.timeRange = timeRange;
        this.status = "ACTIVE";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
