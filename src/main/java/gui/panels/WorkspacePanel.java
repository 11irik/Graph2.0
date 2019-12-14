package gui.panels;

import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.*;

public class WorkspacePanel extends JPanel {
    public WorkspacePanel(GraphAdapter graph) throws Exception {
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        GraphImagePanel graphImagePanel = new GraphImagePanel(graph);
        c.weightx = 1920;
        c.weighty = 1080;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        add(graphImagePanel, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(new GraphSettingsPanel(graph, graphImagePanel), c);
    }
}
