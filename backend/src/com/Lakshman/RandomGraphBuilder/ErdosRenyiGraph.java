package com.Lakshman.RandomGraphBuilder;
import java.util.*;

public class ErdosRenyiGraph {
    Random rand = new Random();

    public Graph generateGraph(int V, double p) {
        Graph randomGraph = new Graph(V);

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (rand.nextDouble() < p) {
                    randomGraph.addEdge(i, j, rand.nextInt(100) + 1);  // assuming the weight is a random integer from 1 to 100
                }
            }
        }

        return randomGraph;
    }
}
