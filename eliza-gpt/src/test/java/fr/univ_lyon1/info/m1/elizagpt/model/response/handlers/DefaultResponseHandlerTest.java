package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultResponseHandlerTest {

    private DefaultResponseHandler defaultResponseHandler;

    @Mock
    private UserName userName;

    @BeforeEach
    public void setUp() {
        userName = mock(UserName.class);
        defaultResponseHandler = new DefaultResponseHandler(userName);
    }

    @Test
    void generateResponse() {
        String input = "test input";
        String expectedResponse  = "Qu'est-ce qui vous fait dire cela ?";
        String result = defaultResponseHandler.handleResponse(input);

        assertEquals(result, expectedResponse);
    }
    @Test
    void generateResponseWithName() {
        when(userName.getUserName()).thenReturn("abdennour");
        String input = "test input";
        String expectedResponse  = "Qu'est-ce qui vous fait dire cela, abdennour ?";
        String result = defaultResponseHandler.handleResponse(input);
        assertEquals(expectedResponse, result);
    }
}
