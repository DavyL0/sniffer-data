package com.davy.snifferdata.services;

import org.springframework.stereotype.Service;

@Service
public class NotifierService {

    public void alert(String message){
        System.out.println("Alert: " + message);
    }
}
