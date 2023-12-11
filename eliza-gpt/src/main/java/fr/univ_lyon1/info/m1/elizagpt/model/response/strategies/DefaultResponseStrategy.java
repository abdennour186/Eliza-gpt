package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseStrategy;

import java.util.ArrayList;



public class DefaultResponseStrategy extends UserNameFinder implements ResponseStrategy {


    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        if (getUserName(messages) != null) {
            return "Qu'est-ce qui vous fait dire cela, " + getUserName(messages) + " ?";
        } else {
            return "Qu'est-ce qui vous fait dire cela ?";
        }
    }
}
