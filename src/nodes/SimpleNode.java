package nodes;

import ledgers.Ledger;
import messages.Message;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleNode extends Node<String> {
    private final Set<SimpleNode> neighbours = new HashSet<>();
    private Ledger ledger;
    private static final AtomicInteger counter = new AtomicInteger();
    private final int id;

    public SimpleNode(Set<SimpleNode> nodes, Ledger ledger, String id) {
        neighbours.addAll(nodes);
        this.ledger = ledger;
        this.id = counter.incrementAndGet();
    }

    @Override
    void flood(Message<String> message) {
        System.out.println(message);

        for (SimpleNode node : neighbours) {
            node.flood(message);
        }
    }

    @Override
    int getId() {
        return this.id;
    }

    @Override
    void addToLedger(int quantity, String recipient) {
        this.ledger.addEntry(this.getId(), recipient, quantity);
    }
}
