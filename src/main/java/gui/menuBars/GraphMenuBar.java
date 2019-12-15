package gui.menuBars;

import graph.adapters.GraphAdapter;
import gui.menuItems.FileNewItem;
import gui.menuItems.FileOpenMenuItem;
import gui.menuItems.FileSaveMenuItem;

import javax.swing.*;

public class GraphMenuBar extends JMenuBar {

    public GraphMenuBar(JFrame frame, GraphAdapter graph) {
        JMenu file = new JMenu("File");

        JMenuItem fileNew = new FileNewItem(frame, graph);
        JMenuItem fileOpen = new FileOpenMenuItem(frame, graph);
        JMenuItem fileSave = new FileSaveMenuItem(graph);

        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
        this.add(file);
    }
}
