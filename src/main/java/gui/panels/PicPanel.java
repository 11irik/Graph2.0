package gui.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PicPanel extends JPanel {

    public PicPanel() {
        ImageIcon imageIcon = new ImageIcon("/home/kirill/Documents/workspace/java/Graph2.0/src/main/resources/logo.png");
        JLabel label = new JLabel(imageIcon);
        add(label);
    }

}

