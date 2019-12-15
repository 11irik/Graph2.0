package gui.panels;

import javax.swing.*;

public class UnitePanel extends JPanel {
    private ConstructorPanel constructorPanel;
    private GeneratorPanel generatorPanel;
    public UnitePanel() {
        constructorPanel = new ConstructorPanel();
        generatorPanel = new GeneratorPanel();
        add(constructorPanel);
        add(generatorPanel);
        constructorPanel.setVisible(false);
        generatorPanel.setVisible(false);
    }

    public ConstructorPanel getConstructorPanel() {
        return constructorPanel;
    }

    public GeneratorPanel getGeneratorPanel() {
        return generatorPanel;
    }
}
