/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;


/**
 *
 * @author oiprado
 */
@Configuration
public class Neo4jConfigurer {

    @Value("${data.neo4j.username}")
    private String username;
    @Value("${data.neo4j.password}")
    private String password;
    @Value("${data.neo4j.uri}")
    private String uri;
    @Autowired
    private Environment environment;

    private final Logger log = LoggerFactory.getLogger(Neo4jConfigurer.class);

   @PostConstruct
    public void showVaraibles() {

       log.debug(String.format("NEO4J_USER=%s",username));
       log.debug(String.format("NEO4J_PASSWORD=%s",password));
       log.debug(String.format("NEO4J_URI=%s",uri));

   }
}
