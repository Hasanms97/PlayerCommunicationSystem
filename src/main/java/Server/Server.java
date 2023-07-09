package Server;

import Util.Constants;
import handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // Starts the server and accepts client connections
    public void startServer() {
        try {
            System.out.println("Server started!");
            while (!serverSocket.isClosed()) {
                // Accept a new client connection
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");

                // Create a new client handler for the connected client
                ClientHandler clientHandler = new ClientHandler(socket);

                // Start a new thread to handle the client communication
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    // Closes the server socket
    public void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(Constants.PORT);

        // Create a new server instance and start the server
        Server server = new Server(serverSocket);
        server.startServer();
    }
}