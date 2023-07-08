package com.Lakshman.RandomGraphBuilder;

//Class for creating edges in the graph
//implementation is fairly straightforward, but having this in a separate file increases modularity imo.

public class Edge implements Comparable<Edge> {
    int src, end, weight;

    public Edge(int src, int end, int weight) {
        this.src = src;
        this.end  = end;
        this.weight = weight;
    }

    //comparator function for pq
    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}
