package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.List;


public class DefaultResponseHandler implements ResponseHandler {

    private final UserName userName;

    private ResponseHandler nextHandler;

    public DefaultResponseHandler(final UserName userName){
        this.userName = userName;
    }

    @Override
    public String handleResponse(String userMessage) {
        String userName = this.userName.getUserName();
        if (userName != null) {
            return "Qu'est-ce qui vous fait dire cela, " + userName + " ?";
        } else {
            return "Qu'est-ce qui vous fait dire cela ?";
        }
    }

    @Override
    public void setNextHandler(ResponseHandler handler) {
        this.nextHandler = handler;
    }

}
