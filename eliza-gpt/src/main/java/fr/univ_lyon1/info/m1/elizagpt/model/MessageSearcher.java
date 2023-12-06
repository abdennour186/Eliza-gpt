package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageSearcher {

    public ArrayList<Message> search(final ArrayList<Message> messages, final String text){
        ArrayList<Message> result = new ArrayList<>();
        Pattern pattern;
        Matcher matcher;
        for (Message message : messages) {
            pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(message.getText());
            if (matcher.find()) {
                result.add(message);
            }
        }
        return result;
    }
}
