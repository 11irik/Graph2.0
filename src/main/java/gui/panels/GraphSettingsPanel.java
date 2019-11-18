package gui.panels;

import graph.Graph;
import graph.wrappers.GraphWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphSettingsPanel extends JPanel {

    GraphWrapper graphWrapper;

    public GraphSettingsPanel(GraphWrapper graphWrapper) {
        this.graphWrapper = graphWrapper;
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphWrapper.setGraph(graphWrapper.getGraph().boruvka());
                graphWrapper.refresh();

            }
        });
        add(refresh);
    }
}
