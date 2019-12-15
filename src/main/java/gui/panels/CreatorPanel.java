package gui.panels;

import graph.adapters.GraphAdapter;
import gui.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatorPanel extends JPanel {
    JRadioButton construct;
    JRadioButton generate;
    ConstructorPanel constructorPanel;
    GeneratorPanel generatorPanel;
    GraphAdapter graph;
    ArrayList<JFrame> fatherFrames;
    Boolean mainFrame;

    public CreatorPanel(GraphAdapter graph) {
        fatherFrames = new ArrayList<>();
        this.graph = graph;
        mainFrame = false;

        construct = new JRadioButton("Construct");
        generate = new JRadioButton("Generate");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(construct);
        buttonGroup.add(generate);
        this.add(construct);
        this.add(generate);

        constructorPanel = new ConstructorPanel();
        generatorPanel = new GeneratorPanel();
        this.add(constructorPanel);
        this.add(generatorPanel);
        constructorPanel.setVisible(false);
        generatorPanel.setVisible(false);

        construct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                constructorPanel.setVisible(true);
                generatorPanel.setVisible(false);
            }
        });

        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                constructorPanel.setVisible(false);
                generatorPanel.setVisible(true);
            }
        });

        JButton b = new JButton("Create");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createGraph();
                if (mainFrame == false) {
                    new MainFrame(graph);
                }
            }
        });

        this.add(b);
    }

    public void addFatherFrame(JFrame frame) {
        fatherFrames.add(frame);
    }

    public void setMainFrame(Boolean hasMain) {
        this.mainFrame = hasMain;
    }

    private void disposeFatherFrames() {
        for (JFrame frame : fatherFrames) {
            frame.dispose();
        }
    }

    public void createGraph() {
        if (construct.isSelected()) {
            graph.setGraph(constructorPanel.createGraph());
            disposeFatherFrames();
        } else {
            graph.setGraph(generatorPanel.createGraph());
            disposeFatherFrames();
        }
    }
}
