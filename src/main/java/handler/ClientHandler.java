package handler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    // ArrayList to store all the client handlers
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    // Socket, BufferedReader, and BufferedWriter for communication with the client
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    // Client name associated with this handler
    private String clientName;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;

            // Initialize the reader and writer for socket communication
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read the client name from the input stream
            this.clientName = this.bufferedReader.readLine();

            // Add this handler to the list of client handlers
            clientHandlers.add(this);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // Broadcasts a message to all connected clients except the sender
    private void broadcastMessage(String msgToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientName.equals(clientName)) {
                    // Write the message to the client's output stream
                    clientHandler.bufferedWriter.write(msgToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    // Removes the client handler from the list and broadcasts their departure
    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientName + " has left the chat!");
    }

    // Closes the socket, BufferedReader, and BufferedWriter
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
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

    @Override
    public void run() {
        String msgFromClient;
        while (socket.isConnected()) {
            try {
                // Read a message from the client
                msgFromClient = bufferedReader.readLine();

                // If the message is null, the client has disconnected
                if (msgFromClient == null) {
                    throw new NullPointerException();
                }

                // Broadcast the message to all connected clients
                broadcastMessage(msgFromClient);
            } catch (IOException | NullPointerException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
}