package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestionResponseHandler extends RandomResponse implements ResponseHandler {

    private ResponseHandler nextHandler;


    @Override
    public String handleResponse(String userMessage) {
        Pattern pattern = Pattern.compile(".*\\?");
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return pickRandom(new String[]{
                    "Je vous renvoie la question.",
                    "Ici, c'est moi qui pose les questions.",
            });
        }
        return nextHandler.handleResponse(userMessage);
    }

    @Override
    public void setNextHandler(ResponseHandler handler) {
            this.nextHandler = handler;
    }
}
