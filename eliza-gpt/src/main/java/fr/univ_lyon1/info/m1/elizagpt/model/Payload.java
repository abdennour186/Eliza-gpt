package fr.univ_lyon1.info.m1.elizagpt.model;
import java.util.ArrayList;

public class Payload{
    private final String newMessage;
    private final int deletedMessageIndex;

    // to store the indexes of the retrieved messages
    private final ArrayList<Message> searchResult;

    public Payload(String newMessage, int deletedMessageIndex, ArrayList<Message> searchResult) {
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

    public ArrayList<Message> getSearchResult() {
        return searchResult;
    }
}
