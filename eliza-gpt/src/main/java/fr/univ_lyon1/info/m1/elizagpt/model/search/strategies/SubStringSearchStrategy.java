package fr.univ_lyon1.info.m1.elizagpt.model.search.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;

import java.util.ArrayList;
import java.util.List;


public class SubStringSearchStrategy implements SearchStrategy {

    private static SubStringSearchStrategy instance = null;
    @Override
    public List<Message> search(final List<Message> messages, final String text) {
        ArrayList<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getText().toLowerCase().contains(text.toLowerCase())) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    public static SubStringSearchStrategy getInstance() {
        if (instance == null) {
            instance = new SubStringSearchStrategy();
        }
        return instance;
    }
    private SubStringSearchStrategy() {};

    @Override
    public String toString() {
        return "Substring";
    }
}
