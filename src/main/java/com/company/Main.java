package com.company;

import ledgers.*;
import messages.Message;
import messages.SimpleMessage;
import nodes.Node;
import nodes.SimpleNode;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Ledger ledger = new SimpleLedger();

        SimpleNode n0 = new SimpleNode(ledger);
        SimpleNode n1 = new SimpleNode(ledger);
        SimpleNode n2 = new SimpleNode(ledger);

        n0.addNeighbour(new HashSet<>(Arrays.asList(n1, n2)));

        n0.flood(new SimpleMessage<>("Hello World!"));

        ledger.addEntry(new SimpleTransaction(-1, 0, 10));
        ledger.addEntry(new SimpleTransaction(-1, 1, 5));
        n0.addToLedger(10, 1);
        ledger.printLedger();
        System.out.println(ledger.getCurrentScore());
    }
}
