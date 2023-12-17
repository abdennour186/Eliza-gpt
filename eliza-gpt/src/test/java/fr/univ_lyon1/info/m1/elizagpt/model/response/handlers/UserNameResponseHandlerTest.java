package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserNameResponseHandlerTest {

    private UserNameResponseHandler nameResponseHandler;
    @Mock
    private UserName userName;
    @BeforeEach
    public void setUp() {
        userName = mock(UserName.class);
        nameResponseHandler = new UserNameResponseHandler(userName);
    }

    @Test
    void generateResponse() {
        when(userName.getUserName()).thenReturn(null);
        String input = "Quel est mon nom ?";
        String expectedResponse  = "Je ne connais pas votre nom.";
        String result = nameResponseHandler.handleResponse(input);

        assertEquals(result, expectedResponse);
    }
    @Test
    void generateResponseWithName() {
        when(userName.getUserName()).thenReturn("abdennour");
        String input = "Quel est mon nom ?";
        String expectedResponse  = "Votre nom est abdennour.";
        String result = nameResponseHandler.handleResponse(input);
        assertEquals(expectedResponse, result);
    }
}
