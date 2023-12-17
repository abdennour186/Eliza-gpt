package fr.univ_lyon1.info.m1.elizagpt.model.message;

/**
 * Represents a message in the system.
 * Each message has a unique identifier, text content, and a sender (either ELIZA or USER).
 */
public class Message {

    /**
     * The next available identifier for a message.
     */
    private static int nextId = 1;

    /**
     * The unique identifier of the message.
     */
    private final int id;

    /**
     * The text content of the message.
     */
    private String text;

    /**
     * The sender of the message, which can be either ELIZA or USER.
     */
    private Sender sender;

    /**
     * Enum representing possible senders of a message (ELIZA or USER).
     */
    public enum Sender {
        ELIZA,
        USER
    }

    /**
     * Constructs a new Message with the given text and sender.
     *
     * @param text   The content of the message.
     * @param sender The sender of the message (ELIZA or USER).
     */
    public Message(final String text, final Sender sender) {
        this.text = text;
        this.sender = sender;
        this.id = nextId++;
    }

    /**
     * Gets the text content of the message.
     *
     * @return The text content of the message.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the sender of the message.
     *
     * @return The sender of the message (ELIZA or USER).
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * Sets the text content of the message.
     *
     * @param text The new text content of the message.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Sets the sender of the message.
     *
     * @param sender The new sender of the message (ELIZA or USER).
     */
    public void setSender(final Sender sender) {
        this.sender = sender;
    }

    /**
     * Gets the unique identifier of the message.
     *
     * @return The unique identifier of the message.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of the message.
     *
     * @return A string representation of the message.
     */
    @Override
    public String toString() {
        return "Message{"
                +
                "text='"
                + text
                + '\''
                + ", sender="
                + sender
                + '}';
    }
}
