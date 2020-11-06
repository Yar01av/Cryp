package nodes;

import ledgers.Ledger;
import messages.Message;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleNode extends Node<String, SimpleNode> {
    private final Set<SimpleNode> neighbours = new HashSet<>();
    private Ledger ledger;
    private static final AtomicInteger counter = new AtomicInteger();
    private final int id;
    private Set<Integer> seenMessageIds = new HashSet<>();

    public SimpleNode(Ledger ledger) {
        this.ledger = ledger;
        this.id = counter.incrementAndGet();
    }

    @Override
    public void flood(Message<String> message) {
        System.out.println(message);

        if (seenMessageIds.contains(message.getId())) {
            return;
        };

        for (SimpleNode node : neighbours) {
            node.flood(message);
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void addNeighbour(SimpleNode node) {
        node.addNeighbour(this);
        neighbours.add(node);
    }

    @Override
    public void addToLedger(int quantity, String recipient) {
        this.ledger.addEntry(this.getId(), recipient, quantity);
    }
}
