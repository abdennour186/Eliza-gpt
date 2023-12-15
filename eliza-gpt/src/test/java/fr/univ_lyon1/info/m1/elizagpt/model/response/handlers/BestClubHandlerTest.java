package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;


import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BestClubHandlerTest {
    BestClubHandler bestClubHandler;
    @Mock
    ResponseHandler responseHandlerMock;

    @BeforeEach
    public void setUp(){
        bestClubHandler = new BestClubHandler();
        responseHandlerMock = mock(ResponseHandler.class);
        bestClubHandler.setNextHandler(responseHandlerMock);
    }

    @Test
    void generateResponse() {
        String input = "Quelle est la meilleure équipe du foot ?";
        String expected_res  = "Bien sûr c'est le Real Madrid !";
        String result = bestClubHandler.handleResponse(input);

        assertEquals(result,expected_res);
    }

    @Test
    void generateFalseResponse(){
        String input = "Test input ?";
        String result = bestClubHandler.handleResponse(input);

        verify(responseHandlerMock).handleResponse(input);
    }
}