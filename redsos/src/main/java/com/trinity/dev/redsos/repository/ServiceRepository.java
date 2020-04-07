/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.repository;

import com.trinity.dev.redsos.domain.Service;
import java.util.List;
import java.util.Map;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author oiprado
 */
@Repository
public interface ServiceRepository extends Neo4jRepository<Service, String>{
    
    @Query("MATCH(p:Service { guid: {guid} }) RETURN p")
    public Service findByGuid(@Param("guid") String guid);
    
    @Query
    (
        "MATCH(s:Service { status: {status} }) RETURN s\n" +
        "UNION\n" +
        "MATCH(c:Person { guid: {user} })-[cr:CREATE]->(s:Service) RETURN s"
    )
    public List<Service> getServicesByStatus(@Param("status") String status, @Param("user") String user);
    
    @Query
    (
            
        "MATCH (s:Service { guid: {serviceGuid} } ) \n" +
        "OPTIONAL MATCH (c:Person  { guid: {personGuid} } )-[cr:CREATE]->(s)\n" +
        "OPTIONAL MATCH (a:Person  { guid: {personGuid} } )-[ar:ATTEND]->(s)\n" +
        "return \n" +
        "CASE \n" +
        " WHEN count(cr) = 1  THEN 'can-cancel-service'\n" +
        " ELSE 'cannot-cancel-service'\n" +
        "END AS cancelService, \n" +
        "CASE\n" +
        " WHEN count(ar) = 1  THEN 'can-cancel-attend'\n" +
        " ELSE 'cannot-cancel-attend'\n" +
        "END AS cancelAttend,\n" +
        "CASE\n" +
        " WHEN count(ar) > 1 OR ar.status = 'ACTIVE'  THEN 'can-service-attended'\n" +
        " ELSE 'cannot-service-attended'\n" +
        "END AS canAttended,\n" +
        "CASE\n" +
        " WHEN s.status = 'DELIVERED' THEN 'can-accept-delivery'\n" +
        " ELSE 'cannot-accept-attend'\n" +
        "END AS acceptDelivery"
    )
    public Iterable<Map<String,Object>> getPermitActions(@Param("serviceGuid") String serviceGuid, @Param("personGuid") String personGuid);
}
