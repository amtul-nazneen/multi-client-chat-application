package chat.app.model.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import chat.app.common.Constants;
import chat.app.common.Logger;
import chat.app.model.common.ServerConfiguration;

/**
 * @author amtul.nazneen
 */
public class ChatProgramServer {
    private ConnectedClients clients;

    public ChatProgramServer(ConnectedClients clients) {
	super();
	this.clients = clients;
    }

    public static void main(String[] args) {
	ChatProgramServer chatServer = new ChatProgramServer(new ConnectedClients());
	chatServer.start();
    }

    public void start() {
	Logger.logServerInfo("In ChatProgramServer.start(): ");
	ServerSocket serverSocket;
	try {
	    serverSocket = new ServerSocket(ServerConfiguration.SERVER_PORT);
	    Logger.logServerInfo("Server sucessfully started at: " + ServerConfiguration.SERVER_HOST + ":"
		    + ServerConfiguration.SERVER_PORT);
	    while (Constants.TRUE) {
		Socket clientSocket = serverSocket.accept();
		Logger.logServerInfo("A client has connnected.");
		ServerHelper.createNewRunningThread(this, clientSocket);
		Logger.logServerInfo("End of ChatProgramServer.start(): " + " Thread successfully created");
	    }

	} catch (IOException e) {
	    Logger.logClientException("Exception in ChatProgramServer.start(): " + "\nDetails: " + e.getMessage(), e);
	    e.printStackTrace();

	}
    }

    public ConnectedClients getServerClients() {
	return clients;
    }

}
