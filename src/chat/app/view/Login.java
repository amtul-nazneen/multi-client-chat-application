package chat.app.view;

import chat.app.LaunchChatApp;
import chat.app.common.Constants;
import chat.app.common.Logger;
import chat.app.view.common.AlertsHelper;
import chat.app.view.common.ViewConstants;
import chat.app.view.common.ViewUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author amtul.nazneen
 */
public class Login {

    private GridPane loginPane = null;
    private TextField userNameInput;
    private Button loginButton;

    private LaunchChatApp runningProgram;

    public Login(LaunchChatApp mainApp) {
	super();
	this.runningProgram = mainApp;
    }

    public GridPane getOrCreateLoginLayout() {
	Logger.logViewInfo("In Login.getOrCreateLoginLayout(): ");
	if (loginPane != null) {
	    return loginPane;
	} else {
	    createLoginViewLayout();
	    return loginPane;
	}
    }

    private void createLoginViewLayout() {
	loginPane = new GridPane();
	GridPane gridPane = createLoginFormPane();
	addUIControls(gridPane);
	loginPane.getChildren().add(gridPane);
	loginPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private GridPane createLoginFormPane() {
	GridPane gridPane = new GridPane();
	gridPane.setAlignment(Pos.CENTER);
	gridPane.setPadding(new Insets(40, 40, 40, 40));
	gridPane.setHgap(10);
	gridPane.setVgap(10);

	ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
	columnOneConstraints.setHalignment(HPos.RIGHT);
	ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
	columnTwoConstrains.setHgrow(Priority.ALWAYS);

	gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
	return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
	Label headerLabel = new Label(Constants.LW_LABEL);
	headerLabel.setFont(Font.font(ViewConstants.LW_ARIAL_FONT, FontWeight.BOLD, 24));
	gridPane.add(headerLabel, 0, 0, 2, 1);
	GridPane.setHalignment(headerLabel, HPos.CENTER);
	GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

	Label nameLabel = new Label(Constants.LW_USERNAME);
	nameLabel.setFont(Font.font(ViewConstants.LW_ARIAL_FONT, FontWeight.BOLD, FontPosture.REGULAR, 14));
	gridPane.add(nameLabel, 0, 1);

	userNameInput = new TextField();
	userNameInput.setPrefHeight(40);
	userNameInput.setPromptText(Constants.LW_USERNAME_PROMPT);
	gridPane.add(userNameInput, 1, 1);

	Label passwordLabel = new Label(Constants.LW_PASSWORD);
	passwordLabel.setFont(Font.font(ViewConstants.LW_ARIAL_FONT, FontWeight.BOLD, FontPosture.REGULAR, 14));
	gridPane.add(passwordLabel, 0, 3);

	PasswordField passwordField = new PasswordField();
	passwordField.setPrefHeight(40);
	gridPane.add(passwordField, 1, 3);

	loginButton = new Button(Constants.LW_LOGIN);
	loginButton.setPrefHeight(40);
	loginButton.setDefaultButton(true);
	loginButton.setPrefWidth(100);
	loginButton.setOnAction(loginButtonEvent);
	loginButton.setFont(Font.font(ViewConstants.LW_ARIAL_FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
	loginButton.setStyle(ViewConstants.LW_LOGIN_BUTTON_STYLE);
	gridPane.add(loginButton, 0, 4, 2, 1);
	GridPane.setHalignment(loginButton, HPos.CENTER);
	GridPane.setMargin(loginButton, new Insets(20, 0, 20, 0));

    }

    private EventHandler<ActionEvent> loginButtonEvent = new EventHandler<ActionEvent>() {
	@Override
	public void handle(ActionEvent event) {
	    Logger.logViewInfo("In Login: Event Handler after logging in: ");
	    String userName = null;
	    try {
		loginButton.setEffect(new DropShadow());
		userName = userNameInput.getText();
		runningProgram.setUserName(userName);
		runningProgram.getChatClientInstance().start();
	    } catch (Exception e) {
		AlertsHelper.displayErrorAlertOnServerDown();
		return;
	    }
	    AlertsHelper.displayInfoAlertOnLogin();
	    runningProgram.setChatWindowPostLogin(runningProgram.getUserChatWindow());
	    runningProgram.getStage().setTitle(ViewUtil.getChatWindowTitle(userName));
	}
    };
}
