/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.repository.relationship;

import com.trinity.dev.redsos.domain.relationship.AttendRelationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oiprado
 */
@Repository
public interface AttendRelationshipRepository extends Neo4jRepository<AttendRelationship, Long>{
    
    @Query("MATCH (p:Person { guid: {person} } )-[r:ATTEND]->(s:Service { guid: {service} } ) RETURN r")
    public AttendRelationship getAttendRelationshipByGUIDs(
        final @Param("service") String service, 
        final @Param("person") String person
    );
}
