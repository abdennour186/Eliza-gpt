package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;

/**
 * The Payload class represents a data container for various updates in the system.
 * It is used to encapsulate information related to new messages,
 * deleted messages, search results, and search text.
 */
public class Payload {

    /**
     * The new message added to the system.
     */
    private final Message newMessage;

    /**
     * The ID of the deleted message (if any).
     */
    private final int deletedMessageId;

    /**
     * The list of messages retrieved as search results.
     */
    private final ArrayList<Message> searchResult;

    /**
     * The text used for searching messages.
     */
    private final String searchText;

    /**
     * Constructs a new Payload with the specified information.
     *
     * @param newMessage      The new message added to the system.
     * @param deletedMessageId The ID of the deleted message (if any).
     * @param searchResult    The list of messages retrieved as search results.
     * @param searchText      The text used for searching messages.
     */
    public Payload(final Message newMessage, final int deletedMessageId,
                   final ArrayList<Message> searchResult, final String searchText) {
        this.newMessage = newMessage;
        this.deletedMessageId = deletedMessageId;
        this.searchResult = searchResult;
        this.searchText = searchText;
    }

    /**
     * Gets the new message added to the system.
     *
     * @return The new message.
     */
    public Message getNewMessage() {
        return newMessage;
    }

    /**
     * Gets the ID of the deleted message (if any).
     *
     * @return The ID of the deleted message.
     */
    public int getDeletedMessageId() {
        return deletedMessageId;
    }

    /**
     * Gets the list of messages retrieved as search results.
     *
     * @return The list of search results.
     */
    public ArrayList<Message> getSearchResult() {
        return searchResult;
    }

    /**
     * Gets the text used for searching messages.
     *
     * @return The search text.
     */
    public String getSearchText() {
        return searchText;
    }
}
