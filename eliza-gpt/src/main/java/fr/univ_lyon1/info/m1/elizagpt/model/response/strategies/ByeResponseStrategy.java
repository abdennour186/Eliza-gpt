package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseStrategy;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ByeResponseStrategy extends UserNameFinder implements ResponseStrategy {
    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        Pattern pattern = Pattern.compile("Au revoir.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = getUserName(messages);
            if( userName != null){
                return "Oh non, c'est trop triste de se quitter "
                        + userName
                        +" !";
            }
            else {
                return "Oh non, c'est trop triste de se quitter !";
            }

        }
        return null;
    }
}