package nodes;

import messages.Message;

import java.util.Set;

abstract public class Node<MessageType, NeighbourType extends Node<MessageType, ? extends Node>> {
    // Sends the message to all of its neighbours except for the one it received it from
    public abstract void flood(Message<MessageType> message);

    public abstract int getId();

    public abstract void addNeighbour(NeighbourType node);

    public void addNeighbour(Set<NeighbourType> nodes) {
        for (NeighbourType node : nodes) {
            addNeighbour(node);
        }
    }

    public abstract void addToLedger(int quantity, String recipient);
}

