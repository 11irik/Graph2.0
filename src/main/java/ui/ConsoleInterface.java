package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.Graph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ConsoleInterface {
    private Graph graph;
    private static Scanner in = new Scanner(System.in);
    private static String[] methodNames = {"Add node - 1", "Add edge - 2", "Remove node - 3", "Remove edge - 4", "Save - 5", "Show - 6", "Exit - 0"};

    public ConsoleInterface(Graph graph) {
        this.graph = graph;
    }

    public static void name() {
        System.out.println("========Graph editor========");
    }

    public static void help() {
        String s = "";

        for (int i = 0; i < methodNames.length; ++i) {
            s += String.format(" %15s\t", methodNames[i]);
            if (i % 2 != 0) {
                s += "\n";
            }
        }
        System.out.println(s);
    }

    public void c() {
        name();
        help();
        int n = 1;
        while (n != 0) {
            System.out.println("Enter n: ");
            n = in.nextInt();
            switch (n) {
                case (1):
                    System.out.println("Enter node: ");
                   // graph.addNode(in.next());
                    break;
                case (2):
                    if (graph.isWeighted()) {
                        System.out.println("Enter nodes a, b and weight: ");
                       // graph.addEdge(in.next(), in.next(), in.nextDouble());
                    }
                    else{
                        System.out.println("Enter nodes a and b: ");
                       // graph.addEdge(in.next(), in.next());
                    }
                    break;
                case (3):
                    System.out.println("Enter node: ");
                   // graph.deleteNode(in.next());
                    break;
                case (4):
                    System.out.println("Enter nodes a and b: ");
                   // System.out.println(graph.deleteEdge(in.next(), in.next()));
                    break;
                case (5):
                    try (Writer writer = new FileWriter("Output.json")) {
                        Gson gson = new GsonBuilder().create();
                        gson.toJson(graph, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                       }
                    break;
                case (6):
                    System.out.println(graph.toString());
                case (0):
                    break;
            }
        }
    }
}
