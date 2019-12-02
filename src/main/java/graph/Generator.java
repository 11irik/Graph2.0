package graph;

import java.util.Random;

public class Generator {
    int nodeCount;
    Random random;
    boolean oriented;
    boolean weighted;

    public Generator(int nodeCount, boolean oriented, boolean weighted) {
        this.nodeCount = nodeCount;
        random = new Random();
        this.oriented = oriented;
        this.weighted = weighted;
    }

    public Graph nextFull() throws Exception {
        Graph graph = new Graph(oriented, weighted);
        for (int i = 0; i < nodeCount; ++i) {
            graph.addNode(String.valueOf(i));
        }

        for (int i = 0; i < nodeCount; ++i) {
            int j;
            if (oriented) {
                j = 0;
            } else {
                j = i;
            }

            for (; j < nodeCount; ++j) {
                if (weighted) {
                    graph.addEdge(String.valueOf(i), String.valueOf(j), random.nextInt(100));
                } else {
                    graph.addEdge(String.valueOf(i), String.valueOf(j));
                }
            }
        }

        return graph;
    }

    //todo tree generator
    private Graph getMinimalBigraph(Graph graph, int fstNodeCount, int sndNodeCount) {


        return graph;
    }

    public Graph nextBigraph(int edgesCount) throws Exception {
        Graph graph = new Graph(oriented, weighted);
        for (int i = 0; i < nodeCount; ++i) {
            graph.addNode(String.valueOf(i));
        }

        int fstNodeCount = random.nextInt(nodeCount - 1) + 1;
        int sndNodeCount = nodeCount - fstNodeCount;

        if (edgesCount == 0) {
            edgesCount = fstNodeCount * sndNodeCount;
            if (oriented) {
               // edgesCount *= 2;
            }
        }

        int fstEdgesCount = random.nextInt(edgesCount - 1) + 1;
        int sndEdgesCount = edgesCount - fstEdgesCount;

        int count = 0;
        while (count < fstEdgesCount) {
            int edgeStart = random.nextInt(fstNodeCount);
            int edgeEnd = random.nextInt(sndNodeCount) + fstNodeCount;
            System.out.println(edgeStart + " " + edgeEnd);
            if (weighted) {
                 if (graph.addEdge(String.valueOf(edgeStart), String.valueOf(edgeEnd), random.nextInt(100))) {
                     count++;
                 }
            } else {
                if (graph.addEdge(String.valueOf(edgeStart), String.valueOf(edgeEnd))) {
                    count++;
                }
            }
        }

        count = 0;
        while (count < sndEdgesCount) {
            int edgeEnd = random.nextInt(fstNodeCount);
            int edgeStart = random.nextInt(sndNodeCount) + fstNodeCount;
            if (weighted) {
                if (graph.addEdge(String.valueOf(edgeStart), String.valueOf(edgeEnd), random.nextInt(100))) {
                    count++;
                }
            } else {
                if (graph.addEdge(String.valueOf(edgeStart), String.valueOf(edgeEnd))) {
                    count++;
                }
            }
        }

        return graph;
    }
}
