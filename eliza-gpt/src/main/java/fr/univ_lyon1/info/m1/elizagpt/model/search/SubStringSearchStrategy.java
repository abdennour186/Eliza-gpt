package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;


public class SubStringSearchStrategy implements SearchStrategy{

    private static SubStringSearchStrategy instance = null;
    @Override
    public ArrayList<Message> search(ArrayList<Message> messages, String text) {
        ArrayList<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getText().toLowerCase().contains(text.toLowerCase())) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    public static SubStringSearchStrategy getInstance(){
        if(instance == null)
            instance = new SubStringSearchStrategy();
        return instance;
    }
    private SubStringSearchStrategy(){};

    @Override
    public String toString() {
        return "Substring";
    }
}