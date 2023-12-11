package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.ArrayList;

public interface ResponseStrategy {
     String generateResponse(ArrayList<Message> messages, String userMessage);
}
