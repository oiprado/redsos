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
import com.trinity.dev.redsos.domain.Device;
import com.trinity.dev.redsos.domain.Person;
import java.util.List;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

//@Repository
public interface PersonRepository extends Neo4jRepository<Person, String> {

    
    public Person findByName(String name);
    
    @Query("MATCH(p:Person { profileName: {profileName} }) RETURN p")
    public Person findByProfileName(@Param("profileName") String profileName);
    
    @Query("MATCH(p:Person { profileName: {profileName} })-[r:USE]->(d:Device) RETURN p as person, COLLECT(d) as devices")
    public PersonData findByProfileName2(@Param("profileName") String profileName);
    
    public Person findOneByGuid(String guid);
    
    @Query("MATCH(p:Person { guid: {guid} })-[r:USE]->(d:Device) RETURN p")
    public Person findByGuid(@Param("guid") String guid);
    
    @Query("MATCH(p:Person { guid: {guid} }) RETURN p")
    public Person getPersonToFollow(final@Param("guid") String guid);
    
    @Query("MATCH(p:Person)-[:CREATE]->(s:Service) where s.guid = {guid} return p")
    public Set<Person> getCreatePersonByService(String guid);
    
    @Query("MATCH(p:Person)-[:ATTEND]->(s:Service) where s.guid = {guid} return p")
    public Set<Person> getAttendPersonByService(String guid);
    
    @Query("MATCH(p:Person { guid: {guid} })-[r:USE]->(d:Device) RETURN d as devices")
    public List<Device> getDevicesByProfile(@Param("guid") String guid);
    
    @QueryResult
    public class PersonData {
        Person person;
        Set<Device> devices;

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public Set<Device> getDevices() {
            return devices;
        }

        public void setDevices(Set<Device> devices) {
            this.devices = devices;
        }
        
        
    }
    
}