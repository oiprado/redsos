/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.domain.relationship;

import com.trinity.dev.redsos.config.IdGenerator;
import com.trinity.dev.redsos.domain.Device;
import com.trinity.dev.redsos.domain.Person;
import java.util.Date;
import java.util.UUID;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

/**
 *
 * @author oiprado
 */
@RelationshipEntity("USE")
public class UseDeviceRelarionship {

    @Id
    @GeneratedValue(strategy = IdGenerator.class)
    private UUID id;
    @StartNode
    private Person person;
    @EndNode
    private Device device;
    @DateLong
    private Date linkedOn;

    public UseDeviceRelarionship() {
    }

    public UseDeviceRelarionship(Person person, Device device) {
        this.person = person;
        this.device = device;
        this.linkedOn = new Date();
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getLinkedOn() {
        return linkedOn;
    }

    public void setLinkedOn(Date linkedOn) {
        this.linkedOn = linkedOn;
    }

}
