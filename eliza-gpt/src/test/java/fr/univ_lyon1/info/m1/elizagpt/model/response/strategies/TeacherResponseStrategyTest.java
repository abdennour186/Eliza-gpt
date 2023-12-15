package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeacherResponseStrategyTest {

    TeacherResponseStrategy tea_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        tea_obj = new TeacherResponseStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String input = "Qui est le plus intelligent ?";
        String expected_res  = "Le plus intelligent est bien sûr votre enseignant de MIF01!";
        String result = tea_obj.generateResponse(Messages,input);

        assertEquals(result,expected_res);
    }
}