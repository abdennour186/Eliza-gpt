package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionResponseStrategyTest {

    QuestionResponseStrategy ques_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        ques_obj = new QuestionResponseStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String input = "a question???";
        String expected_res1  = "Je vous renvoie la question.";
        String expected_res2  ="Ici, c'est moi qui pose les questions.";
        String result = ques_obj.generateResponse(Messages,input);

        assertTrue(result.equals(expected_res1) || result.equals(expected_res2));
    }
}