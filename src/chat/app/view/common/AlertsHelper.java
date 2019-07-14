package chat.app.view.common;

import chat.app.exception.MessageConstants;
import javafx.scene.control.Alert;

/**
 * @author amtul.nazneen
 */
public class AlertsHelper {

    public static void displayErrorAlertOnServerDown() {
	Alert errorPopup = ViewUtil.createErrorAlertPopup(MessageConstants.SERVER_UNAVAILABLE_TITLE,
		MessageConstants.SERVER_UNAVAILABLE_DETAILS);
	errorPopup.setHeight(30);
	errorPopup.setWidth(50);
	errorPopup.showAndWait();
    }

    public static void displayInfoAlertOnLogin() {
	Alert infoPopup = ViewUtil.createInformationAlertPopup(MessageConstants.CONNECTION_STATUS,
		MessageConstants.CONNECTION_DETAILS);
	infoPopup.setHeight(30);
	infoPopup.setWidth(50);
	infoPopup.show();
    }

    public static void displayErrorOnLostConnection() {
	Alert errorPopup = ViewUtil.createErrorAlertPopup(MessageConstants.CONNECTION_ERROR_TITLE,
		MessageConstants.CONNECTION_ERROR_DETAILS);
	errorPopup.setHeight(30);
	errorPopup.setWidth(70);
	errorPopup.showAndWait();
    }
}
