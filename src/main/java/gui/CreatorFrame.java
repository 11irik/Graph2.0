package gui;

import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorFrame extends JFrame {
    GraphAdapter graphAdapter;

    public CreatorFrame(GraphAdapter graphAdapter) {

        this.graphAdapter = graphAdapter;
        JButton refresh = new JButton("Rebuild");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                graphAdapter.refresh();
                //graph.repaint();
            }
        });

        JButton path = new JButton("Animate");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //graph.executeStart();
            }
        });

        add(refresh);

        add(path);
    }
}
