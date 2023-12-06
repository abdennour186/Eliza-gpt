package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;



public class DefaultResponseStrategy extends UserNameFinder implements ResponseStrategy{


    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        if (getUserName(messages) != null) {
            return "Qu'est-ce qui vous fait dire cela, " + getUserName(messages) + " ?";
        } else {
            return "Qu'est-ce qui vous fait dire cela ?";
        }
    }
}
