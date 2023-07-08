package com.Lakshman.RandomGraphBuilder;
import java.util.*;

//super runtime optimized
//really cool imo, took inspo from some Comp Programming libs
public class MST {
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;

    public MST(int N, List<Edge> edgeList) {
        p = new ArrayList<>(N);
        rank = new ArrayList<>(N);
        setSize = new ArrayList<>(N);
        numSets = N;
        for (int i = 0; i < N; i++) {
            p.add(i, i);
            rank.add(i, 0);
            setSize.add(i, 1);
        }
        edgeList.sort(Edge::compareTo);
    }

    public int findSet(int i) {
        if (p.get(i) == i) return i;
        else {
            int result = findSet(p.get(i));
            p.set(i, result);
            return result;
        }
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree as short as possible
            if (rank.get(x) > rank.get(y)) {
                p.set(y, x);
                setSize.set(x, setSize.get(x) + setSize.get(y));
            } else {
                p.set(x, y);
                setSize.set(y, setSize.get(y) + setSize.get(x));
                if (rank.get(x).equals(rank.get(y))) rank.set(y, rank.get(y) + 1);
            }
        }
    }

    public List<Edge> kruskalMST(List<Edge> edgeList) {
        int totalTreeWeight = 0;
        List<Edge> mstEdges = new ArrayList<>();
        for (Edge edge : edgeList) {
            if (!isSameSet(edge.src, edge.end)) {
                totalTreeWeight += edge.weight;
                mstEdges.add(edge);
                unionSet(edge.src, edge.end);
            }
        }
        return mstEdges;
    }
}
