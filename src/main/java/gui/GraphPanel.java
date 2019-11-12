package gui;

import graph.Graph;
import graph.Node;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {
    protected Graph graph;

    int nodeSize = 25;

    public GraphPanel() {
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


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        float regularWidth = 0.5f;
        graph.getNodes().forEach(node -> drawNode(g, node));

    }

    private void drawNode(Graphics g, Node node) {
        g.setColor(Color.BLUE);
        g.fillOval(((int) (node.getX())) - nodeSize / 2, ((int) (node.getY())) - nodeSize / 2, nodeSize, nodeSize);
        g.setColor(Color.BLACK);
        g.drawString("" + node.getKey(), (int) (node.getX()), (int) (node.getY()));
    }


}
