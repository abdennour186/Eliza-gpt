package fr.univ_lyon1.info.m1.elizagpt.model.message;

import java.util.List;

public class MessageManager {

    private final List<Message> messages;

    public MessageManager(final List<Message> messages){
        this.messages = messages;
    }

    public Message addMessage(final String text, final Message.Sender sender) {
        Message newMessage = new Message(text, sender);
        this.messages.add(newMessage);
        return newMessage;
    }

    public void deleteMessage(final int messageId) {
        this.messages.removeIf(message -> message.getId() == messageId);
    }
}
