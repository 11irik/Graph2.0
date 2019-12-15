package gui.panels;

import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(JFrame frame, GraphAdapter graph) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        WelcomeButtonsPanel welcomeButtonsPanel = new WelcomeButtonsPanel(frame, graph);
        add(welcomeButtonsPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.gridwidth = 3;
        PicPanel picPanel = new PicPanel();
        add(picPanel, c);
    }
}
