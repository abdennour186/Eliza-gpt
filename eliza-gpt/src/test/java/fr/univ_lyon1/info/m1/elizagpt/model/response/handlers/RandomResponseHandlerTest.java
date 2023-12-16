package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomResponseHandlerTest {

    private RandomResponseHandler randomResponseHandler;
    @Mock
    private BestClubHandler bestClubHandler;

    @BeforeEach
    public void setUp() {
        bestClubHandler = mock(BestClubHandler.class);
        randomResponseHandler = new RandomResponseHandler();
        randomResponseHandler.setNextHandler(bestClubHandler);
    }

    @Test
    void generateResponse() {
        String input = "a question?";
        String expectedResponse1 = "Il fait beau aujourd'hui, vous ne trouvez pas ?";
        String expectedResponse2 = "Je ne comprends pas.";
        String expectedResponse3 = "Hmmm, hmm ...";
        String expectedResponse4 = "Bien sûr c'est le Real Madrid !";

        when(bestClubHandler.handleResponse(input)).thenReturn(expectedResponse4);

        String result = randomResponseHandler.handleResponse(input);

        assertTrue(result.equals(expectedResponse1) || result.equals(expectedResponse2)
                    || result.equals(expectedResponse3) || result.equals(expectedResponse4));

    }
}
