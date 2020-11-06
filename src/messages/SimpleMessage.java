package messages;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleMessage<C> extends Message<C> {
    private static final AtomicInteger counter = new AtomicInteger();
    private final int id;
    private final C content;

    public SimpleMessage() {
        this.content = getContent();
        this.id = counter.incrementAndGet();
    }

    @Override
    public C getContent() {
        return this.content;
    }

    @Override
    public int getId() {
        return id;
    }
}
