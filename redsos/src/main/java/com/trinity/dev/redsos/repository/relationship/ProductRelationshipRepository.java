/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.repository.relationship;

import com.trinity.dev.redsos.domain.relationship.ProductRelationship;
import java.io.Serializable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oiprado
 */
@Repository
public interface ProductRelationshipRepository extends Neo4jRepository<ProductRelationship, Serializable>{
    
}
