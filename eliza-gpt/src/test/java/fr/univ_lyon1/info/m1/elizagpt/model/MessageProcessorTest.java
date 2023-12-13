package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseGenerator;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageManager;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

import org.mockito.Mock;
/**
 * Tests for MessageProcessor.
 */
public class MessageProcessorTest {

    MessageProcessor Messpro;
    @Mock
    MessageManager messageManagerMock;
    @Mock
    SearchStrategy searchStrategyMock;
    @Mock
    ResponseGenerator responseGeneratorMock;
    ArrayList< Message > Messages;
    @BeforeEach
    public void setUp(){
        messageManagerMock = mock(MessageManager.class);
        searchStrategyMock = mock(SearchStrategy.class);
        responseGeneratorMock = mock(ResponseGenerator.class);
        Messages = new ArrayList<>();
        Messages.add(new Message("a test text !", Message.Sender.USER));
        Messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        Messages.add(new Message("another one !", Message.Sender.ELIZA));
        Messpro = new MessageProcessor(messageManagerMock, searchStrategyMock, responseGeneratorMock, Messages);
    }

    @Test
    void normalize() {
        String original = "test       text     !";
        String expected = "test text !";
        assertEquals(expected, Messpro.normalize(original));

        String original2 = "      another   test       text     .   ";
        String expected2 = "another test text .";
        assertEquals(expected2, Messpro.normalize(original2));

        String original3 = "  aaand    another   test       text     ,   ";
        String expected3 = "aaand another test text ,.";
        assertEquals(expected3, Messpro.normalize(original3));
    }

    @Test
    void addMessage() {
        // Arrange
        String input = "  test  input.   ";
        String normalizedInput = "test input.";
        Message.Sender sender = Message.Sender.USER;
        Message expectedMessage = new Message(normalizedInput, sender);

        when(messageManagerMock.addMessage(any(), eq(normalizedInput), eq(sender))).thenReturn(expectedMessage);

        // Act
        Message result = Messpro.addMessage(input, sender);

        // Assert
        assertEquals(expectedMessage.getText(), result.getText());
        assertEquals(expectedMessage.getSender(), result.getSender());
    }

    @Test
    void deleteMessage() {
        ArrayList< Message > expectedList = new ArrayList<>();
        expectedList.add(new Message("a test text !", Message.Sender.USER));
        expectedList.add(new Message("a new test text !", Message.Sender.ELIZA));
        int messageId = 2;
        Messpro.deleteMessage(messageId);
        verify(messageManagerMock).deleteMessage(Messages, messageId);
    }

    @Test
    void search() {
        //initialisation of the variables
        String ToSearch = "a new test text !";
        ArrayList< Message > expectedList = new ArrayList<>();
        expectedList.add(new Message(ToSearch, any()));

        when(searchStrategyMock.search(Messages, eq(ToSearch))).thenReturn(expectedList);
        ArrayList<Message> result = Messpro.search(ToSearch);

        verify(searchStrategyMock).search(any(), eq(ToSearch));

        assertEquals(result, expectedList);

    }

    @Test
    void generateElizaResponse() {
        String input = "  Quelle  est la   meilleure  équipe  du  foot \\?  ";
        String normalizedInput = Messpro.normalize(input);
        String expectedString = "Bien sûr c'est le Real Madrid !";

        when(responseGeneratorMock.generateElizaResponse(Messages, normalizedInput)).thenReturn(expectedString);
        String result = Messpro.generateElizaResponse(input);
        verify(responseGeneratorMock).generateElizaResponse(any(), eq(normalizedInput));
        assertEquals(expectedString, result);
    }

    @Test
    void getMessages() {
        ArrayList<Message> result = Messpro.getMessages();
        assertEquals(Messages, result);
    }
}
