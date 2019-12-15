package gui.buttons;

import graph.adapters.GraphAdapter;
import gui.frames.CreateGraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGraphButton extends JButton {
    public NewGraphButton(JFrame frame, GraphAdapter graph) {
        super("New");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateGraphFrame createGraphFrame = new CreateGraphFrame(frame, graph);
                createGraphFrame.setVisible(true);
            }
        });
    }

}
