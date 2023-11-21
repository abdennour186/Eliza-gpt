package fr.univ_lyon1.info.m1.elizagpt.model;

public class Message {

    private String text;

    private Sender sender;


    public enum Sender{
        ELIZA,
        USER
    }

    public Message(String text, Sender sender) {
        this.text = text;
        this.sender = sender;
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

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", sender=" + sender +
                '}';
    }
}
