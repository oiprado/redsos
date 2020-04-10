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
    
    @Query("MATCH(s:Service { guid: {guid} }) RETURN s")
    public Service findByGuid(@Param("guid") String guid);
    
    @Query
    (
        "MATCH(s:Service { status: {status} }) RETURN s\n" +
        "UNION\n" +
        "MATCH(c:Person { guid: {user} })-[cr:CREATE]->(s:Service)\n" +
        "WHERE s.status <> 'ACCEPTED' AND s.status <> 'CANCELLED' \n" +
        "RETURN s \n" +
        "UNION\n" +
        "MATCH(c:Person { guid: {user} })-[cr:ATTEND]->(s:Service)\n" +
        "WHERE s.status <> 'ACCEPTED' AND s.status <> 'CANCELLED' \n" +
        "RETURN s"
    )
    public List<Service> getServicesByStatus(@Param("status") String status, @Param("user") String user);
    
    @Query
    (
            
        "MATCH (s:Service { guid: {serviceGuid} } ) \n" +
        "OPTIONAL MATCH (c:Person  { guid: {personGuid} } )-[cr:CREATE]->(s)\n" +
        "OPTIONAL MATCH (a:Person  { guid: {personGuid} } )-[ar:ATTEND]->(s)\n" +
        "return \n" +
        "CASE \n" +
        " WHEN count(cr) = 1 and s.status = 'NEW' THEN true \n" +
        " ELSE false \n" +
        "END AS canCancelService, \n" +
        "CASE\n" +
        " WHEN count(ar) = 1 and ar.status = 'ACTIVE' and s.status <> 'DELIVERED' THEN true\n" +
        " ELSE false\n" +
        "END AS canCancelAttend,\n" +
        "CASE\n" +
        " WHEN (count(ar) = 0 and count(cr) = 0) THEN true\n" +
        " ELSE false\n" +
        "END AS canAttended,\n" +
        "CASE\n" +
        " WHEN (count(ar) = 1 and s.status <> 'DELIVERED') THEN true\n" +
        " ELSE false\n" +
        "END AS canDeliver,\n" +
        "CASE\n" +
        " WHEN count(cr) = 1 and s.status = 'DELIVERED' THEN true\n" +
        " ELSE false\n" +
        "END AS canAcceptDelivery"
    )
    public Iterable<Map<String,Object>> getPermitActions(@Param("serviceGuid") String serviceGuid, @Param("personGuid") String personGuid);
}
