package footballapi.dto;

import java.util.List;

/**
 * Created by hmr on 14/06/2016.
 */
public class TeamPlayersDto {

    public int count;
    public List<PlayerDto> players;

    public class PlayerDto {
        public String name;
        public String position;
        public int jerseyNumber;
        public String dateOfBirth;
        public String nationality;
        public String contractUntil;
        public String marketValue;
    }
}
