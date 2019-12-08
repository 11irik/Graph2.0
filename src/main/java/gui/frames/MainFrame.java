package gui.frames;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.panels.GraphImagePanel;
import gui.panels.GraphSettingsPanel;
import gui.panels.WorkspacePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
                CreatorFrame creatorFrame = new CreatorFrame(graph);
                creatorFrame.setSize(1300, 700);
                creatorFrame.setVisible(true);
            }
        });

        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    try {
                        InputStream stream = new FileInputStream(f);
                        Kryo kryo = new Kryo();
                        try (Input input = new Input(stream)) {
                            graph.setGraph((Graph) kryo.readClassAndObject((Input) input));
                            System.out.println();
                        } catch (Exception e) {

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        file.add(fileNew);
        file.add(fileOpen);
        menuBar.add(file);
        setJMenuBar(menuBar);

        WorkspacePanel workspace = new WorkspacePanel(graph);
        add(workspace);
        revalidate();
    }
}
