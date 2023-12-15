package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomResponseStrategyTest {

    RandomResponseStrategy rand_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        rand_obj = new RandomResponseStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String input = "a question?";
        String expected_res1  = "Il fait beau aujourd'hui, vous ne trouvez pas ?";
        String expected_res2  ="Je ne comprends pas.";
        String expected_res3  ="Hmmm, hmm ...";
        String result = rand_obj.generateResponse(Messages,input);
        if(result != null){
            assertTrue(result.equals(expected_res1) || result.equals(expected_res2 ) || result.equals(expected_res3));
        }
    }
}