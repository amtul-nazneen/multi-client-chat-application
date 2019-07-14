package chat.app.view;

import java.io.File;

import chat.app.LaunchChatApp;
import chat.app.common.Constants;
import chat.app.common.Logger;
import chat.app.view.common.ViewConstants;
import chat.app.view.common.ViewUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author amtul.nazneen
 */
public class ChatWindow {

    private AnchorPane chatWindowPane = null;
    private TextArea conversationText;
    private TextArea inputArea;
    private Button chooseFile;
    private File selectedFile;
    private Stage stage;

    private LaunchChatApp runningProgram;

    public ChatWindow(LaunchChatApp mainApp, Stage stage) {
	super();
	this.runningProgram = mainApp;
	this.stage = stage;
    }

    public AnchorPane getOrCreateChatLayout() {
	Logger.logViewInfo("In ChatWindow.getOrCreateChatLayout(): ");
	if (chatWindowPane != null) {
	    return chatWindowPane;
	} else {
	    createChatWindowLayout();
	    return chatWindowPane;
	}
    }

    private EventHandler<KeyEvent> textEnterEvent = new EventHandler<KeyEvent>() {
	@Override
	public void handle(KeyEvent event) {
	    if (event.getCode() == KeyCode.ENTER) {
		Logger.logViewInfo("In ChatWindow: Event Handler after sending a message: ");
		String message = inputArea.getText() + Constants.NEW_LINE;
		String messageToDisplay = ViewUtil.getMessageToDisplay(runningProgram.getUsername().trim(), message);
		runningProgram.getChatClientInstance().relayMessage(messageToDisplay);
		conversationText.appendText(ViewUtil.getMessageToDisplay(Constants.SELF, message));
		inputArea.clear();
		event.consume();
	    }
	}
    };

    private EventHandler<ActionEvent> chooseFileEvent = new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
	    Logger.logViewInfo("In ChatWindow: Event Handler after sending a file: ");
	    try {
		chooseFile.setEffect(new DropShadow());
		FileChooser fileChooser = new FileChooser();
		selectedFile = fileChooser.showOpenDialog(stage);
		System.out.println(selectedFile.getAbsolutePath());
		System.out.println(selectedFile.getName());
		String messageToDisplay = ViewUtil.getMessageToDisplay(runningProgram.getUsername().trim(),
			selectedFile.getAbsolutePath());
		runningProgram.getChatClientInstance().relayMessage(messageToDisplay);
		conversationText
			.appendText(ViewUtil.getMessageToDisplay(Constants.SELF, selectedFile.getAbsolutePath()));
		chooseFile.setEffect(null);
	    } catch (Exception e) {
		return;
	    }
	}
    };

    public void addTextToChatWindow(String message) {
	conversationText.appendText(message);
    }

    private void createChatWindowLayout() {
	chatWindowPane = new AnchorPane();
	chatWindowPane
		.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	/* Creating a conversationText area */
	conversationText = new TextArea();
	conversationText.setFocusTraversable(false);
	conversationText.setEditable(false);
	conversationText.setMinHeight(ViewConstants.CW_CONV_AREA_HEIGHT);
	conversationText.setWrapText(true);
	conversationText.setFont(Font.font(ViewConstants.CW_VERDANA_FONT, FontWeight.BOLD, FontPosture.REGULAR, 16));
	AnchorPane.setTopAnchor(conversationText, 10.0);
	AnchorPane.setRightAnchor(conversationText, 10.0);
	AnchorPane.setLeftAnchor(conversationText, 10.0);
	chatWindowPane.getChildren().add(conversationText);

	inputArea = new TextArea();
	inputArea.setMaxHeight(ViewConstants.CW_INPUT_AREA_HEIGHT);
	inputArea.setWrapText(true);
	inputArea.setFont(Font.font(ViewConstants.CW_VERDANA_FONT, FontWeight.MEDIUM, FontPosture.REGULAR, 16));
	inputArea.addEventHandler(KeyEvent.KEY_PRESSED, textEnterEvent);

	AnchorPane.setBottomAnchor(inputArea, 10.0);
	AnchorPane.setLeftAnchor(inputArea, 10.0);
	AnchorPane.setRightAnchor(inputArea, 120.0);

	chatWindowPane.getChildren().add(inputArea);
	chooseFile = new Button(Constants.SEND_FILE);
	chooseFile.setPrefHeight(30);
	chooseFile.setDefaultButton(true);
	chooseFile.setPrefWidth(20);
	chooseFile.setOnAction(chooseFileEvent);
	chooseFile.setFont(Font.font(ViewConstants.LW_ARIAL_FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
	chooseFile.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	chatWindowPane.getChildren().add(chooseFile);

	AnchorPane.setBottomAnchor(chooseFile, 35.0);
	AnchorPane.setLeftAnchor(chooseFile, 690.0);
	AnchorPane.setRightAnchor(chooseFile, 10.0);

    }

}
