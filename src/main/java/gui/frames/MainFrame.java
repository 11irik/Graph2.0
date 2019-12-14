package gui.frames;

import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.panels.WorkspacePanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {
    GraphAdapter graph = new GraphAdapter();

    public MainFrame() throws Exception {
        super("Graph");
        setSize(1300, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem fileNew = new JMenuItem("New");
        fileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewGraphFrame newGraphFrame = new NewGraphFrame(graph);
                newGraphFrame.setSize(300, 200);
                newGraphFrame.setVisible(true);
            }
        });
        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Open");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    graph.setGraph(Graph.deserialize(f));

                }
            }
        });
        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.addActionListener(new ActionListener() {
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

        file.add(fileNew);
        file.add(fileOpen);
        file.add(fileSave);
        menuBar.add(file);
        setJMenuBar(menuBar);

        WorkspacePanel workspace = new WorkspacePanel(graph);
        add(workspace);
        revalidate();
    }
}
