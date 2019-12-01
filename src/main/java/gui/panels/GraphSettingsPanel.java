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
        JButton refresh = new JButton("Rebuild");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphAdapter.refresh();
                graph.repaint();
            }
        });

        JButton path = new JButton("Animate");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graph.executeStart();
            }
        });

        JButton upd = new JButton("Update");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graph.repaint();
            }
        });

        add(refresh);
        add(path);
        add(upd);

    }
}
