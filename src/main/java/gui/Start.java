package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The main window of the application
 */
public class Start extends JFrame {

    public Start() {
        super("Graph");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width/8, dimension.height/8, dimension.width/4, dimension.height/2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GraphPanel panel = new GraphPanel();
        add(panel);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Start();
    }

}
