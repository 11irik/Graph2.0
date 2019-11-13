package gui.panels;

import graph.wrappers.EdgeWrapper;
import graph.wrappers.GraphWrapper;
import graph.wrappers.NodeWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GraphImagePanel extends JPanel implements MouseListener, MouseMotionListener {
    protected GraphWrapper graph;
    int nodeSize;
    double aspect;
    int nodeCoordMax = 100000; //value of random in node

    NodeWrapper chosenNode;
    boolean isChosed = false;


    public GraphImagePanel(GraphWrapper graphWrapper) {
        this.graph = graphWrapper;

        ArrayList<EdgeWrapper> edges = graphWrapper.getEdges();
        ArrayList<NodeWrapper> nodes = graphWrapper.getNodes();

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graph.getEdges().forEach(edge -> drawEdge(g, edge));
        graph.getNodes().forEach(node -> drawNode(g, node));
    }

    private void drawNode(Graphics g, NodeWrapper node) {
        g.setColor(Color.BLUE);
        aspect = Math.min(g.getClipBounds().height, g.getClipBounds().width);
        nodeSize = (int) aspect / 8; // 1/8 of minimal side, can be any value
        g.fillOval((int) (node.getX() * aspect / nodeCoordMax - nodeSize/2), (int) (node.getY() * aspect / nodeCoordMax - nodeSize/2), nodeSize, nodeSize);
        g.setColor(Color.BLACK);
        g.drawOval((int) (node.getX() * aspect / nodeCoordMax - nodeSize/2), (int) (node.getY() * aspect / nodeCoordMax - nodeSize/2), nodeSize, nodeSize);
        g.drawString("" + node.getKey(), (int) (node.getX() * aspect / nodeCoordMax), (int) (node.getY() * aspect / nodeCoordMax));
    }

    public void drawEdge(Graphics g, EdgeWrapper edge) {
        double w = Math.min(g.getClipBounds().height, g.getClipBounds().width);

        g.setColor(Color.BLACK);

        g.drawLine((int) (edge.getStart().getX() * w / nodeCoordMax), (int) (edge.getStart().getY() * w / nodeCoordMax), (int) (edge.getEnd().getX() * w / nodeCoordMax), (int) (edge.getEnd().getY() * w / nodeCoordMax));
    }

    private NodeWrapper getClickedNodeFromCoords(int x, int y) {
        NodeWrapper node = null;
        for (NodeWrapper n : graph.getNodes()) {
            if (Math.abs(n.getX() * aspect / nodeCoordMax - x) < nodeSize/2 &&
                    Math.abs(n.getY() * aspect / nodeCoordMax - y) < nodeSize/2) {
                node = n;
            }
        }
        return node;
    }

    //Mouse Listener
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
        if (isChosed) {
            chosenNode.setX((int) (e.getX() / aspect * nodeCoordMax));
            chosenNode.setY((int) (e.getY() / aspect * nodeCoordMax));
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
