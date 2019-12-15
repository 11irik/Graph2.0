package gui.popups;

import graph.adapters.GraphAdapter;

import javax.swing.*;

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
