package com.strava;

import com.strava.models.Node;

import java.util.*;

/**
 * Source: http://www.sanfoundry.com/java-program-implement-uniform-cost-search/
 * Modified by Ryan Huang
 */
public class SearchModule {

    private PriorityQueue<Node> queue;
    private Set<Integer> settled;
    private int distances[];
    private int numberOfNodes;
    private int adjacencyMatrix[][];
    private LinkedList<Integer> path;
    private int[] parent;
    private int source, destination;
    public static final int MAX_VALUE = 999;

    public SearchModule(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.settled = new HashSet<Integer>();
        this.queue = new PriorityQueue<>(numberOfNodes, new Node());
        this.distances = new int[numberOfNodes + 1];
        this.path = new LinkedList<Integer>();
        this.adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];
        this.parent = new int[numberOfNodes + 1];
    }

    public int search(int adjacencyMatrix[][], int source, int destination) {
        int evaluationNode;
        this.source = source;
        this.destination = destination;

        for (int i = 1; i <= numberOfNodes; i++) {
            distances[i] = MAX_VALUE;
        }

        for (int i = 0; i < numberOfNodes + 1; i++) {
            for (int j = 0; j < numberOfNodes + 1; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }

        for (int sourcevertex = 1; sourcevertex <= numberOfNodes; sourcevertex++) {
            for (int destinationVertex = 1; destinationVertex <= numberOfNodes; destinationVertex++) {
                this.adjacencyMatrix[sourcevertex][destinationVertex] =
                        adjacencyMatrix[sourcevertex][destinationVertex];
            }
        }

        queue.add(new Node(source, 0));
        distances[source] = 0;

        while (!queue.isEmpty()) {
            evaluationNode = getNodeWithMinDistanceFromPriorityQueue();
            System.out.println("The eval node is " + evaluationNode);

            if (evaluationNode == destination) {
                return distances[destination];
            }

            settled.add(evaluationNode);
            addFrontiersToQueue(evaluationNode);
        }

        return distances[destination];
    }

    private void addFrontiersToQueue(int evaluationNode) {
        for (int destination = 1; destination <= numberOfNodes; destination++) {
            if (!settled.contains(destination)) {

                if (adjacencyMatrix[evaluationNode][destination] != MAX_VALUE) {

                    if (distances[destination] > adjacencyMatrix[evaluationNode][destination]
                            + distances[evaluationNode]) {
                        distances[destination] = adjacencyMatrix[evaluationNode][destination]
                                + distances[evaluationNode];
                        parent[destination] = evaluationNode;
                    }

                    Node node = new Node(destination, distances[destination]);

                    if (queue.contains(node)) {
                        queue.remove(node);
                    }

                    queue.add(node);
                }
            }
        }
    }

    private int getNodeWithMinDistanceFromPriorityQueue() {
        Node node = queue.remove();
        return node.node;
    }

    public void printPath() {
        path.add(destination);
        boolean found = false;
        int vertex = destination;

        while (!found) {
            if (vertex == source) {
                found = true;
                continue;
            }
            path.add(parent[vertex]);
            vertex = parent[vertex];
        }

        System.out.println("The Path between " + source + " and " + destination + " is ");
        Iterator<Integer> iterator = path.descendingIterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
    }

}
