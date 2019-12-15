package gui.panels;

import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatorPanel extends JPanel {
    RadioPanel radioPanel;
    GraphAdapter graph;
    ArrayList<JFrame> fatherFrames;
    Boolean mainFrame;
    UnitePanel unitePanel;

    public CreatorPanel(GraphAdapter graph) {
        fatherFrames = new ArrayList<>();
        this.graph = graph;
        mainFrame = false;
        this.setLayout(new BorderLayout());

        unitePanel = new UnitePanel();
        radioPanel = new RadioPanel(unitePanel);
//        GraphImagePanel graphImagePanel = new GraphImagePanel(graph);
//        this.add(graphImagePanel, BorderLayout.CENTER);

        this.add(radioPanel, BorderLayout.WEST);

        this.add(unitePanel, BorderLayout.EAST);

        JButton b = new JButton("Create");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createGraph();
                if (mainFrame == false) {
                    new MainFrame(graph);
                }
            }
        });

        this.add(b, BorderLayout.SOUTH);
    }

    public void addFatherFrame(JFrame frame) {
        fatherFrames.add(frame);
    }

    public void setMainFrame(Boolean hasMain) {
        this.mainFrame = hasMain;
    }

    private void disposeFatherFrames() {
        for (JFrame frame : fatherFrames) {
            frame.dispose();
        }
    }

    public void createGraph() {
        if (radioPanel.getConstructSelected()) {
            graph.setGraph(unitePanel.getConstructorPanel().createGraph());
            disposeFatherFrames();
        } else {
            graph.setGraph(unitePanel.getGeneratorPanel().createGraph());
            disposeFatherFrames();
        }
    }
}
