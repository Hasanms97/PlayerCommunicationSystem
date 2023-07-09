package MessageBehaviors;

import Util.Constants;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientGeneratedMessage extends MessageBehavior {

    private AtomicInteger messagesCounter;

    public ClientGeneratedMessage(Socket socket, String username) {
        super(socket, username);

        // Initialize the counter for tracking message count
        this.messagesCounter = new AtomicInteger(0);
    }

    @Override
    public void sendMessage() {
        try {
            // Send the username to the server
            bufferedWriter.write(this.username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            // Read messages from the console and send them to the server
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected() && messagesCounter.get() < Constants.MESSAGE_COUNT) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ":" + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                messagesCounter.incrementAndGet();
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
                        // Read messages from the server and print them to the console
                        messageFromChat = bufferedReader.readLine();
                        System.out.println(messageFromChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }
}
