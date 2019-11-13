package gui;

import graph.Edge;
import graph.Graph;
import graph.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GraphPanel extends JPanel implements MouseListener, MouseMotionListener {
    protected Graph graph;
    int nodeSize;
    double aspect;
    int random = 100000; //value of random in node
    ArrayList<EdgeWrapper> edges;
    ArrayList<NodeWrapper> nodes;


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

        edges = new ArrayList<>();
        nodes = new ArrayList<>();

        Random random = new Random();
        graph.getNodes().forEach(node -> nodes.add(new NodeWrapper(node, random.nextInt(100000), random.nextInt(100000))));
        graph.convertIntoEdges().forEach(edge -> edges.add(new EdgeWrapper(edge, nodes)));

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void drawNode(Graphics g, NodeWrapper node) {
        g.setColor(Color.BLUE);
        aspect = Math.min(g.getClipBounds().height, g.getClipBounds().width);
        nodeSize = (int) aspect / 8; // 1/8 of minimal side, can be any value
        g.fillOval((int) (node.getX() * aspect / random - nodeSize/2), (int) (node.getY() * aspect / random  - nodeSize/2), nodeSize, nodeSize);
        g.setColor(Color.BLACK);
        g.drawOval((int) (node.getX() * aspect / random - nodeSize/2), (int) (node.getY() * aspect / random  - nodeSize/2), nodeSize, nodeSize);
        g.drawString("" + node.getKey(), (int) (node.getX() * aspect / random), (int) (node.getY() * aspect / random));
    }

    public void drawEdge(Graphics g, EdgeWrapper edge) {
        double w = Math.min(g.getClipBounds().height, g.getClipBounds().width);

        g.setColor(Color.BLACK);

        g.drawLine((int) (edge.getStart().getX() * w / 100000), (int) (edge.getStart().getY() * w / 100000), (int) (edge.getEnd().getX() * w / 100000), (int) (edge.getEnd().getY() * w / 100000));
    }

    boolean isChosed = false;
    NodeWrapper chosenNode;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        edges.forEach(edge -> drawEdge(g, edge));
        nodes.forEach(node -> drawNode(g, node));
    }

    private NodeWrapper getClickedNodeFromCoords(int x, int y) {
        NodeWrapper node = null;
        for (NodeWrapper n : nodes) {
            if (Math.abs(n.getX() * aspect / random - x) < nodeSize/2 &&
                    Math.abs(n.getY() * aspect / random - y) < nodeSize/2) {
                node = n;
            }
        }
        return node;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (isChosed) {
            chosenNode.setX((int) (e.getX() / aspect * random));
            chosenNode.setY((int) (e.getY() / aspect * random));
            repaint();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isChosed) {
            chosenNode = getClickedNodeFromCoords(e.getX(), e.getY());
            if (chosenNode != null) {
                isChosed = true;
            }
            else {
                isChosed = false;
            }
        }
        else {
            isChosed = false;
        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
