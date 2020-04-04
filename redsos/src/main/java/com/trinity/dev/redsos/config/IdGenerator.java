/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.config;

import java.util.UUID;
import org.neo4j.ogm.id.IdStrategy;

/**
 *
 * @author oiprado
 */
public class IdGenerator implements IdStrategy {

    @Override
    public Object generateId(Object o) {
        
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
   
}