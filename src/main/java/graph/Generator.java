package graph;

import java.util.Random;

public class Generator {
    int nodeCount;
    int edgeCount;
    Random random;
    boolean oriented;
    boolean weighted;

    public Generator(int nodeCount, int edgeCount, boolean oriented, boolean weighted) {
        this.nodeCount = nodeCount;
        random = new Random();
        this.oriented = oriented;
        this.weighted = weighted;

        int maxEdgeCount = nodeCount * (nodeCount - 1) / 2;
        if (weighted) {
            maxEdgeCount *= 2;
        }
        if (edgeCount < nodeCount - 1) {
            this.edgeCount = nodeCount - 1;
        } else if(edgeCount > maxEdgeCount) {
            this.edgeCount = maxEdgeCount;
        } else {
            this.edgeCount = edgeCount;
        }

    }

    public Graph nextFull() {
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
                    try {
                        graph.addEdge(String.valueOf(i), String.valueOf(j), random.nextInt(100));
                    } catch (Exception e) {

                    }
                } else {
                    graph.addEdge(String.valueOf(i), String.valueOf(j));
                }
            }
        }

        return graph;
    }

    //fixme
    public Graph nextTree() {
        Graph graph = new Graph(oriented, weighted);
        for (int i = 0; i < nodeCount; ++i) {
            graph.addNode(String.valueOf(i));
        }

        int count = 0;
        while (count < nodeCount - 1) {
            int st = random.nextInt(nodeCount);
            int nd = random.nextInt(nodeCount);
            if (st != nd) {
                Node s = graph.getNode(String.valueOf(st));
                Node e = graph.getNode(String.valueOf(nd));
                if (weighted) {
                    try {
                        graph.addEdge(String.valueOf(st), String.valueOf(nd), random.nextDouble());
                    } catch (Exception ex) {

                    }
                } else {
                    graph.addEdge(String.valueOf(st), String.valueOf(nd));
                }
                if (graph.hasCycle()) {
                    graph.deleteEdge(String.valueOf(st), String.valueOf(nd));
                } else {
                    count++;
                }
            }
        }

        return graph;
    }

    //fixme
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

    public Graph nextHamiltonian() {
        Graph graph = new Graph(oriented, weighted);
        for (int i = 0; i < nodeCount; ++i) {
            graph.addNode(String.valueOf(i));
        }

        if (edgeCount < nodeCount) {
            edgeCount = nodeCount;
        }

        for (int i = 0; i < nodeCount-1; ++i) {
            if (weighted) {
                try {
                    graph.addEdge(String.valueOf(i), String.valueOf(i+1), random.nextDouble());
                } catch (Exception ex) {

                }
            } else {
                graph.addEdge(String.valueOf(i), String.valueOf(i+1));
            }
        }

        if (weighted) {
            try {
                graph.addEdge(String.valueOf(0), String.valueOf(nodeCount-1), random.nextDouble());
            } catch (Exception ex) {

            }
        } else {
            graph.addEdge(String.valueOf(0), String.valueOf(nodeCount-1));
        }

        int count = nodeCount;
        while (count < edgeCount) {
            int st = random.nextInt(nodeCount);
            int nd = random.nextInt(nodeCount);
            if (st != nd) {
                if (weighted) {
                    try {
                        if (graph.addEdge(String.valueOf(st), String.valueOf(nd), random.nextDouble())) {
                            count++;
                        }
                    } catch (Exception ex) {

                    }
                } else {
                    if (graph.addEdge(String.valueOf(st), String.valueOf(nd))) {
                        count++;
                    }
                }
            }
        }

        return graph;
    }
}
