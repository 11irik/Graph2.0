import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ConsoleInterface {
    static Scanner in = new Scanner(System.in);
    Graph graph;

    public ConsoleInterface(Graph g) {
        graph = g;
    }

    static String[] methodNames = {"Add node - 1", "Add edge - 2", "Show nodes - 3", "Save - 4"};

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
        int n = 1;
        while (n != 0) {
            System.out.println("Enter n: ");
            n = in.nextInt();
            switch (n) {
                case (1):
                    System.out.println("Enter node: ");
                    String s = in.next();
                    Node a = new Node(s);
                    graph.addNode(a);
                    break;
                case (2):
                    System.out.println("Enter node a and node b: ");
                    break;
                case (3):
                    System.out.println(graph.getNodes());
                    break;
                case (4):
                    try (Writer writer = new FileWriter("Output.json")) {
                        Gson gson = new GsonBuilder().create();
                        gson.toJson(graph, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Saved");
                    break;
            }
        }
    }
}
