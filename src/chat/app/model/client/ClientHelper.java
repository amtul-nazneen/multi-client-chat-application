package chat.app.model.client;

import java.io.IOException;
import java.net.Socket;

import chat.app.common.Logger;
import chat.app.model.common.ModelUtil;
import chat.app.model.common.ServerConfiguration;
import chat.app.model.io.ListenFromServer;
import chat.app.model.io.ReadWriteManager;

/**
 * @author amtul.nazneen
 */
public class ClientHelper {

    public static void createNewRunningThread(ChatProgramClient client) throws IOException {
	Logger.logClientInfo("In ClientHelper.createNewRunningThread(): ");
	ReadWriteManager io = ModelUtil.createReadWriteManager(client.getClientSocket());
	client.setRespondClient(new ListenFromServer(client, io));
	Thread thread = new Thread(client.getRespondClient());
	thread.start();
	Logger.logClientInfo("End of ClientHelper.createNewRunningThread(): " + " Thread has been started ");
    }

    public static Socket createClientSocket() {
	Logger.logClientInfo("In ClientHelper.createClientSocket(): ");
	Socket clientSocket = null;
	try {

	    String host = ServerConfiguration.SERVER_HOST;
	    int port = ServerConfiguration.SERVER_PORT;
	    clientSocket = new Socket(host, port);
	    Logger.logClientInfo("Successfully Connected to Server Host: " + host + " Server Port: " + port);
	} catch (Exception e) {
	    Logger.logClientException(
		    "Exception in ClientHelper.createClientSocket(): " + "\nDetails: " + e.getMessage(), e);
	    e.printStackTrace();
	}
	Logger.logClientInfo("End of ClientHelper.createClientSocket(): " + " Client socket successfully created.");
	return clientSocket;
    }

}
