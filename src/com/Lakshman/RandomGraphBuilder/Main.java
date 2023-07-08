package com.Lakshman.RandomGraphBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int V = 10; // Number of vertices in graph
        double p = 0.6; // Probability for edge creation in Erdos-Renyi graph

        // Create an Erdos-Renyi graph with V vertices and edge probability p
        ErdosRenyiGraph randomGraph = new ErdosRenyiGraph();
        Graph g = randomGraph.generateGraph(V, p);

        // Check if the graph is connected
        if (g.isConnected()) {
            // Create the Minimum Spanning Tree
            MST mst = new MST(V, g.edges);
            List<Edge> result = mst.kruskalMST(g.edges);

            // Print the edges of the Minimum Spanning Tree
            System.out.println("Minimum Spanning Tree edges and their weights:");
            for (Edge e : result) {
                System.out.println("Edge: " + e.src + " - " + e.end + ", weight: " + e.weight);
            }
        } else {
            System.out.println("Graph is disconnected. Minimum Spanning Tree is not possible.");
        }
    }
}
