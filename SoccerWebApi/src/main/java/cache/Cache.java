package cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;


/**
 * Created by hmr on 21/06/2016.
 */



public class Cache {
    Map<String,String> htmlPages = new HashMap<String,String>();
    Function<String,String> getFromFileSystem;
    private Function<String,String> getFromApi;


    public Cache() {
        htmlPages = new HashMap<String, String>();
    }

    private String getFromMemory(String uri){

        return "Not yet!";
    }

    public String getPage(String uri){
        Function<String,String> sup = this::getFromMemory;
        return sup.andThen(getFromFileSystem).andThen(getFromApi).apply(uri);
    }

}
