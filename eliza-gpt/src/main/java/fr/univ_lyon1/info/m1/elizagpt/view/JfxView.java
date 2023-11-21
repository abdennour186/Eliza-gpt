package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class JfxView implements ViewObserver{
    private final VBox dialog;
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;
    private final Controller controller;


    /**
     * Create the main view of the application.
     */
    // TODO: style error in the following line. Check that checkstyle finds it, and then fix it.
    public JfxView(final Stage stage, final int width, final int height , Controller controller) {

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
        //replyToUser("Bonjour");

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
     * @return
     */
    // not working properly (always returns null : to be fixed later)

    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = new TextField();
        searchText.setOnAction(e -> searchText(searchText));
        firstLine.getChildren().add(searchText);
        final Button send = new Button("Search");
        send.setOnAction(e -> {
            searchText(searchText);
        });
        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> {
            controller.undoSearch();
            //throw new UnsupportedOperationException("TODO: implement undo for search");
        });
        secondLine.getChildren().addAll(send, searchTextLabel, undo);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }

    private void searchText(final TextField text) {
        String currentSearchText = text.getText();
        if (currentSearchText == null) {
            searchTextLabel.setText("No active search");
        } else {
            searchTextLabel.setText("Searching for: " + currentSearchText);
        }
        controller.search(currentSearchText);
        text.setText("");
    }

    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();
        text.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });
        final Button send = new Button("Send");
        send.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });
        input.getChildren().addAll(text, send);
        return input;
    }

    @Override
    public void onUserAddUpdate(Payload payload) {
        HBox hBox = new HBox();
        final Label label = new Label(payload.getNewMessage());
        hBox.getChildren().add(label);
        label.setStyle(USER_STYLE);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        dialog.getChildren().add(hBox);
        hBox.setOnMouseClicked(e -> {
            controller.deleteMessage(dialog.getChildren().indexOf(hBox));
        });
    }

    @Override
    public void onElizaAddUpdate(Payload payload) {
        HBox hBox = new HBox();
        final Label label = new Label(payload.getNewMessage());
        hBox.getChildren().add(label);
        label.setStyle(ELIZA_STYLE);
        hBox.setAlignment(Pos.BASELINE_LEFT);
        dialog.getChildren().add(hBox);
        hBox.setOnMouseClicked(e -> {
            controller.deleteMessage(dialog.getChildren().indexOf(hBox));
        });
    }

    @Override
    public void onDeleteUpdate(Payload payload) {
        HBox toBeDeleted = (HBox) dialog.getChildren().get(payload.getDeletedMessageIndex());
        dialog.getChildren().remove(toBeDeleted);
    }

    @Override
    public void onSearchUpdate(Payload payload) {
         ArrayList<Message> searchResult = payload.getSearchResult();
         System.out.println(searchResult);
         List<HBox> result = new ArrayList<>();
         for(Message message : searchResult){
             HBox hBox = new HBox();
             final Label label = new Label(message.getText());
             label.setStyle(
                     message.getSender() == Message.Sender.ELIZA ?
                             ELIZA_STYLE : USER_STYLE
             );
             hBox.setAlignment(
                     message.getSender() == Message.Sender.ELIZA ?
                             Pos.BASELINE_LEFT : Pos.BASELINE_RIGHT
             );
             result.add(hBox);
         }
         dialog.getChildren().clear();
         dialog.getChildren().addAll(result);
    }

    @Override
    public void onUndoSearchUpdate(Payload payload) {
        ArrayList<Message> searchResult = payload.getSearchResult();
        System.out.println(searchResult);
        List<HBox> result = new ArrayList<>();
        for(Message message : searchResult){
            HBox hBox = new HBox();
            final Label label = new Label(message.getText());
            label.setStyle(
                    message.getSender() == Message.Sender.ELIZA ?
                            ELIZA_STYLE : USER_STYLE
            );
            hBox.setAlignment(
                    message.getSender() == Message.Sender.ELIZA ?
                            Pos.BASELINE_LEFT : Pos.BASELINE_RIGHT
            );
            result.add(hBox);
        }
        dialog.getChildren().clear();
        dialog.getChildren().addAll(result);
    }
}
