package fr.univ_lyon1.info.m1.elizagpt.model.response;


public interface ResponseHandler {
    String handleResponse(final String userMessage);
    void setNextHandler(final ResponseHandler handler);
}

