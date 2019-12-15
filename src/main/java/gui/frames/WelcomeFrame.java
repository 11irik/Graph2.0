package gui.frames;

import graph.adapters.GraphAdapter;
import gui.panels.PicPanel;
import gui.panels.WelcomePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomeFrame extends JFrame {
    public WelcomeFrame() throws IOException {
        super("Welcome to Grapher");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GraphAdapter graph = new GraphAdapter();

        WelcomePanel welcomePanel = new WelcomePanel(this, graph);
        add(welcomePanel);

        setVisible(true);
    }
}
