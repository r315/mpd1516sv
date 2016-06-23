package app;

import footballapi.FootBallApiImpl;
import httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * Created by hmr on 11/06/2016.
 */
public class SoccerWebApi {
    private static final int PORT = 8080;

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;

        SoccerController controller = new SoccerController(new HttpServer(PORT),new FootBallApiImpl());

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
                    controller.close();
                } catch (Exception e) {
                    System.out.println("Fail to stop server: "+e.getMessage());
                }
                running = false;
            }
        }
    }
}




class StreamTests{
    private static String [] fruits = {"Apple","Orange","Grapes","Banana","Pera","Pineapple","Peach","password"};

    public static BufferedReader openFile(String fileName){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            return reader;
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void main(String [] args){
        System.out.println("Fruit Count: "  +
                Stream.of(fruits)
                        .peek(System.out::println)
                        .count());

        System.out.println("----------------------------------------");

        BufferedReader words = openFile("wordsEn.txt");
        System.out.println("Words Count: " + words.lines()
                .filter(s -> Stream.of(fruits).anyMatch(t -> s.contains(t)))
                .sorted(comparing((String s) -> s.length()).reversed())
                .peek(System.out::println)
                .count());

        try{
            words.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

}