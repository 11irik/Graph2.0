public class Program {
    public static void main(String[] args) {
        Graph g = new Graph();
        ConsoleInterface consoleInterface = new ConsoleInterface(g);
        consoleInterface.help();
        consoleInterface.c();
    }
}
