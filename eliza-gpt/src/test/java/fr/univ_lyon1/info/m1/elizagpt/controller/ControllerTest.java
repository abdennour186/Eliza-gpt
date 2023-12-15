package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageManager;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerTest {
    Controller controller;
    ArrayList<Message> Messages;
    @Mock
    MessageProcessor messageProcessorMock;
    @Mock
    Subject subjectMock;
    @Mock
    SearchStrategy searchStrategyMock;
    @BeforeEach
    public void setUp(){
        messageProcessorMock = mock(MessageProcessor.class);
        subjectMock = mock(Subject.class);
        controller = new Controller(messageProcessorMock);
    }
    @Test
    void addUserMessage() {
        String inputMessage = "a new message";
        controller.addUserMessage(inputMessage);

        verify(messageProcessorMock).addMessage(inputMessage, Message.Sender.USER);
    }

    @Test
    void addElizaMessage() {
        String inputMessage = "a new message";
        controller.addElizaMessage(inputMessage);

        verify(messageProcessorMock).addMessage(inputMessage, Message.Sender.ELIZA);
    }

    @Test
    void search() {
        String inputMessage = "a message to search";
        controller.search(inputMessage);

        verify(messageProcessorMock).search(inputMessage);
    }

    @Test
    void deleteMessage() {
        int messageID = 2;
        controller.deleteMessage(messageID);

        verify(messageProcessorMock).deleteMessage(messageID);
    }

    @Test
    void generateElizaResponse() {
        String usermessge = "Quelle est la meilleure équipe du foot ?";
        String expectedString = "Bien sûr c'est le Real Madrid !";

        when(messageProcessorMock.generateElizaResponse(usermessge)).thenReturn(expectedString);
        String result = controller.generateElizaResponse(usermessge);

        verify(messageProcessorMock).generateElizaResponse(usermessge);
        assertEquals(expectedString, result);
    }

    @Test
    void setSearchStrategy() {
        controller.setSearchStrategy(searchStrategyMock);

        verify(messageProcessorMock).setSearchStrategy(searchStrategyMock);
        assertSame(searchStrategyMock, messageProcessorMock.getSearchStrategy());
    }
}