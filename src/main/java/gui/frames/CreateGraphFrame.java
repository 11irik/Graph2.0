package gui.frames;

import graph.adapters.GraphAdapter;
import gui.panels.CreatorPanel;

import javax.swing.*;

public class CreateGraphFrame extends JFrame {
    public CreateGraphFrame(JFrame frame, GraphAdapter graph) {
        setSize(300, 200);
        CreatorPanel creatorPanel = new CreatorPanel(graph);
        creatorPanel.addFatherFrame(frame);
        creatorPanel.addFatherFrame(this);
        this.add(creatorPanel);
    }

    public CreateGraphFrame(GraphAdapter graph) {
        setSize(300, 300);
        setResizable(false);
        CreatorPanel creatorPanel = new CreatorPanel(graph);
        creatorPanel.addFatherFrame(this);
        creatorPanel.setMainFrame(true);
        this.add(creatorPanel);
    }
}
