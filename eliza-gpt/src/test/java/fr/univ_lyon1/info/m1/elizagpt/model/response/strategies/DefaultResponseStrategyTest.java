package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DefaultResponseStrategyTest {

    DefaultResponseStrategy def_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        def_obj = new DefaultResponseStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String input = "test input";
        String expected_res  = "Qu'est-ce qui vous fait dire cela ?";
        String result = def_obj.generateResponse(Messages,input);

        assertEquals(result,expected_res);
    }
    @Test
    void generateResponseWithName() {
        Messages.add(new Message("Je m'appelle abdennour.", Message.Sender.USER));
        String input = "test input";
        String expected_res  = "Qu'est-ce qui vous fait dire cela, abdennour ?";
        String result = def_obj.generateResponse(Messages,input);
        assertEquals(expected_res,result);
    }
}