package domain;

/**
 * Created by hmr on 05/06/2016.
 */
public class Player {

    private String name;
    private String position;
    private int jerseynumber;
    private String dateofbirth;
    private String nationality;
    private String contractuntil;
    private String marketvalue;

    public Player(String name, String position, int jerseyNumber, String dateOfBirth, String nationality, String contractUntil, String marketValue) {
        this.name = name;
        this.position = position;
        this.jerseynumber = jerseyNumber;
        this.dateofbirth = dateOfBirth;
        this.nationality = nationality;
        this.contractuntil = contractUntil;
        this.marketvalue = marketValue;
    }

    public String getName(){
        return name;
    }

    public String getPosition(){
        return position;
    }

    public int getJerseyNumber(){
        return jerseynumber;
    }

    public String getDateOfBirth(){
        return dateofbirth;
    }

    public String getNationality(){
        return nationality;
    }

    public String getContractUntil(){
            return contractuntil;
    }

    public String getMarketValue(){
        return marketvalue;
    }
}
