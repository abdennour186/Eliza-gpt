package fr.univ_lyon1.info.m1.elizagpt.controller;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;
import fr.univ_lyon1.info.m1.elizagpt.view.ViewObserver;

import java.util.ArrayList;

public abstract class Subject {
    ArrayList<ViewObserver> observers;

    public  Subject(){
        this.observers = new ArrayList<>();
    }

    public void notifyObservers(String action, Payload payload){
           observers.forEach(observer -> {
               switch (action){
                   case "ADD":
                       observer.onMessageAddUpdate(payload);
                       break;
                   case "DELETE":
                        observer.onDeleteUpdate(payload);
                        break;
                   case "SEARCH":
                        observer.onSearchUpdate(payload);
                        break;
                   case "UNSEARCH":
                        observer.onUndoSearchUpdate(payload);
                        break;
                   default:
                        throw new IllegalArgumentException("Undefined Action");
               }
           });
    }

    public void registerObserver(ViewObserver observer){
        this.observers.add(observer);
    }

    public void unregisterObserver(ViewObserver observer){
        this.observers.remove(observer);
    }
}
