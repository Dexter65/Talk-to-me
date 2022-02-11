package client.ui;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import messages.IMessageInput;
import messages.IMessageViewer;
import messages.Message;
import messages.UIMessageViewer;

import java.io.BufferedInputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class MainWindowController implements IMessageViewer, IMessageInput {
    private static MainWindowController mainWindowController;
    private String message = null;

    public MainWindowController() {
        mainWindowController = this;
    }

    @FXML
    private TextField userInput;
    @FXML
    private TextArea chatOutput;

    @FXML
    private void onKeyTypedUserInput(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    @FXML
    private void onMouseClickedSendBtn(MouseEvent event) {
        sendMessage();
    }

    private void sendMessage() {
        message = userInput.getText();
        userInput.clear();
    }

    public static MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    @Override
    public void viewMessage(Message message) {
//        chatOutput.appendText(message.getMessageText() + '\n');
        chatOutput.appendText("[" + message.getFrom() + "] " + message.getMessageText() + '\n');
    }

    @Override
    public void viewMessage(String message) {
        viewMessage(new Message(message));
    }

    @Override
    public String getNextLine() {
        while (message == null) {
            synchronized (this) {

            }
        }

        String tmpMessage = message;
        message = null;
        return tmpMessage;
    }
}
