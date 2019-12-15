package gui.menuItems;

import graph.adapters.GraphAdapter;
import gui.frames.CreateGraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileNewItem extends JMenuItem {
    public FileNewItem(GraphAdapter graph) {
        super("New");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateGraphFrame createGraphFrame = new CreateGraphFrame(graph);
                createGraphFrame.setSize(300, 200);
                createGraphFrame.setVisible(true);
            }
        });
    }
}
