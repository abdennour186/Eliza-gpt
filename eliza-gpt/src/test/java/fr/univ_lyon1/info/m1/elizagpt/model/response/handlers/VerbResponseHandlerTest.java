package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VerbResponseHandlerTest {
    private VerbResponseHandler verbResponseHandler;
    @Mock
    private BestClubHandler bestClubHandlerMock;
    @BeforeEach
    public void setUp() {
        bestClubHandlerMock = mock(BestClubHandler.class);
        verbResponseHandler = new VerbResponseHandler();
        verbResponseHandler.setNextHandler(bestClubHandlerMock);
    }

    @Test
    void generateResponse() {
        String userMessage = "Je suis intelligent.";
        String response = verbResponseHandler.handleResponse(userMessage);
        String expectedResponse1  = "Pourquoi dites-vous que vous êtes intelligent ?";
        String expectedResponse2  = "Pourquoi pensez-vous que vous êtes intelligent ?";
        String expectedResponse3  = "Êtes-vous sûr que vous êtes intelligent ?";

        assertTrue(response.equals(expectedResponse1) || response.equals(expectedResponse2)
                || response.equals(expectedResponse3));
        userMessage = "Quelle est la meilleure équipe du foot ?";
        String expectedResult = "Bien sûr c'est le Real Madrid !";
        when(bestClubHandlerMock.handleResponse(userMessage)).thenReturn(expectedResult);
        response = verbResponseHandler.handleResponse(userMessage);
        assertEquals(expectedResult, response);
    }

    @Test
    void firstToSecondPerson() {
        //assertEquals("vous avez un livre", ver_obj.firstToSecondPerson("j'ai un livre"));
        assertEquals("vous savez nager",
                verbResponseHandler.firstToSecondPerson("je sais nager"));
        assertEquals("vous devez étudier",
                verbResponseHandler.firstToSecondPerson("je dois étudier"));
        //assertEquals("vous m'aidez", ver_obj.firstToSecondPerson("tu m'aides"));
    }
}
