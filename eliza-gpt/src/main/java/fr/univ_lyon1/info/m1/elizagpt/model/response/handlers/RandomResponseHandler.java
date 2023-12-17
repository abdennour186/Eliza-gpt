package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;


import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;


/**
 * The RandomResponseHandler class represents a response handler that generates random responses.
 * It extends the RandomResponse class, providing a random selection of predefined responses.
 *
 * @version 1.0
 */
public class RandomResponseHandler extends RandomResponse implements ResponseHandler {

    private ResponseHandler nextHandler;

    /**
     * Generates a random response based on predefined options.
     *
     * @param userMessage The user's message (not used in this implementation).
     * @return A randomly selected response.
     */
    @Override
    public String handleResponse(final String userMessage) {
        if (this.getRandom().nextBoolean()) {
            return "Il fait beau aujourd'hui, vous ne trouvez pas ?";
        }
        if (this.getRandom().nextBoolean()) {
            return "Je ne comprends pas.";
        }
        if (this.getRandom().nextBoolean()) {
            return "Hmmm, hmm ...";
        }
        return nextHandler.handleResponse(userMessage);
    }

    /**
     * Sets the next handler in the chain.
     *
     * @param handler The next response handler.
     */
    @Override
    public void setNextHandler(final ResponseHandler handler) {
        this.nextHandler = handler;
    }
}
