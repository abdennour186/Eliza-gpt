package fr.univ_lyon1.info.m1.elizagpt.model.response;

/**
 * The interface for handling responses in the ElizaGPT application.
 * Implementations of this interface define how responses are generated based on user input.
 *
 * <p>Classes that implement this interface should provide logic for generating responses
 * and optionally set the next handler in a chain of responsibility.</p>
 *
 * <p>This interface helps achieve the Chain of Responsibility design pattern,
 * where each handler in the chain can process the request
 * and pass it along the chain if needed.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler
 * @version 1.0
 */
public interface ResponseHandler {

    /**
     * Generates a response based on the user's input.
     *
     * @param userMessage The user's input message.
     * @return The generated response.
     */
    String handleResponse(String userMessage);

    /**
     * Sets the next handler in the chain of responsibility.
     *
     * @param handler The next handler in the chain.
     */
    void setNextHandler(ResponseHandler handler);
}
