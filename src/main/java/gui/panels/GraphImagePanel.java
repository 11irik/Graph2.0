package gui.panels;

import graph.Edge;
import graph.Node;
import graph.adapters.EdgeAdapter;
import graph.adapters.GraphAdapter;
import graph.adapters.NodeAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GraphImagePanel extends JPanel implements MouseListener, MouseMotionListener {
    protected GraphAdapter graph;
    ArrayList<EdgeAdapter> visitedEdges = new ArrayList<>();
    ArrayList<NodeAdapter> visitedNodes = new ArrayList<>();

    private static final Font[] KEY_FONT = new Font[30];
    public static int X_SHIFT = 10;
    public static int Y_SHIFT = 40;
    int nodeSize;
    double maxValue = 500;
    double mf;
    protected float regularWidth = 0.5f;

    GraphWorker graphWorker;
    boolean drawAlg = false;

    NodeAdapter selectedNode;
    boolean selected = false;


    public GraphImagePanel(GraphAdapter graphAdapter) {
        this.graph = graphAdapter;
        addMouseListener(this);
        addMouseMotionListener(this);

        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(200, 200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mf = Math.min(g.getClipBounds().height, g.getClipBounds().width) / 500;
        Graphics2D g2 = (Graphics2D) g;

        graph.getEdges().forEach(edge -> drawEdge(g, edge, Color.BLACK, mf));
//        if (drawAlg) {
//            visitedEdges.forEach(edge -> drawEdge(g, edge, Color.RED));
//        }

        g2.setStroke(new BasicStroke(regularWidth));
        graph.getNodes().forEach(node -> drawNode(g2, (node), Color.BLUE, mf));

        //visitedNodes.forEach(node -> drawNode(g, node, Color.CYAN));
    }


    private void drawNode(Graphics g, NodeAdapter node, Color color, double mf) {
        nodeSize = (int) mf * 500 / 8;
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(color);
        g.fillOval((int) (node.getX() * mf - nodeSize / 2), (int) (node.getY() * mf - nodeSize / 2), nodeSize, nodeSize);
        g2.setColor(Color.BLACK);
        g.drawOval((int) (node.getX() * mf - nodeSize / 2), (int) (node.getY() * mf - nodeSize / 2), nodeSize, nodeSize);

        g.setFont(KEY_FONT[(int) (8 * mf)]);
        FontMetrics fontMetrics = g.getFontMetrics();
        int width = fontMetrics.stringWidth("" + node.getKey());
        //todo magic with string
        g.drawString("" + node.getKey(), (int) (node.getX() * mf) + (nodeSize / 8 - width) / 2, (int) (node.getY() * mf) + nodeSize / 4);
    }

    public void drawEdge(Graphics g, EdgeAdapter edge, Color color, double mf) {

        Graphics2D g2 = (Graphics2D) g;
        double w = Math.min(g.getClipBounds().height, g.getClipBounds().width);
        g2.setColor(color);
        Stroke stroke = new BasicStroke(2f);
        g2.setStroke(stroke);

        NodeAdapter start = edge.getStart();
        NodeAdapter end = edge.getEnd();

        g2.drawLine(
                (int) (start.getX() *mf),
                (int) (start.getY() * mf),
                (int) (end.getX() * mf),
                (int) (end.getY() * mf)
        );

        if (edge.isOriented()) {
            int deltaY = end.getY() - start.getY();
            int deltaX = end.getX() - start.getX();
            int edgeLength = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY) - (int) (nodeSize / 2 / mf);

            double angle = Math.atan2(deltaY, deltaX);
            double angleDelta = 2 * Math.PI / 20;
            int arrowArmLength = 20;
            if (edgeLength < nodeSize * 2 / mf) {
                arrowArmLength = edgeLength / 3;
            }
            int nodeContactX = start.getX() + (int) (Math.cos(angle) * (edgeLength));
            int nodeContactY = start.getY() + (int) (Math.sin(angle) * (edgeLength));

            int leftArrowArmX = nodeContactX - (int) (Math.cos(angle - angleDelta) * arrowArmLength);
            int leftArrowArmY = nodeContactY - (int) (Math.sin(angle - angleDelta) * arrowArmLength);

            int rightArrowArmX = nodeContactX - (int) (Math.cos(angle + angleDelta) * arrowArmLength);
            int rightArrowArmY = nodeContactY - (int) (Math.sin(angle + angleDelta) * arrowArmLength);

            g2.drawLine(
                    (int) (nodeContactX * mf),
                    (int) (nodeContactY * mf),
                    (int) (leftArrowArmX * mf),
                    (int) (leftArrowArmY * mf)
            );

            g2.drawLine(
                    (int) (nodeContactX * mf),
                    (int) (nodeContactY * mf),
                    (int) (rightArrowArmX * mf),
                    (int) (rightArrowArmY * mf)
            );
        }
    }

    public void executeStart() {
        repaint();
        visitedEdges = new ArrayList<>();
        visitedNodes = new ArrayList<>();
        graphWorker = new GraphWorker(visitedNodes, visitedEdges);
        graphWorker.execute();
    }

//    public void executeStop() {
//        graphWorker.cancel(true);
//    }

    public void reset() {
        visitedNodes = new ArrayList<>();
        visitedEdges = new ArrayList<>();
        drawAlg = false;
        repaint();
    }

    public void update() throws InterruptedException {
        repaint();
        Thread.sleep(1000);
    }

    class GraphWorker extends SwingWorker<Void, Void> {
        List<NodeAdapter> visitedNodes;
        List<EdgeAdapter> visitedEdges;

        public GraphWorker(List<NodeAdapter> visitedNodes, List<EdgeAdapter> visitedEdges) {
            this.visitedEdges = visitedEdges;
            this.visitedNodes = visitedNodes;
        }

        @Override
        protected Void doInBackground() throws Exception {
            Queue<Node> nodes = graph.getGraph().getSpanningComponent(graph.getNode("6").getNode());
            NodeAdapter st = graph.getNode("6");
            drawAlg = true;

            for (Node end : nodes) {
                NodeAdapter nd = graph.getNode(end.getKey());
                EdgeAdapter edge = new EdgeAdapter(graph.getNode(st.getKey()), graph.getNode(end.getKey()), false, false);
                visitedEdges.add(edge);
                st = nd;
                update();
            }

            drawAlg = false;
            return null;
        }

    }

    private NodeAdapter selectNodeFromCoords(int x, int y) {
        NodeAdapter node = null;
        for (NodeAdapter n : graph.getNodes()) {
            if (Math.abs(n.getX() * mf - x) < nodeSize / 2 &&
                    Math.abs(n.getY() * mf - y) < nodeSize / 2) {
                node = n;
            }
        }
        return node;
    }

    //Mouse Listener

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            selectedNode = selectNodeFromCoords(e.getX(), e.getY());
            selectedNode.setX((int) (e.getX() / mf));
            selectedNode.setY((int) (e.getY() / mf));
            selected = true;
            repaint();
        } catch (Exception k) {
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selected = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected) {
            selectedNode.setX((int) (e.getX() / mf));
            selectedNode.setY((int) (e.getY() / mf));;
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
