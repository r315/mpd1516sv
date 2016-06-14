package util;

import domain.League;
import domain.Player;
import domain.Standing;
import domain.Team;
import footballapi.dto.LeagueTableDto;
import footballapi.dto.SeasonDto;
import footballapi.dto.TeamDto;
import footballapi.dto.TeamPlayersDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hmr on 11/06/2016.
 */
public class DtoToDomainMapper {

    public static <T,R> List<R> domainMapper(List<T> t, Function<T,R> m){
        return t.stream()
                .map(m::apply)
                .collect(Collectors.toList());
    }

    public static League seasonToLeague(SeasonDto season){
        return new League(season.caption,
                season.id,
                season.league,
                season.year);

    }

    public static Standing leagueTableStandingToStanding(LeagueTableDto.StandingDto standing){
        return new Standing(
                standing.position, standing.teamName,standing.playedGames,
                standing.points, standing.goals, standing.goalsAgainst,
                standing.wins, standing.draws,standing.losses);
    }

    private static Player playerDtoToPlayer(TeamPlayersDto.PlayerDto dto) {
        return new Player(
                dto.name,dto.position,dto.jerseyNumber,dto.dateOfBirth,
                dto.nationality,dto.contractUntil,dto.marketValue);
    }

    public static Team teamDtoToTeam(TeamDto team){
        return new Team(team.name,team.code,team.shortName,team.squadMarketValue, team.crestUrl);
    }

    public static List<League> seasonsToLeagues(List<SeasonDto> seasons){
        return domainMapper(seasons, DtoToDomainMapper::seasonToLeague);
    }

    public static List<Standing> leagueTableToStandings(LeagueTableDto lt){
        return domainMapper(lt.standing, DtoToDomainMapper::leagueTableStandingToStanding);
    }


    public static List<Player> teamPlayersDtoToPlayers(TeamPlayersDto teamplayers) {
        return domainMapper(teamplayers.players, DtoToDomainMapper::playerDtoToPlayer);
    }


}
