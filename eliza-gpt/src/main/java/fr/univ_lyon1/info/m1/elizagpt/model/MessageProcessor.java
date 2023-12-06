package fr.univ_lyon1.info.m1.elizagpt.model;



import fr.univ_lyon1.info.m1.elizagpt.model.response.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The MessageProcessor class is responsible for processing messages in a chatbot system.
 * It offers functionalities such as normalizing text, adding and deleting messages,
 * searching for messages, generating responses, and more. This class acts as a core component
 * in managing and interpreting user interactions.
 */
public class MessageProcessor {

    private final ArrayList<Message> messages;
    private final MessageManager messageManager;

    private final MessageGenerator messageGenerator;


    /**
     * Constructs a new MessageProcessor with an empty list of messages.
     */
    public MessageProcessor() {
        this.messages = new ArrayList<>();
        this.messageManager = new MessageManager();
        this.messageGenerator = new MessageGenerator(
                Arrays.asList(
                        new NameResponseStrategy(),
                        new UserNameResponseStrategy(),
                        new TeacherResponseStrategy(),
                        new VerbResponseStrategy(),
                        new QuestionResponseStrategy(),
                        new RandomResponseStrategy(),
                        new DefaultResponseStrategy()
                )
        );
    }

    /**
     * Normalizes the given text by trimming, removing extra spaces, and ensuring punctuation.
     *
     * @param text The text to be normalized.
     * @return The normalized text.
     */

    public String normalize(final String text) {
        return text.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "")
                .replaceAll("[^\\.!?:]$", "$0.");
    }


    /**
     * Adds a message to the list of messages after normalizing it.
     *
     * @param text The text of the message to be added.
     * @param sender The sender of the message.
     * @return The newly created and added Message object.
     */
    public Message addMessage(final String text, final Message.Sender sender) {
        String normalizedText = normalize(text);
        return this.messageManager.addMessage(messages , normalizedText , sender);
    }

    /**
     * Deletes a message from the list based on its ID.
     *
     * @param messageId The ID of the message to be deleted.
     */
    public void deleteMessage(final int messageId) {
        this.messageManager.deleteMessage(messages , messageId);
    }
    /**
     * Searches for messages containing the specified text.
     *
     * @param text The text to search for within messages.
     * @return A list of messages that contain the specified text.
     */
    public ArrayList<Message> search(final String text) {
        ArrayList<Message> result = new ArrayList<>();
        Pattern pattern;
        Matcher matcher;
        for (Message message : messages) {
            pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(message.getText());
            if (matcher.find()) {
                result.add(message);
            }
        }
        return result;
    }

    /**
     * Generates a response from Eliza to the user's message.
     *
     * @param userMessage The user's message to respond to.
     * @return Eliza's response to the user's message.
     */
    public String generateElizaResponse(final String userMessage) {
        String normalizedText = normalize(userMessage);
        return messageGenerator.generateElizaResponse(messages,normalizedText);
    }


    /**
     * Gets the list of all messages.
     *
     * @return The list of messages.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }




}
