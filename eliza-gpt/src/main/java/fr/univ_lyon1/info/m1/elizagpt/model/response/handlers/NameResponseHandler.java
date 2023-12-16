package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameResponseHandler implements ResponseHandler {

    private ResponseHandler nextHandler;
    private final UserName userName;

    public NameResponseHandler(final UserName userName) {
        this.userName = userName;
    }

    @Override
    public String handleResponse(String userMessage) {
        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = matcher.group(1);
            this.userName.setUserName(userName);
            return "Bonjour " + matcher.group(1) + ".";
        }
        return nextHandler.handleResponse(userMessage);
    }

    @Override
    public void setNextHandler(final ResponseHandler handler) {
        this.nextHandler = handler;
    }
}
