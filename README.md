# Player Communication System

This repository contains a Java program that demonstrates a simple player communication system. The program consists of two `Player` instances that can communicate with each other by exchanging messages.

## Installation

To run the program, follow the instructions below:

1. Ensure that Java Development Kit (JDK) is installed on your system.
2. Download or clone this repository to your local machine.
3. Open a terminal or command prompt and navigate to the project's root directory.
4. Build the project using Maven by executing the following command:
5. After a successful build, a JAR file named `player-communication-system.jar` will be created in the `target` directory.
6. Run the program using the provided shell script by executing the following command:



## Classes

The program consists of the following classes:

### Player

The `Player` class represents a player instance that can communicate with other players. It is responsible for sending and receiving messages, as well as keeping track of the number of messages sent.

#### Responsibilities:
- Send messages to other players
- Receive messages from other players
- Concatenate received messages with the message counter and reply back

### CommunicationSystem

The `CommunicationSystem` class is responsible for coordinating the communication between players. It initializes the players, initiates the message exchange, and determines the stop condition.

#### Responsibilities:
- Create and manage player instances
- Initiate the message exchange between players
- Track the number of messages sent and received
- Define the stop condition for the program

### Main

The `Main` class serves as the entry point for the program. It creates an instance of the `CommunicationSystem` class and starts the player communication.

#### Responsibilities:
- Create an instance of the `CommunicationSystem`
- Start the player communication

## Usage

Upon running the program, two `Player` instances will be created, and the communication will be initiated. The initiator player will send messages to the second player, and each player will reply with a message containing the received message concatenated with the message counter.

The program will gracefully terminate once the initiator player has sent 10 messages and received back 10 messages.

## Project Structure

The project follows the standard Maven project structure. Here's an overview of the directory structure:

## License

This project is licensed under the [MIT License](LICENSE).
