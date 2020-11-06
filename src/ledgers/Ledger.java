package ledgers;

import java.util.Map;

abstract public class Ledger {
    abstract public void addEntry(int nodeFrom, String nodeTo, int value);

    abstract public void printLedger();

    abstract public Map<String, Integer> getCurrentScore();
}
