package app;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerWebApi {

    public static void main(String [] args) throws Exception {

        new SoccerController().start();

        while(true);
    }
}
