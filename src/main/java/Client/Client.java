package Client;

import MessageBehaviors.MessageBehavior;

import java.io.*;
import java.net.Socket;

public class Client {

    // Socket, BufferedReader, and BufferedWriter for communication with the server
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    // Username of the client
    private String username;

    // Message behavior for processing received messages
    MessageBehavior messageBehavior;

    public Client(Socket socket, String username) {
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

    // Closes the socket, BufferedReader, and BufferedWriter
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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

}
