package gui.menuItems;

import graph.adapters.GraphAdapter;
import gui.panels.OpenPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileOpenMenuItem extends JMenuItem {
 public FileOpenMenuItem(JFrame frame, GraphAdapter graph) {
        super("Open");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new OpenPanel(frame, graph);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
