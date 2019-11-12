package gui;

import javax.swing.*;
import java.awt.*;

/**
 * The main window of the application
 */
public class Start extends JFrame {

    public Start() {
        super("Graph");

        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GraphPanel panel = new GraphPanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Start();
    }

}
