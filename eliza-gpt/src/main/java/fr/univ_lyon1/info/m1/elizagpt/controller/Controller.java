package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;


import java.util.ArrayList;

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
        ArrayList<Message> result =  model.search(text);
        Payload payload = new Payload(null , -1  , result);
        System.out.println("from controller : " + result);
        notifyObservers("SEARCH" , null , payload);
    }

    public void undoSearch(){
        Payload payload = new Payload(null , -1 , model.getMessages());
        notifyObservers("UNSEARCH" , null , payload);
    }

    public void deleteMessage(int index){
        this.model.deleteMessage(index);
        notifyObservers("DELETE" ,null ,new Payload(null , index , null));
    }

    public String generateElizaResponse(String userMessage){
        return model.generateElizaResponse(userMessage);
    }


}
