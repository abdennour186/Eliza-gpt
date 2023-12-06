package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestionResponseStrategy extends RandomResponse implements ResponseStrategy {





    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        Pattern pattern = Pattern.compile(".*\\?");
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return pickRandom(new String[]{
                    "Je vous renvoie la question.",
                    "Ici, c'est moi qui pose les questions.",
            });
        }
        return null;
    }

}
