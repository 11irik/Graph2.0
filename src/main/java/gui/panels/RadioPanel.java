package gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioPanel extends JPanel {
    JRadioButton construct;
    JRadioButton generate;
    public RadioPanel(UnitePanel unitePanel) {
        construct = new JRadioButton("Construct");
        generate = new JRadioButton("Generate");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(construct);
        buttonGroup.add(generate);

        this.setLayout(new GridLayout(0, 1));
        this.add(construct);
        this.add(generate);

        construct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitePanel.getConstructorPanel().setVisible(true);
                unitePanel.getGeneratorPanel().setVisible(false);
            }
        });

        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unitePanel.getConstructorPanel().setVisible(false);
                unitePanel.getGeneratorPanel().setVisible(true);
            }
        });
    }
    public boolean getConstructSelected() {
        return construct.isSelected();
    }
}
