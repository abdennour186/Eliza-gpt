package fr.univ_lyon1.info.m1.elizagpt.model.search.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The WordSearchStrategy class implements the SearchStrategy interface and represents
 * a strategy for searching messages based on complete word matches.
 *
 * @version 1.0
 */
public class WordSearchStrategy implements SearchStrategy {

    private static WordSearchStrategy instance = null;

    /**
     * Searches for messages containing the specified complete word within the provided list of messages.
     *
     * @param messages The list of messages to search within.
     * @param text     The complete word to search for within messages.
     * @return A list of messages that contain the specified complete word.
     */
    @Override
    public List<Message> search(final List<Message> messages, final String text) {
        ArrayList<Message> filteredMessages = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*\\b" + text + "\\b.*", Pattern.CASE_INSENSITIVE);
        for (Message message : messages) {
            Matcher matcher = pattern.matcher(message.getText());
            if (matcher.matches()) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    /**
     * Gets the singleton instance of WordSearchStrategy.
     *
     * @return The singleton instance of WordSearchStrategy.
     */
    public static WordSearchStrategy getInstance() {
        if (instance == null) {
            instance = new WordSearchStrategy();
        }
        return instance;
    }

    // Private constructor to enforce singleton pattern
    private WordSearchStrategy() { }

    /**
     * Returns a string representation of the WordSearchStrategy.
     *
     * @return The string representation of the WordSearchStrategy.
     */
    @Override
    public String toString() {
        return "Mot Complet";
    }
}
