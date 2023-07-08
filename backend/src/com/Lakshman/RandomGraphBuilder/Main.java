package com.Lakshman.RandomGraphBuilder;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]); // Number of vertices in graph
        double p = Double.parseDouble(args[1]); // Probability for edge creation in Erdos-Renyi graph

        // Create an Erdos-Renyi graph with V vertices and edge probability p
        ErdosRenyiGraph erGraph = new ErdosRenyiGraph();
        Graph g = erGraph.generateGraph(V, p);

        // Prepare JSON object
        JSONObject graphData = new JSONObject();
        JSONArray verticesArray = new JSONArray();
        JSONArray edgesArray = new JSONArray();
        JSONArray mstEdgesArray = new JSONArray();

        for (int i = 0; i < V; i++) {
            JSONObject vertex = new JSONObject();
            vertex.put("id", i);
            verticesArray.put(vertex);
        }

        for (Edge e : g.edges) {
            JSONObject edge = new JSONObject();
            edge.put("source", e.src);
            edge.put("target", e.end);
            edgesArray.put(edge);
        }

        graphData.put("vertices", verticesArray);
        graphData.put("edges", edgesArray);
        graphData.put("isConnected", g.isConnected());

        if (g.isConnected()) {
            // Create the Minimum Spanning Tree
            MST mst = new MST(V, g.edges);
            List<Edge> result = mst.kruskalMST(g.edges);

            for (Edge e : result) {
                JSONObject edge = new JSONObject();
                edge.put("source", e.src);
                edge.put("target", e.end);
                mstEdgesArray.put(edge);
            }

            graphData.put("mstEdges", mstEdgesArray);
        }

        System.out.println(graphData.toString());
    }
}

