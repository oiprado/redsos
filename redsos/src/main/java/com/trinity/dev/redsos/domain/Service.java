/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.domain;

import java.util.Date;
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
@NodeEntity(label = "Service")
public class Service {

    @Id
    @Index(unique = true)
    private String guid;
    @Property("message")
    private String message;
    @Property("description")
    private String description;
    @Property("publishedDate")
    private Date publishedDate;
    @Property("type")
    private String type;
    @Property("status")
    private String status;
    @Property("latitude")
    private String latitude;
    @Property("longitude")
    private String longitude;
    @Property("tags")
    private String tags;
//    @JsonIgnoreProperties("endNode")
    @Relationship(type = "HAS")
    private Set<Product> hasProducts;
    @Relationship(type = "ATTEND", direction = Relationship.INCOMING)
    private Set<Person> attendPersons;
    @Relationship(type = "CREATE", direction = Relationship.INCOMING)
    private Set<Person> createPersons;

    public Service(String guid, String message, String description, Date publishedDate, String type, String status, String latitude, String longitude, String tags, Set<Product> hasProducts) {
        this.guid = guid;
        this.message = message;
        this.description = description;
        this.publishedDate = publishedDate;
        this.type = type;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
        this.hasProducts = hasProducts;
    }

    public Service() {
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<Product> getHasProducts() {
        return hasProducts;
    }

    public void setHasProducts(Set<Product> hasProducts) {
        this.hasProducts = hasProducts;
    }

    public Set<Person> getAttendPersons() {
        return attendPersons;
    }

    public void setAttendPersons(Set<Person> attendPersons) {
        this.attendPersons = attendPersons;
    }

    public Set<Person> getCreatePersons() {
        return createPersons;
    }

    public void setCreatePersons(Set<Person> createPersons) {
        this.createPersons = createPersons;
    }

}
