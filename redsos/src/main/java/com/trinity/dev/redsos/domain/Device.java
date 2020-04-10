/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 *
 * @author oiprado
 */
@NodeEntity(label = "Device")
public class Device {

    @Id
    @Property("guid")
    private String guid;
    @Property("platform")
    private String platform;
    @Property("token")
    private String token;

    public Device() {
    }

    public Device(String guid, String platform, String token) {
        this.guid = guid;
        this.platform = platform;
        this.token = token;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
