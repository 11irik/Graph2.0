package gui.frames;

import graph.adapters.GraphAdapter;
import gui.panels.NewGraphPanel;

import javax.swing.*;

public class NewGraphFrame extends JFrame {
    GraphAdapter graphAdapter;

    public NewGraphFrame(GraphAdapter graphAdapter) {
        this.graphAdapter = graphAdapter;

        NewGraphPanel newGraphPanel = new NewGraphPanel(graphAdapter);
        this.add(newGraphPanel);
    }
}
