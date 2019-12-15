package gui.menuItems;

import graph.Graph;
import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileSaveMenuItem extends JMenuItem {
    public FileSaveMenuItem(GraphAdapter graph) {
        super("Save");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Save");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    Graph g = new Graph(graph.getGraph(), true);
                    Graph.serialize(g, f);
                }
            }
        });
    }
}
