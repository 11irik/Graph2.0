package graph;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.*;


public class GraphTest {
    Graph graph;

    @Before
    public void createGraph() throws Exception {
        graph = new Graph(true, true);
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addNode("5");
        graph.addNode("6");


        graph.addEdge("1", "2", -2);
        graph.addEdge("2", "6", 1);
        graph.addEdge("6", "3", 1);
        graph.addEdge("4", "3", 4);
        graph.addEdge("4", "2", 3);
        graph.addEdge("5", "4", 3);
        graph.addEdge("5", "1", 1);


        System.out.println(graph + "---Before---\n");
    }

    @After
    public void showGraph() {
        System.out.println("\n---After---\n" + graph);
    }

    @Test
    public void testAddNode() {
        String key = "2";
        assertFalse(graph.addNode(key));
        String newKey = "100";
        assertTrue(graph.addNode(newKey));
    }

    @Test
    public void serialize() {
        File file = new File("./TEST.dat");
        Graph.serialize(graph, file);
        graph = Graph.deserialize(file);
        System.out.println(graph.toString());
    }

    @Test
    public void testDeleteNode() {
        String key = "5";
        String wrongKey = "100";
        assertTrue(graph.deleteNode(key));
        assertFalse(graph.deleteNode(wrongKey));
    }

    @Test
    public void testAddEdge() {
        assertFalse(graph.addEdge("6", "2"));
        assertTrue(graph.addEdge("6", "1"));
        try {
            graph.addEdge("6", "4");
            Assert.fail();
        }
        catch (NullPointerException e) {
        }
    }

    @Test
    public void deleteEdge() {
        assertTrue(graph.deleteEdge("6", "2"));
        assertFalse(graph.deleteEdge("6", "1"));
    }

    //Task Ia(6)
    @Test
    public void getZeros() {
        System.out.println(graph.getIsolated());
    }

    @Test
    public void removeIsolated() {
        graph.addNode("100");
        graph = graph.removeIsolated();
        System.out.println(graph);
    }

    @Test
    public void doesCallingExist() {
        System.out.println(graph.doesCallingExist("1", "5"));
        System.out.println(graph.doesCallingExist("5", "11"));
    }

    //Task Ia(17)
    @Test
    public void doesIssueExist() {
        System.out.println(graph.doesIssueExist("1", "5"));
        System.out.println(graph.doesIssueExist("5", "3"));
    }

    //Task Ib(8)
    @Test
    public void deleteOddEdges() {
        System.out.println(graph.deleteOddEdges());
    }

    @Test
    public void convertIntoEdges() {
        //System.out.println(graph.convertIntoEdges());
    }

    @Test
    public void getFundamentalSetOfCycles() {
        graph.getFundamentalSetOfCycles("1");
    }

    @Test
    public void minimalDistances() {
        HashMap<Node, HashMap<Node, Double>> distances = graph.minimalDistances();
        for (Node node : distances.keySet()) {
            System.out.println(node + " : " + distances.get(node));
        }
    }

    @Test
    public void boruvka() throws Exception {
        if (graph.getWeighted()) {
            System.out.println(graph.boruvkasAlgorithm());
        }
    }

    @Test
    public void getRadius() {
        System.out.println(graph.getRadius());
    }

    @Test
    public void ford() throws Exception {
       graph.yens("5", "3", 2);
    }

    @Test
    public void floyd() {
        graph.floydAlgorithm();
    }

    @Test
    public void maxFlow() {
        System.out.println(graph.maxFlow("1", "5"));
    }
}
