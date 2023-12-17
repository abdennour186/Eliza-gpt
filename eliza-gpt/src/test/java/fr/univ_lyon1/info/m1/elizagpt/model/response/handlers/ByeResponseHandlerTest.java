package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ByeResponseHandlerTest {

    private ByeResponseHandler byeResponseHandler;


    @Mock
    private UserName userName;
    @BeforeEach
    public void setUp() {
        userName = mock(UserName.class);
        byeResponseHandler = new ByeResponseHandler(userName);
    }

    @Test
    void generateResponse() {
        when(userName.getUserName()).thenReturn(null);
        String input = "Au revoir.";
        String expectedRes  = "Oh non, c'est trop triste de se quitter !";
        String result = byeResponseHandler.handleResponse(input);
        assertEquals(result, expectedRes);
    }
    @Test
    void generateResponseWithName() {
        when(userName.getUserName()).thenReturn("abdennour");
        String input = "Au revoir.";
        String expectedRes  = "Oh non, c'est trop triste de se quitter abdennour !";
        String result = byeResponseHandler.handleResponse(input);
        assertEquals(expectedRes, result);
    }
}
