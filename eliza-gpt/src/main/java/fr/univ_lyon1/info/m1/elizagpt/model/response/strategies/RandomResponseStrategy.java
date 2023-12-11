package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseStrategy;

import java.util.ArrayList;

public class RandomResponseStrategy extends RandomResponse implements ResponseStrategy {

    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {

        if (random.nextBoolean()) {
            return "Il fait beau aujourd'hui, vous ne trouvez pas ?";
        }
        if (random.nextBoolean()) {
            return "Je ne comprends pas.";
        }
        if (random.nextBoolean()) {
            return "Hmmm, hmm ...";
        }
        return null;
    }
}
