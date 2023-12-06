package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class UserNameResponseStrategy extends UserNameFinder implements ResponseStrategy{


    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        Pattern pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            if (getUserName(messages) != null) {
                return "Votre nom est " + getUserName(messages) + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }
        return null;
    }

}
