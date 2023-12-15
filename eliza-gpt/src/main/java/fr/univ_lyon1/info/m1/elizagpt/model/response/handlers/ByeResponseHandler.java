package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ByeResponseHandler implements ResponseHandler {
    private ResponseHandler nextHandler;
    private final UserName userName;

    public ByeResponseHandler(UserName userName){
        this.userName = userName;
    }

    @Override
    public String handleResponse(String userMessage) {
        Pattern pattern = Pattern.compile("Au revoir.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = this.userName.getUserName();
            if( userName != null){
                return "Oh non, c'est trop triste de se quitter "
                        + userName
                        +" !";
            }
            else {
                return "Oh non, c'est trop triste de se quitter !";
            }

        }
        return nextHandler.handleResponse(userMessage);
    }

    @Override
    public void setNextHandler(ResponseHandler handler) {
        this.nextHandler = handler;
    }
}