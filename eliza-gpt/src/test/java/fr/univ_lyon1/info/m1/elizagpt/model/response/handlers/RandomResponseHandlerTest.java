package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomResponseHandlerTest {

    RandomResponseHandler randomResponseHandler;

    @BeforeEach
    public void setUp(){
        randomResponseHandler = new RandomResponseHandler();
    }

    @Test
    void generateResponse() {
        String input = "a question?";
        String expectedResponse1  = "Il fait beau aujourd'hui, vous ne trouvez pas ?";
        String expectedResponse2  ="Je ne comprends pas.";
        String expectedResponse3  ="Hmmm, hmm ...";
        String result = randomResponseHandler.handleResponse(input);

        assertTrue(result.equals(expectedResponse1) || result.equals(expectedResponse2 ) || result.equals(expectedResponse3));

    }
}