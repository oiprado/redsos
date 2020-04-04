/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.util;

/**
 *
 * @author oiprado
 */
public class Message {

    public static final int STATUS_CREATED = 1;
    public static final int STATUS_EXISTS = 2;
    public static final int STATUS_SOME_ERROR = 3;
    
    private int status = 0;
    private String message;

    public Message() {
    }

    public Message(int status, String message) {
        this.message = message;
        this.status = status;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
