/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.repository;

import com.trinity.dev.redsos.domain.Service;
import java.util.List;
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
    
    @Query("MATCH(p:Service { status: {status} }) RETURN p")
    public List<Service> getServicesByStatus(@Param("status") String status);
    
}
