package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class NameResponseHandlerTest {

    NameResponseHandler nameResponseHandler;


    @Mock
    UserName userName;
    @BeforeEach
    public void setUp(){
        userName = mock(UserName.class);
        nameResponseHandler = new NameResponseHandler(userName);
    }

    @Test
    void generateResponse() {
        String input = "Je m'appelle abdennour.";
        String expectedResponse  = "Bonjour abdennour.";
        String result = nameResponseHandler.handleResponse(input);

        assertEquals(result,expectedResponse);
    }
}