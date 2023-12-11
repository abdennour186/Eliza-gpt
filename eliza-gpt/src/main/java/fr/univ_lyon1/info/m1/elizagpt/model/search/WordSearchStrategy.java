package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearchStrategy implements SearchStrategy{

    private static WordSearchStrategy instance = null;

    @Override
    public ArrayList<Message> search(ArrayList<Message> messages, String text) {
        ArrayList<Message> filteredMessages = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*\\b" + text + "\\b.*", Pattern.CASE_INSENSITIVE);
        for (Message message : messages) {
            Matcher matcher = pattern.matcher(message.getText());
            if (matcher.matches()) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    public static WordSearchStrategy getInstance(){
        if(instance == null)
            instance = new WordSearchStrategy();
        return instance;
    }
    private WordSearchStrategy(){}

    @Override
    public String toString() {
        return "Mot Complet";
    }
}