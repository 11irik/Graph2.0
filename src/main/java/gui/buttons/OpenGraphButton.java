package gui.buttons;

import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.panels.OpenPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenGraphButton extends JButton {
    public OpenGraphButton(JFrame frame, GraphAdapter graph) {
        super("Open");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new OpenPanel(frame, graph);
            }
        });
    }
}
