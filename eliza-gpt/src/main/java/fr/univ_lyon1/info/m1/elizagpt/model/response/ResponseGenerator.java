package fr.univ_lyon1.info.m1.elizagpt.model.response;

import java.util.List;

/**
 * The ResponseGenerator class is responsible for generating Eliza's responses in a chatbot system.
 * It utilizes a chain of responsibility pattern where each response handler in the chain processes
 * the user's message and provides a response. The handlers are set up during initialization to form
 * a chain, and the generated response is obtained from the first handler in the chain.
 *
 * @version 1.0
 */
public class ResponseGenerator {

    private final List<ResponseHandler> handlers;

    /**
     * Constructs a new ResponseGenerator with the specified list of response handlers.
     *
     * @param handlers The list of response handlers forming the chain.
     */
    public ResponseGenerator(final List<ResponseHandler> handlers) {
        this.handlers = handlers;
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNextHandler(handlers.get(i + 1));
        }
    }

    /**
     * Generates Eliza's response based on the user's input message.
     *
     * @param userMessage The user's input message.
     * @return Eliza's response to the user's message.
     */
    public String generateElizaResponse(final String userMessage) {
        return handlers.get(0).handleResponse(userMessage);
    }
}
