package cui;

import java.io.FileNotFoundException;

public class Start {
    public static void main(String[] args) throws FileNotFoundException {
        GraphCreator gInterface = new GraphCreator();
        GraphCreator.help();
        gInterface.c();
    }

}
