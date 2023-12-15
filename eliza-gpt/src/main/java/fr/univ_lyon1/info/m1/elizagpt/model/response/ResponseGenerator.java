package fr.univ_lyon1.info.m1.elizagpt.model.response;


import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;


import java.util.ArrayList;
import java.util.List;


public class ResponseGenerator {


    private final List<ResponseHandler> handlers;


    public ResponseGenerator(List<ResponseHandler> handlers){
        this.handlers = handlers;
        for(int i = 0;i < handlers.size() - 1;i++)
            handlers.get(i).setNextHandler(handlers.get(i+1));
    }


    public String generateElizaResponse(final String userMessage) {
        return handlers.get(0).handleResponse(userMessage);
    }

}
