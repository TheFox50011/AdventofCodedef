package software.aoc.day8.auxiliar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircuitManager {
    private final int[] parent;
    private final int[] size;
    private int componentCount; // Necesario para Day 8B

    public CircuitManager(int n) {
        parent = new int[n];
        size = new int[n];
        componentCount = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int findRoot(int i) {
        if (parent[i] == i) {
            return i;
        }
        parent[i] = findRoot(parent[i]); // Path compression
        return parent[i];
    }


    public boolean connect(int i, int j) {
        int rootA = findRoot(i);
        int rootB = findRoot(j);


        if (rootA == rootB) {
            return false;
        }


        if (size[rootA] > size[rootB]) {
            int swap = rootA;
            rootA = rootB;
            rootB = swap;
        }

        parent[rootA] = rootB;
        size[rootB] += size[rootA];
        componentCount--;

        return true;
    }


    public List<Integer> getAllCircuitSizes() {
        Map<Integer, Integer> sizesByRoot = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = findRoot(i);
            sizesByRoot.put(root, size[root]);
        }
        return new ArrayList<>(sizesByRoot.values());
    }


    public int getComponentCount() {
        return componentCount;
    }
}