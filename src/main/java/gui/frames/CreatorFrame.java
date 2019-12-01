package gui.frames;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import graph.Graph;
import graph.adapters.GraphAdapter;
import gui.panels.CreatorPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class CreatorFrame extends JFrame {
    GraphAdapter graphAdapter;

    public CreatorFrame(GraphAdapter graphAdapter) {
        this.graphAdapter = graphAdapter;
        CreatorPanel creatorPanel = new CreatorPanel(graphAdapter);
        add(creatorPanel);
    }
}
