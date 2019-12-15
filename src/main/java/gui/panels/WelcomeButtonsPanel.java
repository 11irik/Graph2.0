package gui.panels;

import graph.adapters.GraphAdapter;
import gui.buttons.NewGraphButton;
import gui.buttons.OpenGraphButton;

import javax.swing.*;
import java.awt.*;

public class WelcomeButtonsPanel extends JPanel {
    public WelcomeButtonsPanel(JFrame frame, GraphAdapter graph) {
        setLayout(new GridLayout(0, 1));
        JButton newButton = new NewGraphButton(frame, graph);
        JButton openButton = new OpenGraphButton(frame, graph);
        add(openButton);
        add(newButton);
    }
}
