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
        if (seenMessageIds.contains(message.getId())) {
            return;
        } else {
            System.out.println(message.getContent());
            seenMessageIds.add(message.getId());
        }

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
        if (neighbours.contains(node)) {
            return;
        }

        neighbours.add(node);
        node.addNeighbour(this);
    }

    @Override
    public void addToLedger(int quantity, String recipient) {
        this.ledger.addEntry(this.getId(), recipient, quantity);
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof SimpleNode) {
            return ((SimpleNode) a).id == this.id;
        } else {
            return super.equals(a);
        }
    }
}
