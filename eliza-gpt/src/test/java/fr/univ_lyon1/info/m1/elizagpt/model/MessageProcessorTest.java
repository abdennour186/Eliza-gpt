package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseGenerator;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageManager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
/**
 * Tests for MessageProcessor.
 */
public class MessageProcessorTest {

    private MessageProcessor messageProcessor;
    @Mock
    private MessageManager messageManagerMock;
    @Mock
    private SearchStrategy searchStrategyMock;
    @Mock
    private ResponseGenerator responseGeneratorMock;
    private ArrayList<Message> messages;
    @BeforeEach
    public void setUp() {
        messageManagerMock = mock(MessageManager.class);
        searchStrategyMock = mock(SearchStrategy.class);
        responseGeneratorMock = mock(ResponseGenerator.class);
        messages = new ArrayList<>();
        messages.add(new Message("a test text !", Message.Sender.USER));
        messages.add(new Message("a new test text !", Message.Sender.ELIZA));
        messages.add(new Message("another one !", Message.Sender.ELIZA));
        messageProcessor = new MessageProcessor(messageManagerMock, searchStrategyMock,
                responseGeneratorMock, messages);
    }

    @Test
    void normalize() {
        String original = "test       text     !";
        String expected = "test text !";
        assertEquals(expected, messageProcessor.normalize(original));

        String original2 = "      another   test       text     .   ";
        String expected2 = "another test text .";
        assertEquals(expected2, messageProcessor.normalize(original2));

        String original3 = "  aaand    another   test       text     ,   ";
        String expected3 = "aaand another test text ,.";
        assertEquals(expected3, messageProcessor.normalize(original3));
    }

    @Test
    void addMessage() {
        // Arrange
        String input = "  test  input.   ";
        String normalizedInput = "test input.";
        Message.Sender sender = Message.Sender.USER;
        Message expectedMessage = new Message(normalizedInput, sender);

        when(messageManagerMock.addMessage(eq(normalizedInput),
                eq(sender))).thenReturn(expectedMessage);

        // Act
        Message result = messageProcessor.addMessage(input, sender);

        // Assert
        assertEquals(expectedMessage.getText(), result.getText());
        assertEquals(expectedMessage.getSender(), result.getSender());
    }

    @Test
    void deleteMessage() {
        ArrayList<Message> expectedList = new ArrayList<>();
        expectedList.add(new Message("a test text !", Message.Sender.USER));
        expectedList.add(new Message("a new test text !", Message.Sender.ELIZA));
        int messageId = 2;
        messageProcessor.deleteMessage(messageId);
        verify(messageManagerMock).deleteMessage(messageId);
    }

    @Test
    void search() {
        //initialisation of the variables
        String toSearch = "a new test text !";
        ArrayList<Message> expectedList = new ArrayList<>();
        expectedList.add(new Message(toSearch, any()));

        when(searchStrategyMock.search(messages, eq(toSearch))).thenReturn(expectedList);
        List<Message> result = messageProcessor.search(toSearch);

        verify(searchStrategyMock).search(any(), eq(toSearch));

        assertEquals(result, expectedList);

    }

    @Test
    void generateElizaResponse() {
        String input = "  Quelle  est la   meilleure  équipe  du  foot \\?  ";
        String normalizedInput = messageProcessor.normalize(input);
        String expectedString = "Bien sûr c'est le Real Madrid !";

        when(responseGeneratorMock.generateElizaResponse(normalizedInput)).
                thenReturn(expectedString);
        String result = messageProcessor.generateElizaResponse(input);
        verify(responseGeneratorMock).generateElizaResponse(eq(normalizedInput));
        assertEquals(expectedString, result);
    }

    @Test
    void getMessages() {
        List<Message> result = messageProcessor.getMessages();
        assertEquals(messages, result);
    }
}
