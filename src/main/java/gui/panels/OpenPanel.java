package gui.panels;

import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.frames.MainFrame;

import javax.swing.*;
import java.io.File;

public class OpenPanel extends JPanel {
    public OpenPanel(JFrame frame, GraphAdapter graph) {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showDialog(null, "Open");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            graph.setGraph(Graph.deserialize(f));
            frame.dispose();
            MainFrame mainFrame = new MainFrame(graph);
            mainFrame.repaint();
        }
    }

    public OpenPanel(GraphAdapter graph) {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showDialog(null, "Open");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            graph.setGraph(Graph.deserialize(f));
        }
    }
}
