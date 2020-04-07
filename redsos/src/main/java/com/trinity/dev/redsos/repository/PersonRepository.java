/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.repository;

/**
 *
 * @author oiprado
 */
import com.trinity.dev.redsos.domain.Person;
import java.util.List;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

//@Repository
public interface PersonRepository extends Neo4jRepository<Person, String> {

    
    public Person findByName(String name);
    
    @Query("MATCH(p:Person { profileName: {profileName} }) return p")
    public Person findByProfileName(@Param("profileName") String profileName);
    
    public Person findOneByGuid(String guid);
    
    @Query("MATCH(p:Person { guid: {guid} }) RETURN p")
    public Person findByGuid(@Param("guid") String guid);
    
    @Query("MATCH(p:Person { guid: {guid} }) RETURN p")
    public Person getPersonToFollow(final@Param("guid") String guid);
    
    @Query("MATCH(p:Person)-[:CREATE]->(s:Service) where s.guid = {guid} return p")
    public Set<Person> getCreatePersonByService(String guid);
    
    @Query("MATCH(p:Person)-[:ATTEND]->(s:Service) where s.guid = {guid} return p")
    public Set<Person> getAttendPersonByService(String guid);
    
}