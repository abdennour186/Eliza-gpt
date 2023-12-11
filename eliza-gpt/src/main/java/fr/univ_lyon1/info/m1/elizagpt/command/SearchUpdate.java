package fr.univ_lyon1.info.m1.elizagpt.command;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.ArrayList;

/**
 * Represents a specific update type for searching messages in the ElizaGPT application.
 * Instances of this class encapsulate information related to a search operation, including
 * the search text and the resulting list of messages matching the search criteria.
 *
 * <p>This class extends the {@link Update} abstract class and follows the Command Pattern
 * to provide a standardized way of representing search operations that can be applied to
 * the application state.</p>
 *
 * <p>Instances of this class are typically created and used by the application's components,
 * such as the {@link fr.univ_lyon1.info.m1.elizagpt.controller.Controller}, to communicate and
 * execute search-related updates.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.command.Update
 * @see fr.univ_lyon1.info.m1.elizagpt.controller.Controller
 */
public class SearchUpdate extends Update {
    private final String searchText;
    private final ArrayList<Message> searchResult;

    /**
     * Constructs a new SearchUpdate instance with the specified search text and search result.
     *
     * @param searchText The text used for searching messages.
     * @param searchResult The list of messages matching the search criteria.
     */
    public SearchUpdate(final String searchText, final ArrayList<Message> searchResult) {
        this.searchText = searchText;
        this.searchResult = searchResult;
    }

    /**
     * Gets the search text used for searching messages.
     *
     * @return The search text.
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Gets the list of messages matching the search criteria.
     *
     * @return The search result, represented as an ArrayList of Message objects.
     */
    public ArrayList<Message> getSearchResult() {
        return searchResult;
    }
}
