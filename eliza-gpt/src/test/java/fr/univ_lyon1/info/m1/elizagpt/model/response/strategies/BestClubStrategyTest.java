package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestClubStrategyTest {
    BestClubStrategy bes_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        bes_obj = new BestClubStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String input = "Quelle est la meilleure équipe du foot ?";
        String expected_res  = "Bien sûr c'est le Real Madrid !";
        String result = bes_obj.generateResponse(Messages,input);

        assertEquals(result,expected_res);
    }
}