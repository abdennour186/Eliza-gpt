package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.view.ViewObserver;

import java.util.ArrayList;

public class Controller {
    MessageProcessor model;
    ArrayList<ViewObserver> observers;


    public Controller(MessageProcessor model){
        this.model = model;
        observers = new ArrayList<>();
    }

    public void addUserMessage(String text){
           model.addMessage(text , Message.Sender.USER);
           notifyObservers("ADD","USER",new Payload(text , -1 , null));
    }

    public void addElizaMessage(String text){
          model.addMessage(text , Message.Sender.ELIZA);
          notifyObservers("ADD" , "ELIZA",new Payload(text , -1 , null));
    }

    public void deleteMessage(int index){
        this.model.deleteMessage(index);
        notifyObservers("DELETE" , null ,new Payload(null , index , null));
    }

    public String generateElizaResponse(String userMessage){
        return model.generateElizaResponse(userMessage);
    }

    public void registerView(ViewObserver observer){
        this.observers.add(observer);
    }
    public void unregisterView(ViewObserver observer){
        this.observers.remove(observer);
    }
    public void notifyObservers(String action,String actor,Payload payload){
        for(ViewObserver observer : observers){
            switch (action){
                case "ADD":
                    if(actor.equals("USER"))
                        observer.onUserAddUpdate(payload);
                    else if(actor.equals("ELIZA"))
                        observer.onElizaAddUpdate(payload);
                    break;
                case "DELETE":
                    observer.onDeleteUpdate(payload);
                    break;
            }
        }
    }

    static public class Payload{
        private final String newMessage;
        private final int deletedMessageIndex;
        private final ArrayList<Message> searchResult;

        public Payload(String newMessage, int deletedMessageIndex, ArrayList<Message> searchResult) {
            this.newMessage = newMessage;
            this.deletedMessageIndex = deletedMessageIndex;
            this.searchResult = searchResult;
        }

        public String getNewMessage() {
            return newMessage;
        }

        public int getDeletedMessageIndex() {
            return deletedMessageIndex;
        }

        public ArrayList<Message> getSearchResult() {
            return searchResult;
        }
    }


}
