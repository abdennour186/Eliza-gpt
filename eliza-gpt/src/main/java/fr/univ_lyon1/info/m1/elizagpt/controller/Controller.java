package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.RegexSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.SubStringSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.WordSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.payload.AddUpdate;
import fr.univ_lyon1.info.m1.elizagpt.model.payload.DeleteUpdate;
import fr.univ_lyon1.info.m1.elizagpt.model.payload.SearchUpdate;
import fr.univ_lyon1.info.m1.elizagpt.model.payload.Update;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;


import java.util.Arrays;
import java.util.List;


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
        Update addUpdate = new AddUpdate(newMessage);
        notifyObservers(ACTION.ADD, addUpdate);
    }

    /**
     * Adds an Eliza message to the model and notifies observers of the action.
     *
     * @param text The text content of the Eliza message.
     */
    public void addElizaMessage(final String text) {
        Message newMessage = model.addMessage(text, Message.Sender.ELIZA);
        Update addUpdate = new AddUpdate(newMessage);
        notifyObservers(ACTION.ADD, addUpdate);
    }

    /**
     * Searches for messages in the model based on the given text
     * and notifies observers of the results.
     * @param text The text to search for in messages.
     */
    public void search(final String text) {
        List<Message> result = model.search(text);
        Update searchUpdate = new SearchUpdate(text, result);
        notifyObservers(ACTION.SEARCH, searchUpdate);
    }

    /**
     * Undoes the search operation and notifies observers.
     */
    public void undoSearch() {
        Update undoSearchUpdate = new SearchUpdate("", model.getMessages());
        notifyObservers(ACTION.UNDOSEARCH, undoSearchUpdate);
    }

    /**
     * Deletes a message with the specified ID from the model and notifies observers of the action.
     *
     * @param messageId The ID of the message to delete.
     */
    public void deleteMessage(final int messageId) {
        this.model.deleteMessage(messageId);
        Update deleteUpdate = new DeleteUpdate(messageId);
        notifyObservers(ACTION.DELETE, deleteUpdate);
    }

    /**
     * Generates an Eliza response for the given user message.
     *
     * @param userMessage The user's message for which an Eliza response is generated.
     * @return The generated Eliza response.
     */
    /**
     * Generates an Eliza GPT response based on the user's input.
     *
     * @param userMessage The user's input message.
     * @return The generated Eliza GPT response.
     */
    public String generateElizaResponse(final String userMessage) {
        return model.generateElizaResponse(userMessage);
    }

    /**
     * Sets the search strategy for message searching.
     *
     * @param strategy The search strategy to be set.
     */
    public void setSearchStrategy(final SearchStrategy strategy) {
        this.model.setSearchStrategy(strategy);
    }

    /**
     * Gets a list of available search strategies.
     *
     * @return A list of search strategies.
     */
    public List<SearchStrategy> getSearchStrategies() {
        return Arrays.asList(
                SubStringSearchStrategy.getInstance(),
                RegexSearchStrategy.getInstance(),
                WordSearchStrategy.getInstance()
        );
    }

}
