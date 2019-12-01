package gui.panels;

import graph.Edge;
import graph.Node;
import graph.adapters.EdgeAdapter;
import graph.adapters.GraphAdapter;
import graph.adapters.NodeAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class GraphImagePanel extends JPanel implements MouseListener, MouseMotionListener {
    protected GraphAdapter graph;
    int nodeSize;
    double aspect;
    int nodeCoordMax = 100000; //value of random in node

    NodeAdapter chosenNode;
    boolean isChosed = false;


    public GraphImagePanel(GraphAdapter graphAdapter) {
        this.graph = graphAdapter;

        ArrayList<EdgeAdapter> edges = graphAdapter.getEdges();
        ArrayList<NodeAdapter> nodes = graphAdapter.getNodes();

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public void update() throws InterruptedException {
        repaint();
        Thread.sleep(1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graph.getEdges().forEach(edge -> drawEdge(g, edge));
        graph.getNodes().forEach(node -> drawNode(g, node));
    }

    private void drawNode(Graphics g, NodeAdapter node) {
        Graphics2D g2 = (Graphics2D) g;
        int size = (int) (5000 * aspect / nodeCoordMax);
        g.setFont(new Font("TimesRoman", Font.PLAIN, size));

        g2.setColor(Color.BLUE);
        aspect = Math.min(g.getClipBounds().height, g.getClipBounds().width);
        nodeSize = (int) aspect / 8; // 1/8 of minimal side, can be any value
        g2.fillOval((int) (node.getX() * aspect / nodeCoordMax - nodeSize / 2), (int) (node.getY() * aspect / nodeCoordMax - nodeSize / 2), nodeSize, nodeSize);
        g2.setColor(Color.BLACK);
        g2.drawOval((int) (node.getX() * aspect / nodeCoordMax - nodeSize / 2), (int) (node.getY() * aspect / nodeCoordMax - nodeSize / 2), nodeSize, nodeSize);
        g2.drawString("" + node.getKey(), (int) (node.getX() * aspect / nodeCoordMax - nodeSize / 4), (int) (node.getY() * aspect / nodeCoordMax + nodeSize / 6));
    }

    public void drawEdge(Graphics g, EdgeAdapter edge) {
        Graphics2D g2 = (Graphics2D) g;

        double w = Math.min(g.getClipBounds().height, g.getClipBounds().width);

        g2.setColor(Color.GRAY);

        Stroke stroke = new BasicStroke(2f);

        g2.setStroke(stroke);


        g2.drawLine((int) (edge.getStart().getX() * w / nodeCoordMax), (int) (edge.getStart().getY() * w / nodeCoordMax), (int) (edge.getEnd().getX() * w / nodeCoordMax), (int) (edge.getEnd().getY() * w / nodeCoordMax));
        if (edge.isOriented()) {
//            double angle = Math.atan2(edge.getEnd().getY() - edge.getStart().getY(), edge.getEnd().getX() - edge.getStart().getX());
//            AffineTransform tx1 = g2.getTransform();
//            AffineTransform tx2 = (AffineTransform) tx1.clone();
//            tx2.translate(edge.getEnd().getX(), edge.getEnd().getY());
//            tx2.rotate(angle - Math.PI / 2);
//            g2.setTransform(tx2);
            int st = (int) (edge.getStart().getX() * w / nodeCoordMax);
            int en = (int) (edge.getStart().getY() * w / nodeCoordMax);
            g2.drawLine(st + 100, en + 100, st, en);
        }
    }

    private static final Polygon ARROW_HEAD = new Polygon();

    static {
        ARROW_HEAD.addPoint(0, 0);
        ARROW_HEAD.addPoint(-5, -10);
        ARROW_HEAD.addPoint(5, -10);
    }

    private NodeAdapter getClickedNodeFromCoords(int x, int y) {
        NodeAdapter node = null;
        for (NodeAdapter n : graph.getNodes()) {
            if (Math.abs(n.getX() * aspect / nodeCoordMax - x) < nodeSize / 2 &&
                    Math.abs(n.getY() * aspect / nodeCoordMax - y) < nodeSize / 2) {
                node = n;
            }
        }
        return node;
    }

    public void executeStart() {

        repaint();
        GraphWorker graphWorker = new GraphWorker();
        graphWorker.execute();
    }

    class GraphWorker extends SwingWorker<Void, Void> {

        java.util.List<Node> visitedNodes;
        java.util.List<Edge> visitedEdges;
        java.util.List<Node> processedNodes;

        public GraphWorker() {
            this.visitedNodes = visitedNodes;
            this.visitedEdges = visitedEdges;
            this.processedNodes = processedNodes;
        }

        @Override
        protected Void doInBackground() throws Exception {

            Queue<Node> nodes = graph.getGraph().getSpanningComponent(graph.getNode("6").getNode());
            NodeAdapter st = graph.getNode("6");
            graph.removeEdges();

            for (Node end : nodes) {
                NodeAdapter nd = graph.getNode(end.getKey());
                EdgeAdapter edge = new EdgeAdapter(graph.getNode(st.getKey()), graph.getNode(end.getKey()), false, false);
                graph.addEdge(edge);
                st = nd;
                update();
            }

            return null;
        }

    }

    //Mouse Listener
    @Override
    public void mousePressed(MouseEvent e) {
        try {
            chosenNode = getClickedNodeFromCoords(e.getX(), e.getY());
            chosenNode.setX((int) (e.getX() / aspect * nodeCoordMax));
            chosenNode.setY((int) (e.getY() / aspect * nodeCoordMax));
            isChosed = true;
            repaint();
        } catch (Exception k) {
            //System.out.println();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isChosed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isChosed) {
            chosenNode.setX((int) (e.getX() / aspect * nodeCoordMax));
            chosenNode.setY((int) (e.getY() / aspect * nodeCoordMax));
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        if (isChosed) {
//            chosenNode.setX((int) (e.getX() / aspect * nodeCoordMax));
//            chosenNode.setY((int) (e.getY() / aspect * nodeCoordMax));
//            repaint();
//        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
//        if (!isChosed) {
//            chosenNode = getClickedNodeFromCoords(e.getX(), e.getY());
//            if (chosenNode != null) {
//                isChosed = true;
//            }
//            else {
//                isChosed = false;
//            }
//        }
//        else {
//            isChosed = false;
//        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
