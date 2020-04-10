/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.component;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author oiprado
 */
@Component
public class NotificationComponent {

    @Autowired
    private FirebaseApp firebaseApp;

    public void send(List<String> tokens, String body, String title, String guid) {

        try {
            
            FirebaseApp.getInstance();

            MulticastMessage message = MulticastMessage.builder()
                    .setNotification(new Notification(title, body))
                    .putData("guid", guid)
                    .addAllTokens(tokens)
                    .build();

            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException ex) {
            Logger.getLogger(NotificationComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendBatch(String body, String title, String guid) {
        
        try {
            
            FirebaseApp.getInstance();
            
            List<Message> messages = Arrays.asList(
                Message.builder()
                    .setNotification(new Notification(title, body))
                    .putData("guid", guid)
                    .setTopic("Red-SOS")
                    .build()
            );
            
            BatchResponse response = FirebaseMessaging.getInstance().sendAll(messages);
        } catch (FirebaseMessagingException ex) {
            Logger.getLogger(NotificationComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
