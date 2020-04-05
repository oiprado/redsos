/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.rest;

import com.trinity.dev.redsos.dto.AttendServiceRequest;
import com.trinity.dev.redsos.dto.CreateServiceRequest;
import com.trinity.dev.redsos.services.RedSOSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oiprado
 */
@RestController
@RequestMapping("/api/redsos")
public class RedSOSResource {

    @Autowired
    private RedSOSService redSOSService;
    
    @RequestMapping(value = "/available-services", method = RequestMethod.GET)
    public ResponseEntity availableServices() {

        try {
            return new ResponseEntity(redSOSService.availableServices(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody CreateServiceRequest createServiceRequest) {

        try {
            redSOSService.createService(
                createServiceRequest.getService(),
                createServiceRequest.getPerson()
            );
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/attend", method = RequestMethod.POST)
    public ResponseEntity attend(@RequestBody AttendServiceRequest attendServiceRequest) {
        try {
            redSOSService.attendService(
                attendServiceRequest.getService(),
                attendServiceRequest.getPerson(),
                attendServiceRequest.getDeliveryDate(),
                attendServiceRequest.getTimeRange()
            );
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_GATEWAY);
        }
    }
    
    @RequestMapping(value = "/delivered", method = RequestMethod.POST)
    public ResponseEntity delivered(@RequestBody com.trinity.dev.redsos.dto.Service service) {
        try {
            redSOSService.deliveredService(service);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex) {
           return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/received", method = RequestMethod.POST)
    public ResponseEntity received(@RequestBody com.trinity.dev.redsos.dto.Service service) {
        try {
            redSOSService.acceptedService(service);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex) {
           return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/cancel-attend", method = RequestMethod.POST)
    public ResponseEntity cancelAttend(@RequestBody CreateServiceRequest createServiceRequest) {
        try {
            redSOSService.cancelAttend(
                createServiceRequest.getService(),
                createServiceRequest.getPerson()
            );
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex) {
           return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/cancel-service", method = RequestMethod.POST)
    public ResponseEntity cancelService(@RequestBody CreateServiceRequest createServiceRequest) {
        try {
            redSOSService.cancelService(
                createServiceRequest.getService(),
                createServiceRequest.getPerson()
            );
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex) {
           return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }

}
