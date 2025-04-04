import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Game> games = new ArrayList<>();

    public static void main(String[] args) {
        List<Game> game = createGame();
        printGames(game);
    }

    public static List<Player> createPlayers(List<Team> teams) {
        List<Player> players = new ArrayList<>();

        String[][] playerData = {
                {"0", "Allen Iverson", "1"},
                {"0", "Aaron McKie", "2"},
                {"0", "Dikembe Mutombo", "3"},
                {"0", "Tyrone Hill", "4"},
                {"0", "Jumaine Jones", "5"},
                {"1", "Kobe Bryant", "6"},
                {"1", "Shaquille O'Neal", "7"},
                {"1", "Rick Fox", "8"},
                {"1", "Horace Grant", "9"},
                {"1", "Derek Fisher", "10"}
        };

        int teamCounter = 0;
        for(Team team : teams) {
            List<Player> teamPlayers = Player.populatePlayer(playerData, teamCounter);
            for(Player player : teamPlayers) {
                team.addPlayer(player);
            }
            teamCounter++;
        }

        return players;
    }


    // Teams
    public static List<Team> createTeams() {
        String[][] teamData = {
            {"Philadelphia 76ers", "Conference 1"},
            {"Los Angeles Lakers", "Conference 2"}
        };

        List<Team> teams = Team.populateTeams(teamData);

        createPlayers(teams);

        return teams;
    }

    // Game
    public static List<Game> createGame() {
        List<Team> teams = createTeams();

        String[][] gameData = {
                {"1", "2001-06-06", "0", "1", "18997"}
        };

        games = Game.populateGames(teams, gameData);

        return games;
    }
    public static void printGames(List<Game> games) {
        for(Game game : games) {
            game.printGameDetails();
        }
    }
}