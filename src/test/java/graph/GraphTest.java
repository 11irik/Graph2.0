package graph;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;


public class GraphTest {
    Graph graph;

    @Before
    public void createGraph() {
        graph = new Graph();
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addEdge(graph.getNode("5"), graph.getNode("6"));
        graph.addEdge(graph.getNode("5"), graph.getNode("2"));
        graph.addEdge(graph.getNode("6"), graph.getNode("2"));
        graph.addEdge(graph.getNode("5"), graph.getNode("11"));
        System.out.println(graph);
    }

    @After
    public void showGraph() {
        System.out.println(graph);
    }

    @Test
    public void isWeighted() {
        assertFalse(graph.isWeighted());
    }

    @Test
    public void testAddNode() {
        String key = "2";
        assertFalse(graph.addNode(key));
    }

    @Test
    public void testHasNode() {

    }

    @Test
    public void testDeleteNode() {
        String key = "5";
        graph.deleteNode(graph.getNode("5"));
    }

    @Test
    public void testAddEdge() {
        assertFalse(graph.addEdge(graph.getNode("6"), graph.getNode("2")));
        //assertFalse(graph.addEdge(graph.getNode("6"), graph.getNode("4")));
        assertTrue(graph.addEdge(graph.getNode("6"), graph.getNode("1")));
    }

    @Test
    public void deleteEdge() {
        assertTrue(graph.deleteEdge(graph.getNode("6"), graph.getNode("2")));
        assertFalse(graph.deleteEdge(graph.getNode("6"), graph.getNode("1")));
    }

    @Test
    public void getZeros() {
        System.out.println(graph.getZeros());
    }

    @Test
    public void doesCallingExist() {
        System.out.println(graph.doesCallingExist(graph.getNode("1"), graph.getNode("5")));
        System.out.println(graph.doesCallingExist(graph.getNode("5"), graph.getNode("11")));
    }

    @Test
    public void doesIssueExist() {
        System.out.println(graph.doesIssueExist(graph.getNode("1"), graph.getNode("5")));
        System.out.println(graph.doesIssueExist(graph.getNode("5"), graph.getNode("2")));
    }

    @Test
    public void deleteOddEdges() {
        graph.deleteOddEdges();
    }

    @Test
    public void convertIntoEdges() {
    }

    @Test
    public void boruvka() {
    }
}
