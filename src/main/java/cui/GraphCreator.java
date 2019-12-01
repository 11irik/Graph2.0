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

    public GraphCreator() {
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
