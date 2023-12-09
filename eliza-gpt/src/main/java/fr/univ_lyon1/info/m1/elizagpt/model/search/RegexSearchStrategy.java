package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RegexSearchStrategy implements SearchStrategy{

    private static RegexSearchStrategy instance = null;
    @Override
    public ArrayList<Message> search(ArrayList<Message> messages, String text) {
        Pattern pattern = Pattern.compile(".*" + text + ".*", Pattern.CASE_INSENSITIVE);

        ArrayList<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            Matcher matcher = pattern.matcher(message.getText());
            if (matcher.matches()) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    public static RegexSearchStrategy getInstance(){

        if(instance == null)
            instance = new RegexSearchStrategy();
        return instance;
    }
    private RegexSearchStrategy(){}

    @Override
    public String toString() {
        return "Regexp";
    }
}