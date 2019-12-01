package gui.panels;

import graph.Edge;
import graph.Node;
import graph.adapters.EdgeAdapter;
import graph.adapters.GraphAdapter;
import graph.adapters.NodeAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class GraphSettingsPanel extends JPanel {

    GraphAdapter graphAdapter;

    public GraphSettingsPanel(GraphAdapter graphAdapter, GraphImagePanel graph) {
        this.graphAdapter = graphAdapter;
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graph.reset();
                graph.repaint();
            }
        });

        JButton path = new JButton("Path");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graph.executeStart();
            }
        });

        JButton stop = new JButton("Stop");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //graph.executeStop();
            }
        });

        add(refresh);
        add(path);
        add(stop);
    }
}
