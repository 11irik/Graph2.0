package graph;

import graph.view.ConsoleGInterface;
import graph.view.ConsoleInterface;

import java.io.FileNotFoundException;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        ConsoleGInterface gInterface = new ConsoleGInterface();
        gInterface.help();
        gInterface.c();
    }
}
