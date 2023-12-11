package fr.univ_lyon1.info.m1.elizagpt.model.message;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.ArrayList;

public class MessageManager {


    public Message addMessage(final ArrayList<Message> messages, final String text, final Message.Sender sender) {
        Message newMessage = new Message(text, sender);
        messages.add(newMessage);
        return newMessage;
    }

    public void deleteMessage(final ArrayList<Message> messages,final int messageId) {
        messages.removeIf(message -> message.getId() == messageId);
    }
}
