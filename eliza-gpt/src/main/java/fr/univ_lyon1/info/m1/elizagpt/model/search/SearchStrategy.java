package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.List;

/**
 * The SearchStrategy interface defines the contract for implementing different strategies
 * for searching messages within a chatbot system.
 *
 * @version 1.0
 */
public interface SearchStrategy {

    /**
     * Searches for messages containing the specified text within the provided list of messages.
     *
     * @param messages The list of messages to search within.
     * @param text     The text to search for within messages.
     * @return A list of messages that contain the specified text.
     */
    List<Message> search(final List<Message> messages, final String text);
}
