package domain;

import java.time.LocalDate;

/**
 * Created by hmr on 05/06/2016.
 */
public class Player {

    private String name;
    private String position;
    private int jnumber;
    private LocalDate birthdate;
    private String nationality;
    private String contractFinal;
    private String marketValue;

    public String GetName(){
        return name;
    }

    public String getPosition(){
        return position;
    }

    public int getJerseyNumber(){
        return jnumber;
    }

    public String getDataOfBirth(){
        return birthdate.toString();
    }

    public String getNationality(){
        return nationality;
    }

    public String getContractUntil(){
            return contractFinal;
    }

    public String getMarketValue(){
        return marketValue;
    }
}
