package chat.app.view.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import chat.app.LaunchChatApp;
import chat.app.common.Constants;
import chat.app.common.Logger;
import chat.app.model.client.ChatProgramClient;
import chat.app.view.ChatWindow;
import chat.app.view.Login;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author amtul.nazneen
 */
public class ViewUtil {

    public static Alert createErrorAlertPopup(String title, String errorBody) {
	Logger.logViewInfo("Launching Error Dialog from: ViewUtils.createErrorAlertPopup(): ");
	Alert errorPopup = new Alert(AlertType.ERROR);
	errorPopup.setTitle(Constants.ERROR);
	errorPopup.setHeaderText(title);
	errorPopup.setContentText(errorBody);
	return errorPopup;
    }

    public static Alert createInformationAlertPopup(String title, String errorBody) {
	Logger.logViewInfo("Launching Info Dialog from: ViewUtils.createInformationAlertPopup(): ");
	Alert infoPopup = new Alert(AlertType.INFORMATION);
	infoPopup.setTitle(Constants.INFORMATION);
	infoPopup.setHeaderText(title);
	infoPopup.setContentText(errorBody);
	return infoPopup;
    }

    public static void initialiseStage(LaunchChatApp chatProgram, Stage initialStage) {
	chatProgram.setBaseStage(initialStage);
	chatProgram.getBaseStage().setTitle(Constants.USER_WINDOW_TITLE);
	chatProgram.getBaseStage().setOnCloseRequest(e -> Platform.exit());
	chatProgram.getBaseStage().setResizable(Constants.FALSE);

	chatProgram.setChatClientInstance(new ChatProgramClient(chatProgram));
	chatProgram.setUserLoginWindow(new Login(chatProgram));
	chatProgram.setUserchatWindow(new ChatWindow(chatProgram, initialStage));
	chatProgram.setRootNode(new BorderPane());

	Scene myScene = new Scene(chatProgram.getRootNode(), ViewConstants.SCENE_WIDTH, ViewConstants.SCENE_HEIGHT);
	initialStage.setScene(myScene);
	initialStage.show();

	chatProgram.setLoginWindowOnLaunch(chatProgram.getUserLoginWindow());
    }

    public static String getTimeStamp() {
	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	// System.out.println(sdf.format(timestamp));
	return sdf.format(timestamp);
    }

    public static String getMessageToDisplay(String user, String message) {
	String updatedMessage = Constants.LEFT_BRACKET + ViewUtil.getTimeStamp() + Constants.RIGHT_BRACKET
		+ Constants.SPACE + user + Constants.COLON + message;
	return updatedMessage;

    }

    public static String getChatWindowTitle(String user) {
	String title = Constants.CHAT_APP_COPYRIGHT + Constants.SPACE + Constants.ACCOUNT + Constants.COLON
		+ Constants.LEFT_SQUARE_BRACKET + user.trim() + Constants.RIGHT_SQUARE_BRACKET;
	return title;

    }
}
