package chat.app;

import chat.app.common.Logger;
import chat.app.model.client.ChatProgramClient;
import chat.app.view.ChatWindow;
import chat.app.view.Login;
import chat.app.view.common.ViewUtil;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author amtul.nazneen
 */
public class LaunchChatApp extends Application {

    private Stage baseStage;
    private BorderPane rootNode;
    private Login userLoginWindow;
    private ChatWindow userchatWindow;
    private ChatProgramClient chatClientInstance;
    private String username;

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage initialStage) {
	Logger.logViewInfo("In start(): Beginning of Initialising the Stage");
	ViewUtil.initialiseStage(this, initialStage);
	Logger.logViewInfo("End of start(): Completed Initialising the Stage");
    }

    public ChatProgramClient getChatClientInstance() {
	return chatClientInstance;
    }

    public ChatWindow getUserChatWindow() {
	return userchatWindow;
    }

    public Login getUserLoginWindow() {
	return userLoginWindow;
    }

    public void setLoginWindowOnLaunch(Login loginWindow) {
	rootNode.setCenter(loginWindow.getOrCreateLoginLayout());
    }

    public void setChatWindowPostLogin(ChatWindow chatWindow) {
	rootNode.setCenter(chatWindow.getOrCreateChatLayout());
    }

    public String getUserName() {
	return username;
    }

    public void setUserName(String userName) {
	this.username = userName;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public Stage getStage() {
	return baseStage;
    }

    public Stage getBaseStage() {
	return baseStage;
    }

    public void setBaseStage(Stage baseStage) {
	this.baseStage = baseStage;
    }

    public BorderPane getRootNode() {
	return rootNode;
    }

    public void setRootNode(BorderPane rootNode) {
	this.rootNode = rootNode;
    }

    public ChatWindow getUserchatWindow() {
	return userchatWindow;
    }

    public void setUserchatWindow(ChatWindow userchatWindow) {
	this.userchatWindow = userchatWindow;
    }

    public void setChatClientInstance(ChatProgramClient chatClientInstance) {
	this.chatClientInstance = chatClientInstance;
    }

    public void setUserLoginWindow(Login userLoginWindow) {
	this.userLoginWindow = userLoginWindow;
    }

}