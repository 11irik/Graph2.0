package graph;

import java.io.FileNotFoundException;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        ConsoleGInterface gInterface = new ConsoleGInterface();
        gInterface.help();
        gInterface.c();
    }
}
