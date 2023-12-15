package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NameResponseStrategyTest {

    NameResponseStrategy name_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        name_obj = new NameResponseStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String input = "Je m'appelle abdennour.";
        String expected_res  = "Bonjour abdennour.";
        String result = name_obj.generateResponse(Messages,input);

        assertEquals(result,expected_res);
    }
}