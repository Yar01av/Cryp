package ledgers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleLedger extends Ledger {
    private final ArrayList<SimpleTransaction> memory = new ArrayList<>();

    @Override
    public void addEntry(SimpleTransaction transaction) {
        if (transaction.getAmount() <= 0) {
            throw new AssertionError("Attempted to add a <= 0 transaction to the ledger.");
        }

        memory.add(transaction);
    }

    @Override
    public void printLedger() {
        System.out.println(memory);
    }

    @Override
    public Map<Integer, Integer> getCurrentScore() {
        Map<Integer, Integer> result = new HashMap<>();

        for (SimpleTransaction transaction : memory) {
            // Safe add if the recipient is absent
            if (transaction.getSenderId() == -1 && !result.containsKey(transaction.getRecipientId())) {
                result.put(transaction.getRecipientId(), transaction.getAmount());
            }
            // Safe add if the recipient is present
            else if (transaction.getSenderId() == -1 && result.containsKey(transaction.getRecipientId())) {
                result.replace(transaction.getRecipientId(), result.get(transaction.getRecipientId()) + transaction.getAmount());
            }
            // Does the sender have any coin?
            else if (!result.containsKey(transaction.getSenderId())) {
                throw new AssertionError("The currency was sent from a node that has not previously " +
                        "received any.");
            }
            // Does the sender have enough coin?
            else if (result.containsKey(transaction.getSenderId()) &&
                     result.get(transaction.getSenderId()) < transaction.getAmount()) {
                throw new AssertionError("Node " + transaction.getSenderId() + " tried to send " +
                        transaction.getAmount() + " coin but had only " + result.get(transaction.getSenderId()) +
                        " coin.");
            }
            // No loop-backs
            else if (transaction.getSenderId() == transaction.getRecipientId()) {
                throw new AssertionError("Node " + transaction.getRecipientId() + " attempts a loop-back.");
            }
            // Exchange
            else {
                // Take the money out
                result.replace(transaction.getSenderId(),
                        result.get(transaction.getSenderId()) - transaction.getAmount());
                // Put the money on
                int new_value = result.getOrDefault(transaction.getRecipientId(), 0) +
                        transaction.getAmount();
                result.put(transaction.getRecipientId(), new_value);
            }

        }

        return result;
    }
}
