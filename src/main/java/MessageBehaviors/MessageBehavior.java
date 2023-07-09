package MessageBehaviors;

import java.io.*;
import java.net.Socket;

public abstract class MessageBehavior {

    // Socket, BufferedReader, and BufferedWriter for communication with the server
    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;

    // Username of the client
    protected String username;

    public MessageBehavior(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
        try {
            // Initialize the reader and writer for socket communication
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    protected MessageBehavior() {
    }

    // Closes the socket, BufferedReader, and BufferedWriter
    protected void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Slices the string to remove the prefix before the actual message content
    protected String sliceString(String str) {
        int colonIndex = str.indexOf(":");
        return str.substring(colonIndex + 1);
    }

    // Abstract method for sending messages to the server
    public abstract void sendMessage();

    // Abstract method for listening to messages from the server
    public abstract void listenToMessage();
}
