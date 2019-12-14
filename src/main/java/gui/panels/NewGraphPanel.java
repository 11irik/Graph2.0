package gui.panels;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sun.deploy.panel.GeneralPanel;
import graph.Graph;
import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class NewGraphPanel extends JPanel {
    JRadioButton construct;
    JRadioButton generate;
    ConstructorPanel constructorPanel;
    GeneratorPanel generatorPanel;
    GraphAdapter graphAdapter;

    public NewGraphPanel(GraphAdapter graphAdapter) {
        construct = new JRadioButton("Construct");
        generate = new JRadioButton("Generate");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(construct);
        buttonGroup.add(generate);
        this.add(construct);
        this.add(generate);

        this.graphAdapter = graphAdapter;

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
            }
        });
        this.add(b);
    }

    public void createGraph() {
        if (construct.isSelected()) {
            graphAdapter.setGraph(constructorPanel.createGraph());
        }
    }
}
