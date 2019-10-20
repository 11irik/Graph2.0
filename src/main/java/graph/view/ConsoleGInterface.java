package graph.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.model.Graph;

import java.io.*;
import java.util.Scanner;

public class ConsoleGInterface {
    Graph graph;
    private ConsoleInterface cInterface;

    private static Scanner in = new Scanner(System.in);
    private static String[] methodNames = {"Create graph - 1", "Deserialize - 2", "Serialize - 3", "Graph options - 4"};

    public ConsoleGInterface() {
    }

    public static void name() {
        System.out.println("========Graph creator========");
    }

    public static void help() {
        String s = "";

        for (int i = 0; i < methodNames.length; ++i) {
            s += String.format(" %20s\t", methodNames[i]);
            if (i % 2 != 0) {
                s += "\n";
            }
        }
        System.out.println(s);
    }

    public void c() throws FileNotFoundException {
        name();
        int n = 1;
        while (n != 0) {
            Gson gson = new GsonBuilder().create();

            System.out.println("Enter n: ");
            n = in.nextInt();
            switch (n) {
                case (1):
                    System.out.println("Oriented - y/n:");
                    Boolean oriented;
                    String v = in.next();
                    if(v.equals("y")) {
                        oriented = true;
                    }
                    else {
                        oriented = false;
                    }
                    System.out.println("Weighted - y/n:");
                    Boolean weighted;
                    if(in.next().equals("y")) {
                        weighted = true;
                    }
                    else {
                        weighted = false;
                    }
                    graph = new Graph(oriented, weighted);
                    break;
                case (2):
                    System.out.println("Enter file name");
                    Reader reader = new FileReader(in.next());
                    graph = gson.fromJson(reader, Graph.class);
                    break;
                case (3):
                    System.out.println("Enter file name");;
                    try (Writer writer = new FileWriter(in.next())) {
                        gson.toJson(graph, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case (4):
                    cInterface = new ConsoleInterface(graph);
                    cInterface.c();
                    name();
                    help();
                    break;
                case (5):
                    System.out.println(graph.getZeros());
                    break;
                case (6):
                    System.out.println("Enter nodes a and b: ");
                    System.out.println(graph.doesCallingExist(in.next(), in.next()));
                    break;
            }
        }
    }

}
