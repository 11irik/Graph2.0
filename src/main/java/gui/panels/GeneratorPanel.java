package gui.panels;


import graph.Generator;
import graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratorPanel extends JPanel{
    JRadioButton buttonTree;
    JRadioButton buttonHamiltonian;
    JRadioButton buttonFullGraph;
    JTextField nodeCount;
    JTextField edgeCount;


    public GeneratorPanel() {
        this.setLayout(new GridLayout(0, 1));

        buttonFullGraph = new JRadioButton("Full graph");
        buttonTree = new JRadioButton("Tree");
        buttonHamiltonian = new JRadioButton("Hamiltonian");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonTree);
        buttonGroup.add(buttonHamiltonian);
        buttonGroup.add(buttonFullGraph);
        this.add(buttonTree);
        this.add(buttonFullGraph);
        this.add(buttonHamiltonian);

        nodeCount = new JTextField("Nodes");
        this.add(nodeCount);

        edgeCount = new JTextField("0");
        edgeCount.setVisible(false);
        this.add(edgeCount);

        buttonHamiltonian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonHamiltonian.isSelected()) {
                    edgeCount.setVisible(true);
                }
            }
        });

        buttonTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonTree.isSelected()) {
                    edgeCount.setVisible(false);
                }
            }
        });

        buttonFullGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonFullGraph.isSelected()) {
                    edgeCount.setVisible(false);
                }
            }
        });
    }

    public Graph createGraph() {
        Generator generator = new Generator(Integer.parseInt(nodeCount.getText()), Integer.parseInt(edgeCount.getText()), false, false);
        if (buttonFullGraph.isSelected()) {
            return generator.nextFull();
        }
        if (buttonTree.isSelected()) {
            return generator.nextTree();
        }
        if (buttonHamiltonian.isSelected()) {
            return generator.nextHamiltonian();
        }
        return null;
    }
}
