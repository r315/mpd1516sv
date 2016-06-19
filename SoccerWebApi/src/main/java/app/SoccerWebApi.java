package app;

import httpserver.HttpServer;

import java.util.Scanner;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerWebApi {
    private static final int PORT = 8080;

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;

        SoccerController controller = new SoccerController(new HttpServer(PORT));

        try {
            controller.startServer();
        } catch (Exception e) {
            System.out.println("Fail to start server: "+e.getMessage());
            System.exit(-1);
        }
        System.out.println("Type Exit to end application");
        while(running){
            if(in.nextLine().equalsIgnoreCase("exit")){
                try {
                    controller.stopServer();
                } catch (Exception e) {
                    System.out.println("Fail to stop server: "+e.getMessage());
                }
                running = false;
            }
        }
    }
}
