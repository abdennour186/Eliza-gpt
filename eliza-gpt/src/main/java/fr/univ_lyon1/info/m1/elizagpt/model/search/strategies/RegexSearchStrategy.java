package fr.univ_lyon1.info.m1.elizagpt.model.search.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The RegexSearchStrategy class implements the SearchStrategy interface and represents
 * a strategy for searching messages based on regular expression matches.
 *
 * @version 1.0
 */
public class RegexSearchStrategy implements SearchStrategy {

    private static RegexSearchStrategy instance = null;

    /**
     * Searches for messages containing the specified regular expression match
     * within the provided list of messages.
     *
     * @param messages The list of messages to search within.
     * @param text     The regular expression to search for within messages.
     * @return A list of messages that contain the specified regular expression match.
     */
    @Override
    public List<Message> search(List<Message> messages, String text) {
        Pattern pattern = Pattern.compile(".*" + text + ".*", Pattern.CASE_INSENSITIVE);

        ArrayList<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            Matcher matcher = pattern.matcher(message.getText());
            if (matcher.matches()) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    /**
     * Gets the singleton instance of RegexSearchStrategy.
     *
     * @return The singleton instance of RegexSearchStrategy.
     */
    public static RegexSearchStrategy getInstance() {
        if (instance == null) {
            instance = new RegexSearchStrategy();
        }
        return instance;
    }

    // Private constructor to enforce singleton pattern
    private RegexSearchStrategy() {}

    /**
     * Returns a string representation of the RegexSearchStrategy.
     *
     * @return The string representation of the RegexSearchStrategy.
     */
    @Override
    public String toString() {
        return "Regexp";
    }
}
