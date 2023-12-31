package Client;

import MessageBehaviors.AutoGeneratedMessage;
import MessageBehaviors.MessageBehavior;
import Util.Constants;

import java.io.IOException;
import java.net.Socket;

public class RecipientPlayer extends Client {

    public RecipientPlayer(Socket socket, String username) {
        super(socket, username);

        // Create a message behavior for auto-generated messages
        MessageBehavior messageBehavior = new AutoGeneratedMessage(socket, username);

        // Start listening for messages from the server
        messageBehavior.listenToMessage();

        // Send messages to the server
        messageBehavior.sendMessage();
    }

    public static void main(String[] args) throws IOException {
        // Connect to the server
        Socket socket = new Socket(Constants.HOSTNAME, Constants.PORT);

        // Create a recipient player client instance
        Client client = new RecipientPlayer(socket, Constants.RECIPIENT_PLAYER);
    }
}
