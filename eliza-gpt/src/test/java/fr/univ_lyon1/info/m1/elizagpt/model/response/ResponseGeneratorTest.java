package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageManager;
import fr.univ_lyon1.info.m1.elizagpt.model.response.strategies.BestClubStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.response.strategies.ByeResponseStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResponseGeneratorTest {
    List<ResponseStrategy> strategiesMock;
    ResponseGenerator respoGen;
    ArrayList<Message> Messages;
    @BeforeEach
    public void setUp(){
        ResponseStrategy stratMock1 = mock(BestClubStrategy.class);
        ResponseStrategy stratMock2 = mock(ByeResponseStrategy.class);
        strategiesMock =Arrays.asList(
                stratMock1,
                stratMock2
        );
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
        respoGen = new ResponseGenerator(strategiesMock);
    }
    @Test
    void GenerateElizaResponse() {
        String usermessage = "Au revoir.";
        String expectedRes = "Oh non, c'est trop triste de se quitter !";

        when(strategiesMock.get(0).generateResponse(Messages, usermessage)).thenReturn(null);
        when(strategiesMock.get(1).generateResponse(Messages, usermessage)).thenReturn(expectedRes);

        String result = respoGen.generateElizaResponse(Messages, usermessage);

        assertEquals(result, expectedRes);

    }
}