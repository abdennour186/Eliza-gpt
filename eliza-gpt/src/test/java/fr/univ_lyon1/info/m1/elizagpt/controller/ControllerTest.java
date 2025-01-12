package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;


class ControllerTest {
    private Controller controller;
    @Mock
    private MessageProcessor messageProcessorMock;
    @Mock
    private SearchStrategy searchStrategyMock;
    @BeforeEach
    public void setUp() {
        messageProcessorMock = mock(MessageProcessor.class);
        controller = new Controller(messageProcessorMock);
    }
    @Test
    void addUserMessage() {
        String inputMessage = "a new message";
        Message expectedMessage = new Message(inputMessage, Message.Sender.USER);
        when(messageProcessorMock.addMessage(inputMessage, Message.Sender.USER))
                .thenReturn(expectedMessage);
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
