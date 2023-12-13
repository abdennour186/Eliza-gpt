package fr.univ_lyon1.info.m1.elizagpt.model.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.univ_lyon1.info.m1.elizagpt.model.message.Message.Sender.ELIZA;
import static fr.univ_lyon1.info.m1.elizagpt.model.message.Message.Sender.USER;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    public Message msg ;

    @BeforeEach
    public void setUp() {
        msg = new Message("the text", ELIZA);
    }

    @Test
    void getText() {
        assertEquals("the text", msg.getText());
    }

    @Test
    void getSender() {
        assertEquals(ELIZA, msg.getSender());
    }

    @Test
    void setText() {
        msg.setText("the new text");
        assertEquals("the new text", msg.getText());
    }

    @Test
    void setSender() {
        msg.setSender(USER);
        assertEquals(USER, msg.getSender());
    }

}