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
import org.neo4j.ogm.annotation.typeconversion.DateLong;

/**
 *
 * @author oiprado
 */
@RelationshipEntity("CREATE")
public class CreateRelationship {

    @Id
    @GeneratedValue(strategy = IdGenerator.class)
    private UUID id;
    @StartNode
    private Person person;
    @EndNode
    private Service service;
    @DateLong
    @Property("publishedDate")
    private Date publishedDate;
    @DateLong
    @Property("receivedDate")
    private Date receivedDate;
    @Property("status")
    private String status;

    public CreateRelationship() {
    }

    public CreateRelationship(UUID id, Person person, Service service) {
        this.id = id;
        this.person = person;
        this.service = service;
        this.status = "NEW";
        this.publishedDate = new Date();
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

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
