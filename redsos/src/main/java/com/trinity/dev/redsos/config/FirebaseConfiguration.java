/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trinity.dev.redsos.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author oiprado
 */
@Component
public class FirebaseConfiguration {
    
    @Value("${google.application.credentials}")
    private String googleApplicationCredentials;
    @Value("${google.database.url}")
    private String database;
    
    @Bean
    public FirebaseApp firebaseApp() {
        FirebaseApp firebaseApp = null;
        try {
            FileInputStream refreshToken = new FileInputStream(googleApplicationCredentials);
            
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl(database)
                .build();
            firebaseApp = FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FirebaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirebaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return firebaseApp;
    }
}
