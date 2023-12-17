package fr.univ_lyon1.info.m1.elizagpt.model.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.univ_lyon1.info.m1.elizagpt.model.message.Message.Sender.ELIZA;
import static fr.univ_lyon1.info.m1.elizagpt.model.message.Message.Sender.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTest {
    private Message message;

    @BeforeEach
    public void setUp() {
        message = new Message("the text", ELIZA);
    }

    @Test
    void getText() {
        assertEquals("the text", message.getText());
    }

    @Test
    void getSender() {
        assertEquals(ELIZA, message.getSender());
    }

    @Test
    void setText() {
        message.setText("the new text");
        assertEquals("the new text", message.getText());
    }

    @Test
    void setSender() {
        message.setSender(USER);
        assertEquals(USER, message.getSender());
    }

}
