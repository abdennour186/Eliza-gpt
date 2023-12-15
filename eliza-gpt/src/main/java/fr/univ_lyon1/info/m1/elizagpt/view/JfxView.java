package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.payload.AddUpdate;
import fr.univ_lyon1.info.m1.elizagpt.payload.DeleteUpdate;
import fr.univ_lyon1.info.m1.elizagpt.payload.SearchUpdate;
import fr.univ_lyon1.info.m1.elizagpt.payload.Update;
import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.RegexSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.SearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.SubStringSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.search.strategies.WordSearchStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The JfxView class represents the JavaFX-based
 * graphical user interface for the Eliza GPT application.
 * It provides a chat-like interface where users
 * can interact with Eliza and view messages.
 */
public class JfxView implements ViewObserver {
    private final VBox dialog;
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;
    private final Controller controller;
    private ComboBox<SearchStrategy> searchComboBox = null;


    private final Map<Integer, HBox> messageToHbox = new HashMap<>();

    /**
     * Creates the main view of the application.
     *
     * @param stage      The JavaFX stage.
     * @param width      The width of the stage.
     * @param height     The height of the stage.
     * @param controller The controller managing the application logic.
     */
    public JfxView(final Stage stage, final int width,
                   final int height, final Controller controller) {
        this.controller = controller;
        controller.registerObserver(this);

        stage.setTitle("Eliza GPT");

        final VBox root = new VBox(10);

        final Pane search = createSearchWidget();
        root.getChildren().add(search);

        ScrollPane dialogScroll = new ScrollPane();
        dialog = new VBox(10);
        dialogScroll.setContent(dialog);
        // scroll to bottom by default:
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        root.getChildren().add(dialogScroll);
        dialogScroll.setFitToWidth(true);

        final Pane input = createInputWidget();
        root.getChildren().add(input);
        controller.undoSearch();
        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
    }

    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;

    private void replyToUser(final String text) {
        controller.addElizaMessage(text);
    }

    private void sendMessage(final String text) {
        controller.addUserMessage(text);
        String elizaResponse = controller.generateElizaResponse(text);
        replyToUser(elizaResponse);
    }

    /**
     * Extract the name of the user from the dialog.
     * TODO: this totally breaks the MVC pattern, never, ever, EVER do that.
     *
     * @return The extracted name of the user (not working properly, to be fixed later).
     */
    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = new TextField();
        searchText.setOnAction(e -> searchText());
        searchComboBox = new ComboBox<>();
        searchComboBox.getItems().addAll(
                SubStringSearchStrategy.getInstance(),
                RegexSearchStrategy.getInstance(),
                WordSearchStrategy.getInstance()
        );

        searchComboBox.setOnAction(e -> {
            SearchStrategy strategy = searchComboBox.getSelectionModel().getSelectedItem();
            controller.setSearchStrategy(strategy);
        });

        searchComboBox.setPromptText("Select search strategy");
        firstLine.getChildren().addAll(searchText , searchComboBox);

        final Button send = new Button("Search");
        send.setOnAction(e -> searchText());
        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> controller.undoSearch());
        secondLine.getChildren().addAll(send, searchTextLabel, undo);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }
    /**
     * Handles the user's action when initiating a search.
     * <p>
     * If the search text is not empty, this method triggers a search operation
     * through the controller, updates the search label, and clears the search text field.
     * If the search text is empty, it updates the search label to indicate no active search.
     */
    private void searchText() {
        String currentSearchText = this.searchText.getText();
        if (currentSearchText == null) {
            searchTextLabel.setText("No active search");
        } else {
            controller.search(currentSearchText);
            this.searchText.setText("");
        }
    }

    /**
     * Creates and returns a user input widget, consisting of a text field and a "Send" button.
     * <p>
     * The "Send" button triggers the sendMessage method when pressed, sending the text
     * from the input field to the controller. After sending, the input field is cleared.
     * <p>
     * This method encapsulates the creation and configuration of the user input components.
     *
     * @return A Pane containing the user input widget.
     */
    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();

        // Set an action event for the text field to handle pressing "Enter"
        text.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });

        final Button send = new Button("Send");

        // Set an action event for the "Send" button to handle clicks
        send.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });

        // Add the text field and the "Send" button to the input pane
        input.getChildren().addAll(text, send);

        return input;
    }


    /**
     * Handles the update when a new message is added.
     * Adds the new message to the user interface.
     *
     * @param update The update object containing information about the added message.
     * @throws IllegalArgumentException If the provided update is not of type {@link AddUpdate}.
     */
    @Override
    public void onMessageAddUpdate(final Update update) throws IllegalArgumentException {
        try {
            AddUpdate addUpdate = (AddUpdate) update;
            HBox newHbox = createHboxFromMessage(addUpdate.getNewMessage());
            messageToHbox.put(addUpdate.getNewMessage().getId(), newHbox);
            dialog.getChildren().add(newHbox);
        } catch (ClassCastException exception) {
            throw new IllegalArgumentException("Expected AddUpdate object but found another");
        }
    }



    /**
     * Handles the update when a message is deleted.
     * Removes the corresponding message from the user interface.
     *
     * @param update The update object containing information about the deleted message.
     * @throws IllegalArgumentException If the provided update is not of type {@link DeleteUpdate}.
     */
    @Override
    public void onDeleteUpdate(final Update update) throws IllegalArgumentException {
            try {
                DeleteUpdate deleteUpdate = (DeleteUpdate) update;
                int messageId = deleteUpdate.getDeletedMessageId();
                HBox toBeDeleted = messageToHbox.get(messageId);
                dialog.getChildren().remove(toBeDeleted);
                messageToHbox.remove(messageId);
            } catch (ClassCastException exception) {
                throw new IllegalArgumentException("Expected DeleteUpdate object but find another");
            }
    }

    /**
     * Handles the update when a search operation is performed.
     * Updates the search label with the search text and processes the search result.
     *
     * @param update The update object containing search-related information.
     * @throws IllegalArgumentException If the provided update is not of type {@link SearchUpdate}.
     */
    @Override
    public void onSearchUpdate(final Update update) throws IllegalArgumentException {
        try {
            SearchUpdate searchUpdate = (SearchUpdate) update;
            searchTextLabel.setText("Searching for: " + searchUpdate.getSearchText());
            List<Message> searchResult = searchUpdate.getSearchResult();
            processSearchResult(searchResult);
        } catch (ClassCastException exception) {
             throw new IllegalArgumentException("Expected SearchUpdate object but found another");
        }
    }


    /**
     * Handles the update when the user undoes a search operation.
     * Restores the original list of messages in the user interface.
     *
     * @param update The update object containing information for undoing the search.
     * @throws IllegalArgumentException If the provided update is not of type {@link SearchUpdate}.
     */
    @Override
    public void onUndoSearchUpdate(final Update update) throws IllegalArgumentException {
            try {
                SearchUpdate undoSearchUpdate = (SearchUpdate) update;
                searchTextLabel.setText(null);
                List<Message> allMessages = undoSearchUpdate.getSearchResult();
                processSearchResult(allMessages);
            } catch (ClassCastException exception) {
                throw new IllegalArgumentException("Expected SearchUpdate object"
                        + " but found another");
            }
    }


    /**
     * Creates an HBox from a message for display in the dialog.
     *
     * @param message The message to be displayed.
     * @return An HBox containing the message label with appropriate styling.
     */
    private HBox createHboxFromMessage(final Message message) {
        String messageText = message.getText();
        HBox parentHBox = new HBox();
        HBox hBox = new HBox();

        final Label label = new Label(messageText);


        parentHBox.setAlignment(
                message.getSender() == Message.Sender.ELIZA
                        ? Pos.BASELINE_LEFT : Pos.BASELINE_RIGHT
        );

        hBox.setStyle(
                message.getSender() == Message.Sender.ELIZA
                        ? ELIZA_STYLE : USER_STYLE
        );

        final Button button = new Button("x");
        button.setOnMouseClicked(e -> {
            controller.deleteMessage(message.getId());
        });
        hBox.getChildren().addAll(label , button);
        parentHBox.getChildren().add(hBox);

        return parentHBox;
    }


    /**
     * Processes the search result messages and displays them in the dialog.
     * Clears the existing mapping of message IDs to HBoxes, creates new HBoxes
     * from the search result messages, and adds them to the dialog.
     *
     * @param messages The list of messages resulting from a search operation.
     */
    private void processSearchResult(final List<Message> messages) {
        messageToHbox.clear();

        ArrayList<HBox> result = new ArrayList<>();
        for (Message message : messages) {
            HBox hBox = createHboxFromMessage(message);
            result.add(hBox);
            messageToHbox.put(message.getId(), hBox);
        }

        dialog.getChildren().clear();
        dialog.getChildren().addAll(result);
    }

}
