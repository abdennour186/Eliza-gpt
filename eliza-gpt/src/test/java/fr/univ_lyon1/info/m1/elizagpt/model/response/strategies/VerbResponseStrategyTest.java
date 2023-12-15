package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VerbResponseStrategyTest {
    VerbResponseStrategy ver_obj;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        ver_obj = new VerbResponseStrategy();
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
    }

    @Test
    void generateResponse() {
        String userMessage = "Je suis intelligent.";
        String response = ver_obj.generateResponse(Messages, userMessage);
        String expected_res1  = "Pourquoi dites-vous que vous êtes intelligent ?";
        String expected_res2  ="Pourquoi pensez-vous que vous êtes intelligent ?";
        String expected_res3  ="Êtes-vous sûr que vous êtes intelligent ?";

        assertTrue(response.equals(expected_res1) || response.equals(expected_res2) || response.equals(expected_res3));
        userMessage = "This is not a matching pattern.";
        response = ver_obj.generateResponse(Messages, userMessage);
        assertNull(response);
    }

    @Test
    void firstToSecondPerson() {
        //assertEquals("vous avez un livre", ver_obj.firstToSecondPerson("j'ai un livre"));
        assertEquals("vous savez nager", ver_obj.firstToSecondPerson("je sais nager"));
        assertEquals("vous devez étudier", ver_obj.firstToSecondPerson("je dois étudier"));
        //assertEquals("vous m'aidez", ver_obj.firstToSecondPerson("tu m'aides"));
    }
}