package com.company;

import ledgers.Ledger;
import ledgers.SimpleLedger;
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
    }
}
