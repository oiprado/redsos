/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author oiprado
 */
@NodeEntity(label = "Person")
public class Person {

    @Id
    @Index(unique = true)
    private String guid;
    @Property("profileName")
    private String profileName;
    @Property("name")
    private String name;
    @Index(unique = true)
    @Property("mail")
    private String mail;
    @Property("address")
    private String address;
    @Property("active")
    private boolean active;
    @JsonIgnoreProperties("endNode")
    @Relationship(type = "CREATE")
    private Set<Service> createServices;
    @JsonIgnoreProperties("endNode")
    @Relationship(type = "ATTEND")
    private Set<Service> attendServices;

    public Person() {
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Service> getCreateServices() {
        return createServices;
    }

    public void setCreateServices(Set<Service> createServices) {
        this.createServices = createServices;
    }

    public Set<Service> getAttendServices() {
        return attendServices;
    }

    public void setAttendServices(Set<Service> attendServices) {
        this.attendServices = attendServices;
    }

}
