package graph;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;


public class GraphTest {
    Graph graph;

    //@Before
    public void createGraph() {
        graph = new Graph();
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addEdge("5", "6");
        graph.addEdge("5", "2");
        graph.addEdge("6", "2");
        graph.addEdge("5", "11");
        graph.addEdge("11", "1");
        graph.addEdge("1", "10");
        System.out.println(graph + "---Before---\n");
    }

    @Before
    public void createOrGraph() {
        graph = new Graph(true, true);
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addEdge("6", "2", 1);
        graph.addEdge("5", "6", 1);
        graph.addEdge("2", "5", 0);
        graph.addEdge("11", "10", 0);
        graph.addEdge("5", "11", 1);
        graph.addEdge("10", "1", 1);
        graph.addEdge("5", "1", 1);
        graph.addEdge("2", "1", 1);


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
    public void testHasNode() {

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
        System.out.println(graph.getZeros());
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
        System.out.println(graph.doesIssueExist("5", "2"));
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
        System.out.println(graph.getFundamentalSetOfCycles());
    }

    @Test
    public void minimalRoutesLengths() {
        graph.minimalRouteLengths();
    }
}
