package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;

import java.util.ArrayList;

/**
 * The Controller class serves as the controller in the MVC (Model-View-Controller) architecture.
 * It handles user input, communicates with the model, and notifies observers of state changes.
 */
public class Controller extends Subject {

    /**
     * The model responsible for processing messages.
     */
    private final MessageProcessor model;

    /**
     * Constructs a new Controller with the specified MessageProcessor.
     *
     * @param model The MessageProcessor to be associated with the controller.
     */
    public Controller(final MessageProcessor model) {
        super();
        this.model = model;
    }

    /**
     * Adds a user message to the model and notifies observers of the action.
     *
     * @param text The text content of the user message.
     */
    public void addUserMessage(final String text) {
        Message newMessage = model.addMessage(text, Message.Sender.USER);
        Payload payload = new Payload(newMessage, -1, null, null);
        notifyObservers(ACTION.ADD, payload);
    }

    /**
     * Adds an Eliza message to the model and notifies observers of the action.
     *
     * @param text The text content of the Eliza message.
     */
    public void addElizaMessage(final String text) {
        Message newMessage = model.addMessage(text, Message.Sender.ELIZA);
        Payload payload = new Payload(newMessage, -1, null, null);
        notifyObservers(ACTION.ADD, payload);
    }

    /**
     * Searches for messages in the model based on the given text
     * and notifies observers of the results.
     * @param text The text to search for in messages.
     */
    public void search(final String text) {
        ArrayList<Message> result = model.search(text);
        Payload payload = new Payload(null, -1, result, text);
        notifyObservers(ACTION.SEARCH, payload);
    }

    /**
     * Undoes the search operation and notifies observers.
     */
    public void undoSearch() {
        Payload payload = new Payload(null, -1, model.getMessages(), "");
        notifyObservers(ACTION.UNDOSEARCH, payload);
    }

    /**
     * Deletes a message with the specified ID from the model and notifies observers of the action.
     *
     * @param messageId The ID of the message to delete.
     */
    public void deleteMessage(final int messageId) {
        this.model.deleteMessage(messageId);
        Payload payload = new Payload(null, messageId, null, null);
        notifyObservers(ACTION.DELETE, payload);
    }

    /**
     * Generates an Eliza response for the given user message.
     *
     * @param userMessage The user's message for which an Eliza response is generated.
     * @return The generated Eliza response.
     */
    public String generateElizaResponse(final String userMessage) {
        return model.generateElizaResponse(userMessage);
    }
}
