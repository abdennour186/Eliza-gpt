package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Controller extends Subject{
    MessageProcessor model;



    public Controller(MessageProcessor model){
        super();
        this.model = model;
    }

    public void addUserMessage(String text){
           Message newMessage = model.addMessage(text , Message.Sender.USER);
           Payload payload = new Payload(newMessage , -1 , null);
           notifyObservers("ADD",payload);
    }

    public void addElizaMessage(String text){
          Message newMessage =  model.addMessage(text , Message.Sender.ELIZA);
          Payload payload = new Payload(newMessage , -1 , null);
          notifyObservers("ADD" ,payload);
    }

    public void search(String text){
        ArrayList<Message> result =  model.search(text);
        Payload payload = new Payload(null , -1  , result);
        notifyObservers("SEARCH" , payload);
    }

    public void undoSearch(){
        Payload payload = new Payload(null , -1 , model.getMessages());
        notifyObservers("UNSEARCH" , payload);
    }

    public void deleteMessage(int messageId){
        this.model.deleteMessage(messageId);
        Payload payload = new Payload(null , messageId , null);
        notifyObservers("DELETE" , payload);
    }

    public String generateElizaResponse(String userMessage){
        return model.generateElizaResponse(userMessage);
    }


}
