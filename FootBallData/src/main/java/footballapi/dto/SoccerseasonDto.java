package footballapi.dto;

/**
 * Created by hmr on 07/06/2016.
 */
public class SoccerSeasonDto {
    private int id;
    private String caption;
    private String league;
    private String year;
    private Links _links;



    public String getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    private class Links {
        private Self self;

        private class Self {
            private String href;
        }
    }
}
