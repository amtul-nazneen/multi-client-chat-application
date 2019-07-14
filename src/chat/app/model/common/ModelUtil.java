package chat.app.model.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import chat.app.common.Constants;
import chat.app.model.io.ReadWriteManager;

/**
 * @author amtul.nazneen
 */
public class ModelUtil {

    private static BufferedReader createBufferedReader(Socket clientSocket) {
	BufferedReader inStream = null;
	try {
	    inStream = new BufferedReader(
		    new InputStreamReader(clientSocket.getInputStream(), Constants.UTF_8_ENCODING));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return inStream;
    }

    private static BufferedWriter createBufferedWriter(Socket clientSocket) {
	BufferedWriter outStream = null;
	try {
	    outStream = new BufferedWriter(
		    new OutputStreamWriter(clientSocket.getOutputStream(), Constants.UTF_8_ENCODING));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return outStream;
    }

    public static ReadWriteManager createReadWriteManager(Socket clientSocket) throws IOException {
	ReadWriteManager io = null;
	BufferedWriter outStream = createBufferedWriter(clientSocket);
	outStream.flush();
	BufferedReader inStream = createBufferedReader(clientSocket);
	io = new ReadWriteManager(outStream, inStream);
	return io;
    }

}
