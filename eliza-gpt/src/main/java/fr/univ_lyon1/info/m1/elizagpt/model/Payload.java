package fr.univ_lyon1.info.m1.elizagpt.model;
import java.util.Map;

public class Payload{
    private final String newMessage;
    private final int deletedMessageIndex;

    // to store the retrieved messages
    private final Map<Integer , Message> searchResult;

    public Payload(String newMessage, int deletedMessageIndex, Map<Integer , Message> searchResult) {
        this.newMessage = newMessage;
        this.deletedMessageIndex = deletedMessageIndex;
        this.searchResult = searchResult;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public int getDeletedMessageIndex() {
        return deletedMessageIndex;
    }

    public Map<Integer,Message> getSearchResult() {
        return searchResult;
    }
}
