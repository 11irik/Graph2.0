package gui.menuBars;

import graph.adapters.GraphAdapter;
import gui.menuItems.FileNewItem;
import gui.menuItems.FileOpenMenuItem;
import gui.menuItems.FileSaveMenuItem;

import javax.swing.*;

public class GraphMenuBar extends JMenuBar {

    public GraphMenuBar(GraphAdapter graph) {
        JMenu file = new JMenu("File");

        JMenuItem fileNew = new FileNewItem(graph);
        JMenuItem fileOpen = new FileOpenMenuItem(graph);
        JMenuItem fileSave = new FileSaveMenuItem(graph);

        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
        this.add(file);
    }
}
