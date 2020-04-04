/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.domain.relationship;

import com.trinity.dev.redsos.config.IdGenerator;
import com.trinity.dev.redsos.domain.Person;
import com.trinity.dev.redsos.domain.Product;
import com.trinity.dev.redsos.domain.Service;
import java.util.UUID;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author oiprado
 */
@RelationshipEntity("HAS")
public class ProductRelationship {

    @Id
    @GeneratedValue(strategy = IdGenerator.class)
    private UUID id;
    @StartNode
    private Service service;
    @EndNode
    private Product product;

    public ProductRelationship() {
    }

    public ProductRelationship(Service service, Product product) {
        this.id = UUID.randomUUID();
        this.service = service;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
