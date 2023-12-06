package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameResponseStrategy implements ResponseStrategy{

    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Bonjour " + matcher.group(1) + ".";
        }
        return null;
    }
}
