/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;

/**
 *
 * @author oiprado
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "guid",
    "message",
    "description",
    "publishedDate",
    "type",
    "status",
    "longitude",
    "latitude",
    "tags",
    "products",
    "delivery"
})
public class ServiceResponse extends Service {

    private Map<String, Object> delivery;

    public Map<String, Object> getDelivery() {
        return delivery;
    }

    public void setDelivery(Map<String, Object> delivery) {
        this.delivery = delivery;
    }

}
