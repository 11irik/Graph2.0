package cui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Start {
    public static void main(String[] args) throws Exception {
        GraphCreator gInterface = new GraphCreator();
        GraphCreator.help();
        gInterface.c();
    }

}
