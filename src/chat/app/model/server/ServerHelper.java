package chat.app.model.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import chat.app.common.Constants;
import chat.app.common.Logger;
import chat.app.exception.MessageConstants;
import chat.app.model.common.ModelUtil;
import chat.app.model.io.ListenFromClient;
import chat.app.model.io.ReadWriteManager;

/**
 * @author amtul.nazneen
 */
public class ServerHelper {
    public static void sendChatToClients(ListenFromClient source, String message, ChatProgramServer server) {
	Logger.logServerInfo("In ServerHelper.sendChatToClients(): ");
	for (Map.Entry<Double, ListenFromClient> entry : server.getServerClients().getClientMap().entrySet()) {
	    Double currentIdentifier = entry.getKey();
	    if (currentIdentifier != source.getClientIndentifier()) {
		try {
		    entry.getValue().sendResponse(message + Constants.NEW_LINE);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
	Logger.logServerInfo("End of ServerHelper.sendChatToClients(): ");
    }

    public static void sendUserLeftChat(ListenFromClient leavingClient, ChatProgramServer server) {
	Logger.logServerInfo("In ServerHelper.sendUserLeftChat(): ");
	for (Map.Entry<Double, ListenFromClient> entry : server.getServerClients().getClientMap().entrySet()) {
	    Double currentIdentifier = entry.getKey();
	    if (currentIdentifier != leavingClient.getClientIndentifier()) {
		try {
		    entry.getValue().sendResponse(MessageConstants.USER_LEFT_CHAT + Constants.NEW_LINE);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
	Logger.logServerInfo(
		"End of ServerHelper.sendUserLeftChat(): " + " Message successfully updated to other clients.");
    }

    public static void createNewRunningThread(ChatProgramServer server, Socket clientSocket) throws IOException {
	Logger.logServerInfo("In ServerHelper.createNewRunningThread(): ");
	ReadWriteManager io = ModelUtil.createReadWriteManager(clientSocket);
	ListenFromClient clientThread = new ListenFromClient(server, io);
	clientThread.setClientIndentifier(Math.random());
	server.getServerClients().updateClients(clientThread, true, false);

	Thread thread = new Thread(clientThread);
	thread.start();
	Logger.logServerInfo("End of ServerHelper.createNewRunningThread(): " + " Thread running successfully");
    }

}
