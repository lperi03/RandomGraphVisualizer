package com.Lakshman.RandomGraphBuilder;

import java.util.*;

//graph class

public class Graph {
    //need vars for vertices, edges
    int V;
    List<Edge> edges;
    //need adjacency list
    List<List<Integer>> adjacency;

    public Graph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
        this.adjacency = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    //function to add edges to graph
    public void addEdge(int src, int end, int Weight) {
        edges.add(new Edge(src, end, Weight));
        //all for undirected graph to have pointers both ways
        adjacency.get(src).add(end);
        adjacency.get(end).add(src);
    }

    //boolean function to check if graph is connected
    public boolean isConnected() {
        boolean[] visited = new boolean[V];
        //need to do dfs to check if every vertex is visited from origin
        //can start dfs from first vertex
        dfs(0, visited);

        for (boolean v: visited) {
            if (!v) return false;
        }
        return true;
    }

    //helper function that performs dfs from given node/vertex

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int w: adjacency.get(v)) {
            if (!visited[w]) {
                dfs(w, visited);
            }
        }
    }
}
