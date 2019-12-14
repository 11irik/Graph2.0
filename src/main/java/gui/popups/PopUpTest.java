package gui.popups;

import graph.adapters.GraphAdapter;
import gui.frames.NodeCreatorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpTest extends JPopupMenu {
    public PopUpTest(GraphAdapter graphAdapter) {

        JMenu menu = new JMenu("Add node");

        JMenuItem addNode = new JMenuItem("Add");
        JTextField text = new JTextField();
        menu.add(text);
        menu.add(addNode);

        this.add(menu);

        JMenuItem menuItem = new JMenuItem("Add edge");
        this.add(menuItem);


    }
}
