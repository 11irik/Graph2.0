package gui.panels;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import graph.Graph;
import graph.adapters.GraphAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class CreatorPanel extends JPanel{
    GraphAdapter graphAdapter;

    public CreatorPanel(GraphAdapter graphAdapter) {

        this.graphAdapter = graphAdapter;
        JButton refresh = new JButton("Open");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Graph graph = new Graph();
                Kryo kryo = new Kryo();

                try (Input input = new Input(new FileInputStream("file.dat"))) {
                    graph = (Graph) kryo.readClassAndObject(input);
                } catch (Exception e) {

                }
                graphAdapter.setGraph(graph);

            }
        });

        JButton path = new JButton("Animate");
        path.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //graph.executeStart();
            }
        });

        add(refresh);

        add(path);


    }
}
