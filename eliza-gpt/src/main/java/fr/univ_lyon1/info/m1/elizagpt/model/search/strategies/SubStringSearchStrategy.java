package fr.univ_lyon1.info.m1.elizagpt.model.search.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * The SubStringSearchStrategy class implements the SearchStrategy interface and represents
 * a strategy for searching messages based on substring matches.
 *
 * @version 1.0
 */
public final class SubStringSearchStrategy implements SearchStrategy {

    private static SubStringSearchStrategy instance = null;

    /**
     * Searches for messages containing the specified substring
     * within the provided list of messages.
     *
     * @param messages The list of messages to search within.
     * @param text     The substring to search for within messages.
     * @return A list of messages that contain the specified substring.
     */
    @Override
    public List<Message> search(final List<Message> messages, final String text) {
        ArrayList<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getText().toLowerCase().contains(text.toLowerCase())) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    /**
     * Gets the singleton instance of SubStringSearchStrategy.
     *
     * @return The singleton instance of SubStringSearchStrategy.
     */
    public static SubStringSearchStrategy getInstance() {
        if (instance == null) {
            instance = new SubStringSearchStrategy();
        }
        return instance;
    }

    // Private constructor to enforce singleton pattern
    private SubStringSearchStrategy() { }

    /**
     * Returns a string representation of the SubStringSearchStrategy.
     *
     * @return The string representation of the SubStringSearchStrategy.
     */
    @Override
    public String toString() {
        return "Substring";
    }
}
