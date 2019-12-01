package cui;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import graph.Graph;

import java.io.*;
import java.util.Scanner;

public class GraphCreator {
    Graph graph;
    private GraphSettings cInterface;

    private static Scanner in = new Scanner(System.in);
    private static String[] methodNames = {"Create graph - 1", "Deserialize - 2", "Serialize - 3", "Show graph - 4", "Graph editor - 5"};

    public GraphCreator() throws Exception {
        graph = new Graph(true, true);
        graph.addNode("5");
        graph.addNode("6");
        graph.addNode("2");
        graph.addNode("1");
        graph.addNode("10");
        graph.addNode("11");
        graph.addNode("9");
        graph.addNode("12");


        graph.addEdge("5", "6", 6);
        graph.addEdge("5", "2", 9);
        graph.addEdge("6", "2", 12);
        graph.addEdge("5", "11", 3);
        graph.addEdge("11", "1", 10);
        graph.addEdge("1", "10", 20);
        graph.addEdge("2", "9", 7);
        graph.addEdge("10", "9", 13);
        graph.addEdge("10", "12", 8);
        graph.addEdge("11", "12", 14);
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
            Kryo kryo = new Kryo();

            System.out.println("Enter n: ");
            n = in.nextInt();
            switch (n) {
                case (1):
                    System.out.println("Oriented - y/n:");
                    Boolean oriented;
                    String v = in.next();
                    oriented = v.equals("y");
                    System.out.println("Weighted - y/n:");
                    Boolean weighted;
                    weighted = in.next().equals("y");
                    graph = new Graph(oriented, weighted);
                    break;
                case (2):
                    System.out.println("Enter file name");
                    try (Input input = new Input(new FileInputStream("file.dat"))) {
                        graph = (Graph) kryo.readClassAndObject(input);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Exception was processed. Program continues");
                        name();
                        help();
                    }
                    break;
                case (3):
                    System.out.println("Enter file name");
                    try (Output output = new Output(new FileOutputStream("file.dat"))) {
                        kryo.writeClassAndObject(output, graph);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case (4):
                    try {
                        System.out.println(graph.toString());
                    }
                    catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Exception was processed. Program continues");
                    }
                    break;
                case (5):
                    try {
                        cInterface = new GraphSettings(graph);
                        cInterface.c();
                    }
                    catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Exception was processed. Program continues");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    name();
                    help();
                    break;
                    //Tasks
                case (10):
                    System.out.println(graph.getZeros());
                    break;
                case (11):
                    System.out.println("Enter nodes a and b: ");
                    //System.out.println(graph.doesCallingExist(in.next(), in.next()));
                    break;
                case (12):
                    System.out.println(graph.deleteOddEdges());
                    break;
                case (13):
                    graph.getEdges();
                    break;

            }
        }
    }
}
