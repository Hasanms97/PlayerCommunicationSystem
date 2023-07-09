package Client;

import MessageBehaviors.ClientGeneratedMessage;
import Util.Constants;

import java.io.IOException;
import java.net.Socket;

public class InitiatorPlayer extends Client {

    public InitiatorPlayer(Socket socket, String username) {
        super(socket, username);

        // Create a message behavior for client-generated messages
        messageBehavior = new ClientGeneratedMessage(socket, username);

        // Start listening for messages from the server
        messageBehavior.listenToMessage();

        // Send messages to the server
        messageBehavior.sendMessage();
    }

    public static void main(String[] args) throws IOException {
        // Connect to the server
        Socket socket = new Socket(Constants.HOSTNAME, Constants.PORT);

        // Create an initiator player client instance
        Client client = new InitiatorPlayer(socket, Constants.INITIATOR_PLAYER);
    }
}
