package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller extends Subject{
    MessageProcessor model;



    public Controller(MessageProcessor model){
        super();
        this.model = model;
    }

    public void addUserMessage(String text){
           model.addMessage(text , Message.Sender.USER);
           notifyObservers("ADD", Message.Sender.USER,new Payload(text , -1 , null));
    }

    public void addElizaMessage(String text){
          model.addMessage(text , Message.Sender.ELIZA);
          notifyObservers("ADD" ,Message.Sender.ELIZA,new Payload(text , -1 , null));
    }

    public void search(String text){
        Map<Integer , Message> result =  model.search(text);
        Payload payload = new Payload(null , -1  , result);
        notifyObservers("SEARCH" , null , payload);
    }

    public void undoSearch(){
        Map<Integer , Message> originalMessages = new HashMap<>();
        for(int i = 0;i < model.getMessages().size() ; i++){
            Message current = model.getMessages().get(i);
            originalMessages.put(i,current);
        }
        Payload payload = new Payload(null , -1 , originalMessages);
        notifyObservers("UNSEARCH" , null , payload);
    }

    public void deleteMessage(int originalIndex,int currentIndex){
        this.model.deleteMessage(originalIndex);
        notifyObservers("DELETE" ,null ,new Payload(null , currentIndex , null));
    }

    public String generateElizaResponse(String userMessage){
        return model.generateElizaResponse(userMessage);
    }


}
