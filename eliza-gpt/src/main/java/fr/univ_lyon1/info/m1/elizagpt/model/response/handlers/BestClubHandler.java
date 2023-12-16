package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BestClubHandler implements ResponseHandler {

    private ResponseHandler nextHandler;
    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern = Pattern.compile("Quelle est la meilleure équipe du foot \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Bien sûr c'est le Real Madrid !";
        }
        return nextHandler.handleResponse(userMessage);
    }

    @Override
    public void setNextHandler(final ResponseHandler handler) {
           this.nextHandler = handler;
    }
}
