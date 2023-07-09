package MessageBehaviors;

import Util.Constants;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoGeneratedMessage extends MessageBehavior {

    // Queue to store received messages
    ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();

    // Counter for tracking the number of sent messages
    private AtomicInteger messageCounter;

    public AutoGeneratedMessage(Socket socket, String username) {
        super(socket, username);

        // Initialize the counter for tracking message count
        this.messageCounter = new AtomicInteger(0);
    }

    @Override
    public void sendMessage() {
        try {
            // Send the username to the server
            bufferedWriter.write(this.username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while (socket.isConnected() && messageCounter.get() < Constants.MESSAGE_COUNT) {
                // Check if there are messages in the queue to send
                if (!messageQueue.isEmpty()) {
                    // Increment the message counter and send the message to the server
                    messageCounter.incrementAndGet();
                    bufferedWriter.write(username + " received: " + sliceString(messageQueue.poll()) + ", counter: " + messageCounter.get());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }

            // Close the socket, BufferedReader, and BufferedWriter
            closeEverything(socket, bufferedReader, bufferedWriter);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void listenToMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromChat;
                while (socket.isConnected()) {
                    try {
                        // Read messages from the server and add them to the message queue
                        messageFromChat = bufferedReader.readLine();
                        messageQueue.add(messageFromChat);
                        System.out.println(messageFromChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }
}