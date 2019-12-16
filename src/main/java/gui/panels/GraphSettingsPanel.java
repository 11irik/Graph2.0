package gui.panels;

import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphSettingsPanel extends JPanel {

    GraphAdapter graphAdapter;

    public GraphSettingsPanel(GraphAdapter graph, GraphImagePanel graphImagePanel) {
        this.graphAdapter = graph;

        this.setLayout(new GridLayout(0, 1));

        JButton rebuild = new JButton("Rebuild");
        rebuild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graph.rebuild();
                graphImagePanel.repaint();
            }
        });

        JButton upd = new JButton("Update");
        upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphImagePanel.repaint();
            }
        });

        JTextField mark = new JTextField("Node mark");

        JButton addNodeButton = new JButton("Add node");
        addNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.addNode(mark.getText());
                graphImagePanel.repaint();
            }
        });

        JTextField weight = new JTextField("Edge weight");

        JButton addEdgeButton = new JButton("Add edge");
        addEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graph.getGraph().getWeighted()) {
                    try {
                        graphImagePanel.addEdge(Double.parseDouble(weight.getText()));
                    } catch (Exception ex) {
                        weight.setText("WRONG WEIGHT");
                    }
                } else {
                    graphImagePanel.addEdge();
                }

            }
        });

        JButton deleteEdgeButton = new JButton("Delete edge");
        deleteEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphImagePanel.deleteEdge();
            }
        });

        JButton deleteNodeButton = new JButton("Delete node");
        deleteNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphImagePanel.deleteNode();
            }
        });

        add(rebuild);
        //add(upd);
        add(addNodeButton);
        add(mark);
        add(addEdgeButton);
        if (graph.getGraph().getWeighted()) {
            add(weight);
        }
        add(deleteNodeButton);
        add(deleteEdgeButton);

    }
}
