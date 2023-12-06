package fr.univ_lyon1.info.m1.elizagpt.model;


import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseStrategy;

import java.util.ArrayList;
import java.util.List;


public class MessageGenerator {


    private final List<ResponseStrategy> strategies;


    public MessageGenerator(List<ResponseStrategy> strategies){
        this.strategies = strategies;
    }


    public String generateElizaResponse(final ArrayList<Message> messages,final String userMessage) {
        for(ResponseStrategy strategy : strategies){
            String response = strategy.generateResponse(messages,userMessage);
            if(response != null)
                return response;
        }
        return null;
    }

}
