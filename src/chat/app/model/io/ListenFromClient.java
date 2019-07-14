package chat.app.model.io;

import java.net.SocketException;

import chat.app.common.Logger;
import chat.app.model.server.ChatProgramServer;
import chat.app.model.server.ServerHelper;

/**
 * @author amtul.nazneen
 */
public class ListenFromClient extends ListenerThread implements Runnable {

    private ChatProgramServer parentServerThread;
    private Double clientIndentifier;
    private ReadWriteManager io;

    public ListenFromClient(ChatProgramServer chatServer, ReadWriteManager io) {
	this.parentServerThread = chatServer;
	this.io = io;
    }

    @Override
    public void run() {
	Logger.logClientInfo("In RelayerThread.run(): ");
	try {
	    while (true) {
		String input = io.getInput();
		ServerHelper.sendChatToClients(this, input, parentServerThread);
		Logger.logClientInfo("End of RelayerThread.run(): " + " Thread put to run successfully.");
	    }

	} catch (SocketException e) {
	    Logger.logClientException(
		    "Excpetion in RelayerThread.run(): " + " One of the clients has been disconnected from the server.",
		    e);
	    Logger.logClientException("Exception in RelayerThread.run(): " + "\nDetails: " + e.getMessage(), e);
	    ServerHelper.sendUserLeftChat(this, parentServerThread);
	    parentServerThread.getServerClients().updateClients(this, false, true);

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void sendResponse(String message) throws Exception {
	io.sendResponse(message);

    }

    public Double getClientIndentifier() {
	return clientIndentifier;
    }

    public void setClientIndentifier(Double clientIndentifier) {
	this.clientIndentifier = clientIndentifier;
    }

    public ReadWriteManager getIo() {
	return io;
    }

    public void setIo(ReadWriteManager io) {
	this.io = io;
    }

}
