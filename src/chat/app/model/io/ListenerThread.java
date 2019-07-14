package chat.app.model.io;

/**
 * @author amtul.nazneen
 */
public abstract class ListenerThread {
    public abstract void sendResponse(String message) throws Exception;
}
