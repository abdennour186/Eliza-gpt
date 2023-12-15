package fr.univ_lyon1.info.m1.elizagpt.model.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class MessageManagerTest {
    ArrayList<Message> messages;
    MessageManager messageManager;
    @BeforeEach
    public void setUp(){
        messages = new ArrayList<>();
        messages.add(new Message("a test text !", Message.Sender.USER));
        messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        messages.add(new Message("another one !", Message.Sender.ELIZA));
        messageManager = new MessageManager(messages);
    }

    @Test
    void addMessage() {
        Message.Sender newMessageSender = Message.Sender.USER;
        String newMessageText = "new message text";
        int oldSize = messages.size();
        Message result = messageManager.addMessage(newMessageText, newMessageSender);
        int newSize = messages.size();



        assertEquals(oldSize+1, newSize);
        assertEquals(result.getText(), newMessageText);
        assertEquals(result.getSender(), newMessageSender);
        assertEquals(messages.get(messages.size()-1).getText(), newMessageText);
    }

    @Test
    void deleteMessage() {
        int MessageId = messages.get(0).getId();

        int oldSize = messages.size();
        messageManager.deleteMessage(MessageId);
        int newSize = messages.size();
        assertEquals(oldSize-1, newSize);
        assertEquals(messages.get(1).getText(), "another one !");
    }
}