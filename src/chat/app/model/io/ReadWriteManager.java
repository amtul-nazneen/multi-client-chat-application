package chat.app.model.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author amtul.nazneen
 */
public class ReadWriteManager {
    private BufferedWriter bufWriter;
    private BufferedReader bufReader;

    public ReadWriteManager(BufferedWriter bufWriter, BufferedReader bufReader) {
	super();
	this.bufWriter = bufWriter;
	this.bufReader = bufReader;
    }

    public BufferedWriter getBufWriter() {
	return bufWriter;
    }

    public void setBufWriter(BufferedWriter bufWriter) {
	this.bufWriter = bufWriter;
    }

    public BufferedReader getBufReader() {
	return bufReader;
    }

    public void setBufReader(BufferedReader bufReader) {
	this.bufReader = bufReader;
    }

    public void sendResponse(String message) throws IOException {
	this.bufWriter.write(message);
	this.bufWriter.flush();
    }

    public String getInput() throws IOException {
	String input = null;
	input = this.bufReader.readLine();
	return input;
    }
}
