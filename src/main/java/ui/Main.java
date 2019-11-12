package ui;

import graph.Graph;
import graph.Node;
import ui.ConsoleGInterface;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ConsoleGInterface gInterface = new ConsoleGInterface();
        ConsoleGInterface.help();
        gInterface.c();
    }

}
