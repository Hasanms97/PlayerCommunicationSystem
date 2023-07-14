# Player Communication System

**PlayerCommunicationSystem** is a Java console application that facilitates communication between two players. It establishes a client-server architecture where one player acts as the initiator and sends messages to the other player, who acts as the recipient and replies with a modified message.

## Packages

The application is organized into the following packages:

### client

- **Client**: Represents a client that connects to a server and communicates with it. It is the superclass for InitiatorPlayer and RecipientPlayer classes.

+ **InitiatorPlayer**: Represents the initiator player client. It sends messages to the server and receives modified messages from the recipient player.

* **RecipientPlayer**: Represents the recipient player client. It connects to the server, sends auto-generated messages, and listens for messages from the server.

### handler

**ClientHandler**: Handles communication with a connected client. It receives messages from the client and broadcasts them to other connected clients.

### message behavior

**MessageBehavior**: An abstract class representing the behavior of a message in client-server communication. It provides common functionality for sending and receiving messages to/from the server.

**ClientGeneratedMessage**: Extends MessageBehavior and represents a message behavior where the client generates messages. It allows the client to send messages to the server and receive messages from the server.

**AutoGeneratedMessage**: Extends MessageBehavior and represents a message behavior where messages are automatically generated by the client. It allows the client to send messages to the server and receive messages from the server.

### server

**Server**: Represents a server that accepts client connections and handles communication with them.

### util

**Constants**: Stores constant values used in the messaging application. These constants provide centralized access to important values throughout the application.

### Usage

Create two instances of the InitiatorPlayer and RecipientPlayer classes.

The initiator player sends a message to the recipient player.

Upon receiving a message, the recipient player replies with a modified message containing the received message concatenated with a counter.

The program gracefully finalizes after the initiator player has sent 10 messages and received back 10 messages.

### Installation

To run the PlayerCommunicationSystem application locally, follow these steps:

Clone the repository from GitHub:
bash
Copy code
git clone https://github.com/username/PlayerCommunicationSystem.git
Import the project into your preferred Java IDE.
Build the project to resolve any dependencies.
Run the Main class to start the application.
### Contributing

If you'd like to contribute to the PlayerCommunicationSystem project, please follow these guidelines:

Fork the repository and create a new branch for your contribution.
Make your changes and test thoroughly.
Submit a pull request, describing your changes and the problem they solve.
Ensure your code follows the project's coding style and guidelines.
### License

The PlayerCommunicationSystem application is released under the MIT License. See LICENSE for more details.

### Contact

If you have any questions or suggestions regarding the PlayerCommunicationSystem project, please contact us at [email address].
