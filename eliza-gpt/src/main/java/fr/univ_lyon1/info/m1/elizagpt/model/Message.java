package fr.univ_lyon1.info.m1.elizagpt.model;

public class Message {

    private static int nextId = 1;
    private final int id;
    private String text;
    private Sender sender;

    public enum Sender{
        ELIZA,
        USER
    }

    public Message(String text, Sender sender) {
        this.text = text;
        this.sender = sender;
        this.id = nextId++;
    }

    public String getText() {
        return text;
    }

    public Sender getSender() {
        return sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", sender=" + sender +
                '}';
    }
}
