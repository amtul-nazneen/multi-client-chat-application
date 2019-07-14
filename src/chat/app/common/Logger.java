package chat.app.common;

import chat.app.exception.MessageConstants;
import chat.app.view.common.ViewUtil;

/**
 * @author amtul.nazneen
 */
public class Logger {
    public static boolean uiEnableLogs = true;
    public static boolean clientEnableLogs = true;
    public static boolean serverEnableLogs = true;

    public static void logViewInfo(String message) {
	if (uiEnableLogs) {
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_INFO + message);
	}

    }

    public static void logViewError(String message, Exception e) {
	if (uiEnableLogs) {
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_ERROR + e.getMessage());
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_ERROR + message);
	}
    }

    public static void logClientInfo(String message) {
	if (clientEnableLogs) {
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_INFO + message);
	}

    }

    public static void logClientException(String message, Exception e) {
	if (clientEnableLogs) {
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_ERROR + e.getMessage());
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_ERROR + message);
	}
    }

    public static void logClientError(String message) {
	if (clientEnableLogs) {
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_ERROR + message);
	}
    }

    public static void logServerInfo(String message) {
	if (serverEnableLogs) {
	    System.out.println(getTimeStamp() + Constants.SPACE + MessageConstants.LOG_INFO + message);
	}

    }

    public static void logServerError(String message, Exception e) {
	if (serverEnableLogs) {
	    System.out.println(MessageConstants.LOG_ERROR + e.getMessage());
	    System.out.println(MessageConstants.LOG_ERROR + message);
	}
    }

    private static String getTimeStamp() {
	String timeStamp = ViewUtil.getTimeStamp();
	return Constants.LEFT_SQUARE_BRACKET + timeStamp + Constants.RIGHT_SQUARE_BRACKET;
    }
}
