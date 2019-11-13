package gui.panels;

import graph.wrappers.GraphWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphSettingsPanel extends JPanel {

    GraphWrapper graphWrapper;

    public GraphSettingsPanel(GraphWrapper graphWrapper) {
        this.graphWrapper = graphWrapper;
        JButton her = new JButton("Her");
        her.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphWrapper.addNode("hello");
            }
        });
        add(her);
    }
}
