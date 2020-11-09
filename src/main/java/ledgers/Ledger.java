package ledgers;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Map;

abstract public class Ledger {
    abstract public void addEntry(SimpleTransaction transaction);

    abstract public void printLedger();

    abstract public Map<Integer, Integer> getCurrentScore() throws AssertionError;
}
