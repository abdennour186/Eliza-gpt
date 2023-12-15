package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UserNameResponseHandler implements ResponseHandler {

    private final UserName userName;

    private ResponseHandler nextHandler;

    public UserNameResponseHandler(final UserName userName){
        this.userName = userName;
    }

    @Override
    public String handleResponse(String userMessage) {
        Pattern pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = this.userName.getUserName();
            if (userName != null) {
                return "Votre nom est " + userName + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }
        return nextHandler.handleResponse(userMessage);
    }

    @Override
    public void setNextHandler(ResponseHandler handler) {
        this.nextHandler = handler;
    }
}
