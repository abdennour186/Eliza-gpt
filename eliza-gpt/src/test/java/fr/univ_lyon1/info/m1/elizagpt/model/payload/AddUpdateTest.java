package fr.univ_lyon1.info.m1.elizagpt.model.payload;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class AddUpdateTest {
    private AddUpdate addupdate;
    private Message message;
    @Test
    void getNewMessage() {
        message = new Message("test input", Message.Sender.USER);
        addupdate = new AddUpdate(message);
        Message result = addupdate.getNewMessage();

        assertSame(message, result);
    }
}
