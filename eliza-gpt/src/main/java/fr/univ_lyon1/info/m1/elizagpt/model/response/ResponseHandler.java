package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.List;

public interface ResponseHandler {
    String handleResponse(String userMessage);
    void setNextHandler(ResponseHandler handler);
}
