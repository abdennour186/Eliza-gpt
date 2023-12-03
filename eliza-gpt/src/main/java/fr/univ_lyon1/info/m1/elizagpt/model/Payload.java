package fr.univ_lyon1.info.m1.elizagpt.model;
import java.util.ArrayList;
import java.util.Map;

public class Payload{
    private final Message newMessage;
    private final int deletedMessageId;

    // to store the retrieved messages
    private final ArrayList<Message> searchResult;

    public Payload(Message newMessage, int deletedMessageId, ArrayList<Message> searchResult) {
        this.newMessage = newMessage;
        this.deletedMessageId = deletedMessageId;
        this.searchResult = searchResult;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    public int getDeletedMessageId() {
        return deletedMessageId;
    }

    public ArrayList<Message> getSearchResult() {
        return searchResult;
    }
}
