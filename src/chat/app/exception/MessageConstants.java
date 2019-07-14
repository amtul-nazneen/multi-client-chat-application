package chat.app.exception;

import chat.app.common.Constants;
import chat.app.model.common.ServerConfiguration;

/**
 * @author amtul.nazneen
 */
public class MessageConstants {

    public static final String CONNECTION_ERROR_TITLE = "Connection Error!";
    public static final String CONNECTION_ERROR_DETAILS = "Server is unavailable to process your request. \nPlease try again after sometime.";

    public static final String SERVER_UNAVAILABLE_TITLE = "Connection Unavailable!";
    public static final String SERVER_UNAVAILABLE_DETAILS = "Server is unavailable to process your request. \nPlease try again after some time.";

    public static final String CONNECTION_STATUS = "You are now logged in!";
    public static final String CONNECTION_DETAILS = "Connected " + ServerConfiguration.SERVER_HOST + Constants.COLON
	    + ServerConfiguration.SERVER_PORT + " ..";

    public static final String USER_LEFT_CHAT = "User has left the chat.";

    public static final String LOG_INFO = "INFO: ";
    public static final String LOG_ERROR = "ERROR: ";

}
