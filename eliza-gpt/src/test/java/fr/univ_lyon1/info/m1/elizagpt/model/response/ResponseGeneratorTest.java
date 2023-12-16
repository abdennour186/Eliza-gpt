package fr.univ_lyon1.info.m1.elizagpt.model.response;


import fr.univ_lyon1.info.m1.elizagpt.model.response.handlers.BestClubHandler;
import fr.univ_lyon1.info.m1.elizagpt.model.response.handlers.ByeResponseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResponseGeneratorTest {
    private List<ResponseHandler> responseHandlers;
    private ResponseGenerator responseGenerator;
    @BeforeEach
    public void setUp() {
        ResponseHandler responseHandlerMock1 = mock(BestClubHandler.class);
        ResponseHandler responseHandlerMock2 = mock(ByeResponseHandler.class);
        responseHandlers = Arrays.asList(
                responseHandlerMock1,
                responseHandlerMock2
        );
        responseGenerator = new ResponseGenerator(responseHandlers);
    }
    @Test
    void generateElizaResponse() {
        String userMessage = "Au revoir.";
        String expectedRes = "Oh non, c'est trop triste de se quitter !";

        when(responseHandlers.get(0).handleResponse(userMessage)).thenReturn(expectedRes);

        String result = responseGenerator.generateElizaResponse(userMessage);

        assertEquals(result, expectedRes);

    }
}
