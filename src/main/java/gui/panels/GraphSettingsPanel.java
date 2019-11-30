package gui.panels;

import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphSettingsPanel extends JPanel {

    GraphAdapter graphAdapter;

    public GraphSettingsPanel(GraphAdapter graphAdapter) {
        this.graphAdapter = graphAdapter;
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphAdapter.setGraph(graphAdapter.getGraph().boruvka());
                graphAdapter.refresh();

            }
        });
        add(refresh);
    }
}
