public class Program {
    public static void main(String[] args) {
        Graph a = new Graph();
        a.addNeighbour("hel", "llo");
        a.addNeighbour("hel", "blo");

        a.show();
    }
}
