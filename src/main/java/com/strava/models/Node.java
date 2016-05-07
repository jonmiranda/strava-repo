package com.strava.models;

import java.util.Comparator;

/**
 * Created by Ryan on 5/7/16.
 */
public class Node implements Comparator<Node> {

    public int node;
    public int cost;

    public Node() {
        //Default
    }

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        if (node1.node < node2.node)
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;

            if (this.node == node.node) {
                return true;
            }
        }
        return false;
    }
}
