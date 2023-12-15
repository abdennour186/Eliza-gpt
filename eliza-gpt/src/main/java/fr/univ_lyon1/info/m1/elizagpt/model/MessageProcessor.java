package fr.univ_lyon1.info.m1.elizagpt.model;



import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageManager;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseGenerator;
import fr.univ_lyon1.info.m1.elizagpt.model.response.handlers.*;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.SubStringSearchStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The MessageProcessor class is responsible for processing messages in a chatbot system.
 * It offers functionalities such as normalizing text, adding and deleting messages,
 * searching for messages, generating responses, and more. This class acts as a core component
 * in managing and interpreting user interactions.
 */
public class MessageProcessor implements UserName{

    private final List<Message> messages;
    private final MessageManager messageManager;

    private final ResponseGenerator responseGenerator;


    private SearchStrategy searchStrategy;

    private String userName;




    /**
     * Constructs a new MessageProcessor with an empty list of messages.
     */
    public MessageProcessor() {
        this.messages = new ArrayList<>();
        this.messageManager = new MessageManager(this.messages);
        this.responseGenerator = new ResponseGenerator(
                Arrays.asList(
                        new NameResponseHandler(this),
                        new UserNameResponseHandler(this),
                        new TeacherResponseHandler(),
                        new VerbResponseHandler(),
                        new BestClubHandler(),
                        new QuestionResponseHandler(),
                        new ByeResponseHandler(this),
                        new RandomResponseHandler(),
                        new DefaultResponseHandler(this)
                )
        );
        this.addMessage("Bonjour" , Message.Sender.ELIZA);
        this.searchStrategy = SubStringSearchStrategy.getInstance();

    }
    public MessageProcessor(MessageManager messManage, SearchStrategy searchStrategy, ResponseGenerator response, ArrayList<Message> messages) {
        this.messages = messages;
        this.messageManager = messManage;
        this.responseGenerator = response;
        this.searchStrategy = searchStrategy;
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
        return this.messageManager.addMessage(normalizedText , sender);
    }

    /**
     * Deletes a message from the list based on its ID.
     *
     * @param messageId The ID of the message to be deleted.
     */
    public void deleteMessage(final int messageId) {
        this.messageManager.deleteMessage(messageId);
    }
    /**
     * Searches for messages containing the specified text.
     *
     * @param text The text to search for within messages.
     * @return A list of messages that contain the specified text.
     */
    public List<Message> search(final String text) {
        return searchStrategy.search(messages,text);
    }

    /**
     * Generates a response from Eliza to the user's message.
     *
     * @param userMessage The user's message to respond to.
     * @return Eliza's response to the user's message.
     */
    public String generateElizaResponse(final String userMessage) {
        String normalizedText = normalize(userMessage);
        return responseGenerator.generateElizaResponse(normalizedText);
    }


    /**
     * Gets the list of all messages.
     *
     * @return The list of messages.
     */
    public List<Message> getMessages() {
        return messages;
    }
    public void setSearchStrategy(SearchStrategy strategy){
        this.searchStrategy = strategy;
    }

    public SearchStrategy getSearchStrategy() {return searchStrategy; }


    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String getUserName() {
        return this.userName;
    }
}
