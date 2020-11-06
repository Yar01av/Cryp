package messages;

abstract public class Message<C> {
    abstract public C getContent();

    abstract public int getId();
}
