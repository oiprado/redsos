/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.repository.relationship;

import com.trinity.dev.redsos.domain.relationship.AttendRelationship;
import com.trinity.dev.redsos.domain.relationship.CreateRelationship;
import java.io.Serializable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oiprado
 */
@Repository
public interface CreateRelationshipRepository extends Neo4jRepository<CreateRelationship, Serializable>{
    
    @Query("MATCH (n:Service { guid: {guid} })<-[r:CREATE]-() RETURN r")
    public CreateRelationship findByServiceGuid(@Param("guid") String guid);
    
    @Query("MATCH (p:Person { guid: {person} } )-[r:CREATE]->(s:Service { guid: {service} } ) SET r.status = 'CANCELLED' ")
    public AttendRelationship cancelRelationship( 
        final @Param("service") String service, 
        final @Param("person") String person
    );
}
