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
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

//@Repository
public interface PersonRepository extends Neo4jRepository<Person, String> {

    
    public Person findByName(String name);
    
    public Person findOneByGuid(String guid);
    
    @Query("MATCH(p:Person { guid: {guid} }) RETURN p")
    public Person findByGuid(@Param("guid") String guid);
    
    @Query("MATCH(p:Person { guid: {guid} }) RETURN p")
    public Person getPersonToFollow(final@Param("guid") String guid);
    
//    public Person findRelationship();
    
    @Query("MATCH (p:Entity { guid: {own} })-[r:FOLLOW_TO]->(a:Entity { guid: {profile} } ) DELETE r")
    public void unfollow(@Param("own") String own, @Param("profile") String profile);
}