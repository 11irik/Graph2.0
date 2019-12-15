package gui.panels;

import graph.Graph;
import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ConstructorPanel extends JPanel {
    JCheckBox weight;
    JCheckBox orient;

    public ConstructorPanel() {
        weight = new JCheckBox("Weighted");
        orient = new JCheckBox("Oriented");
        this.add(weight);
        this.add(orient);
    }

    public Graph createGraph() {
        boolean weighted = false;
        boolean oriented = false;
        if (weight.isSelected()) {
            weighted = true;
        }
        if (orient.isSelected()) {
            oriented = true;
        }

        return new Graph(oriented, weighted);
    }
}
