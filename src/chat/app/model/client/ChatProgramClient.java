package chat.app.model.client;

import java.net.Socket;

import chat.app.LaunchChatApp;
import chat.app.common.Logger;
import chat.app.model.io.ListenFromServer;
import chat.app.view.Login;

/**
 * @author amtul.nazneen
 */
public class ChatProgramClient {

    private LaunchChatApp runningProgram;
    private Socket clientSocket;
    private ListenFromServer respondClient;

    public ChatProgramClient(LaunchChatApp runningProgram) {
	this.runningProgram = runningProgram;
    }

    public void start() throws Exception {
	try {
	    Logger.logClientInfo("In ChatProgramClient.start(): ");
	    clientSocket = ClientHelper.createClientSocket();
	    ClientHelper.createNewRunningThread(this);
	    Logger.logClientInfo("End of ChatProgramClient.start(): " + " Thread successfully created");
	} catch (Exception e) {
	    Logger.logClientException("Exception in ChatProgramClient.start(): " + "\nDetails: " + e.getMessage(), e);
	    throw new Exception();

	}
    }

    public void relayMessage(String message) {
	try {
	    respondClient.sendResponse(message);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public LaunchChatApp getRunningProgram() {
	return runningProgram;
    }

    public void setLoginWindowAfterError() {
	getRunningProgram().setLoginWindowOnLaunch(new Login(null));
    }

    public Socket getClientSocket() {
	return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
	this.clientSocket = clientSocket;
    }

    public ListenFromServer getRespondClient() {
	return respondClient;
    }

    public void setRespondClient(ListenFromServer respondClient) {
	this.respondClient = respondClient;
    }

}
