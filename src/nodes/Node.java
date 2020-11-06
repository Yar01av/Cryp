package nodes;

import messages.Message;

abstract public class Node<MessageType> {
    // Sends the message to all of its neighbours except for the one it received it from
    abstract void flood(Message<MessageType> message);

    abstract int getId();

    abstract void addToLedger(int quantity, String recipient);
}
