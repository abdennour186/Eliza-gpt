package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherResponseHandler implements ResponseHandler {

    private ResponseHandler nextHandler;

    @Override
    public String handleResponse(String userMessage) {
        Pattern pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Le plus " + matcher.group(1) + " est bien sûr votre enseignant de MIF01!";
        }
        return nextHandler.handleResponse(userMessage);
    }

    @Override
    public void setNextHandler(ResponseHandler handler) {
         this.nextHandler = handler;
    }
}
