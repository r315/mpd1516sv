package domain.mapper;

import domain.League;
import footballapi.dto.SoccerSeasonDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hmr on 11/06/2016.
 */
public class DtoToDomainMapper {

    public static League seasonToLeague(SoccerSeasonDto season){
        return new League(season.caption,
                season.id,
                season.league,
                season.year);

    }

    public static List<League> seasonsToLeagues(List<SoccerSeasonDto> seasons){
        return seasons.stream()
                .map(DtoToDomainMapper::seasonToLeague)
                .collect(Collectors.toList());
    }


}
