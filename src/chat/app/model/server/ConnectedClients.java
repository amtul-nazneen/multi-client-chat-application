package chat.app.model.server;

import java.util.HashMap;

import chat.app.common.Logger;
import chat.app.model.io.ListenFromClient;

/**
 * @author amtul.nazneen
 */
public class ConnectedClients {
    HashMap<Double, ListenFromClient> clientMap;

    public ConnectedClients() {
	super();
	this.clientMap = new HashMap<Double, ListenFromClient>();
    }

    public void updateClients(ListenFromClient client, boolean add, boolean delete) {
	Logger.logServerInfo("In ConnectedClients.updateClients(): ");
	Logger.logServerInfo("Sender Client: " + client.getClientIndentifier());
	Logger.logServerInfo("Operation to perform: " + (add ? " Add a new client" : "Remove a disconnected client"));
	if (add) {
	    getClientMap().put(client.getClientIndentifier(), client);
	} else if (delete) {
	    getClientMap().remove(client.getClientIndentifier());
	}
	Logger.logServerInfo("End of ConnectedClients.updateClients(): ");
    }

    public HashMap<Double, ListenFromClient> getClientMap() {
	return clientMap;
    }

    public void setClientMap(HashMap<Double, ListenFromClient> clientMap) {
	this.clientMap = clientMap;
    }

    @Override
    public String toString() {
	return "ConnectedClients [clientMap=" + clientMap + "]";
    }

}
