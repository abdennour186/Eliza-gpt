package fr.univ_lyon1.info.m1.elizagpt.model.message;

import java.util.List;

/**
 * The MessageManager class is responsible for managing messages in a chatbot system.
 *
 * @version 1.0
 */
public class MessageManager {

    private final List<Message> messages;

    /**
     * Constructs a new MessageManager with the specified list of messages.
     *
     * @param messages The list of messages to manage.
     */
    public MessageManager(final List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Adds a new message to the list with the given text and sender.
     *
     * @param text   The text of the new message.
     * @param sender The sender of the new message.
     * @return The newly created Message object.
     */
    public Message addMessage(final String text, final Message.Sender sender) {
        Message newMessage = new Message(text, sender);
        this.messages.add(newMessage);
        return newMessage;
    }

    /**
     * Deletes a message from the list based on its ID.
     *
     * @param messageId The ID of the message to be deleted.
     */
    public void deleteMessage(final int messageId) {
        this.messages.removeIf(message -> message.getId() == messageId);
    }
}
