package chat.app.model.io;

import java.net.SocketException;

import chat.app.common.Constants;
import chat.app.common.Logger;
import chat.app.model.client.ChatProgramClient;
import chat.app.view.common.AlertsHelper;
import javafx.application.Platform;

/**
 * @author amtul.nazneen
 */
public class ListenFromServer extends ListenerThread implements Runnable {

    private ChatProgramClient parentClientThread;
    private ReadWriteManager io;

    public ListenFromServer(ChatProgramClient chatClient, ReadWriteManager io) {
	this.parentClientThread = chatClient;
	this.io = io;
    }

    @Override
    public void run() {
	Logger.logClientInfo("In UpdaterThread.run(): ");
	try {
	    while (true) {
		Logger.logClientInfo("Listening to messages; update the chat window");
		String message = io.getInput();
		parentClientThread.getRunningProgram().getUserChatWindow()
			.addTextToChatWindow(message + Constants.NEW_LINE);
		Logger.logClientInfo("Appended the text: " + "** " + message + " **" + " to the client window");

	    }
	} catch (SocketException e) {
	    Logger.logClientError("Exception in UpdaterThread.run(): "
		    + "Connection to the server has been lost. Displaying error popup.");
	    Logger.logClientException("Exception in UpdaterThread.run(): " + "\nDetails: " + e.getMessage(), e);
	    Platform.runLater(new Runnable() {
		@Override
		public void run() {
		    AlertsHelper.displayErrorOnLostConnection();
		    parentClientThread.setLoginWindowAfterError();
		}
	    });

	} catch (Exception e) {
	    Logger.logClientException("Exception in UpdaterThread.run(): " + "\nDetails: " + e.getMessage(), e);
	    e.printStackTrace();
	}
    }

    @Override
    public void sendResponse(String message) throws Exception {
	io.sendResponse(message);

    }

    public ReadWriteManager getIo() {
	return io;
    }

    public void setIo(ReadWriteManager io) {
	this.io = io;
    }

}
