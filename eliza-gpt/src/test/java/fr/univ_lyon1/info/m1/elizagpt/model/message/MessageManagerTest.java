package fr.univ_lyon1.info.m1.elizagpt.model.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;

class MessageManagerTest {
    ArrayList<Message> Messages;
    MessageManager Messmana;
    @BeforeEach
    public void setUp(){
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
        Messmana = new MessageManager();
    }

    @Test
    void addMessage() {
        Message.Sender newMessageSender = Message.Sender.USER;
        String newMessageText = "new message text";
        int old_size = Messages.size();
        Message result = Messmana.addMessage(Messages, newMessageText, newMessageSender);
        int new_size = Messages.size();



        assertEquals(old_size+1, new_size);
        assertEquals(result.getText(), newMessageText);
        assertEquals(result.getSender(), newMessageSender);
        assertEquals(Messages.get(Messages.size()-1).getText(), newMessageText);
    }

    @Test
    void deleteMessage() {
        int MessageId = 1;

        int old_size = Messages.size();
        Messmana.deleteMessage(Messages, MessageId);
        int new_size = Messages.size();

        assertEquals(old_size-1, new_size);
        assertEquals(Messages.get(1).getText(), "another one !");
    }
}