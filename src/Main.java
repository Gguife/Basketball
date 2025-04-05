import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static List<Game> games = new ArrayList<>();

    public static void main(String[] args) {
        // Call the createGame method to start the process
        List<Game> gameList = createGame();

        // Print game results
        printGameResults();
    }

    // Method to create games and integrate player stats
    public static List<Game> createGame() {

        // Calls createTeams to create and populate teams
        List<Team> teams = createTeams();

        // Calls createPlayers to populate players for each team
        List<Player> players = createPlayers(teams);

        // Game-specific data for stats integration
        int[][][] playerStatsOfAllGames = {
                {
                        {18, 3, 9, 2, 3, 6},
                        {3, 1, 2, 2, 5, 9},
                        {4, 0, 5, 5, 11, 0},
                        {1, 0, 2, 1, 5, 0},
                        {2, 0, 0, 0, 0, 0},
                        {7, 0, 1, 0, 3, 5},
                        {17, 0, 10, 6, 14, 5},
                        {7, 3, 2, 1, 6, 5},
                        {3, 0, 2, 4, 1, 0},
                        {0, 0, 0, 0, 0, 1}
                },
                {
                        {10, 3, 0, 0, 4, 3},
                        {6, 2, 0, 4, 2, 6},
                        {5, 0, 6, 4, 9, 1},
                        {1, 0, 0, 1, 4, 0},
                        {1, 1, 0, 0, 3, 0},
                        {11, 1, 8, 1, 7, 6},
                        {12, 0, 4, 8, 12, 9},
                        {5, 2, 2, 0, 0, 3},
                        {0, 0, 0, 1, 5, 2},
                        {2, 0, 2, 2, 3, 2}
                },
                {
                        {12, 3, 10, 2, 10, 4},
                        {9, 0, 5, 5, 7, 0},
                        {2, 0, 1, 1, 5, 8},
                        {1, 0, 0, 0, 2, 0},
                        {1, 1, 0, 0, 1, 0},
                        {13, 0, 6, 0, 6, 3},
                        {11, 0, 8, 3, 9, 3},
                        {2, 0, 3, 1, 1, 2},
                        {2, 0, 0, 3, 4, 1},
                        {1, 0, 1, 0, 0, 2}
                },
                {
                        {12, 1, 10, 1, 3, 4},
                        {9, 0, 1, 3, 6, 0},
                        {1, 0, 3, 0, 3, 2},
                        {3, 0, 1, 2, 5, 1},
                        {0, 0, 0, 1, 2, 1},
                        {6, 0, 7, 2, 8, 9},
                        {1, 0, 8, 8, 6, 5},
                        {4, 2, 0, 0, 1, 1},
                        {2, 1, 2, 0, 1, 4},
                        {1, 0, 0, 0, 5, 0}
                }
        };

        String[] gameDates = {
                "2001-06-06",
                "2001-06-08",
                "2001-06-10",
                "2001-06-13"
        };

        int[] gameAttendance = {
                18997,
                18997,
                20900,
                20896
        };

        // Create game data array
        String[][] gameData = new String[gameDates.length][5];
        for (int i = 0; i < gameDates.length; i++) {
            gameData[i][0] = String.valueOf(i + 1); // Game number
            gameData[i][1] = gameDates[i]; // Game date
            gameData[i][2] = "0"; // Home team index
            gameData[i][3] = "1"; // Away team index
            gameData[i][4] = String.valueOf(gameAttendance[i]); // Attendance
        }

        // Populate games using the teams and game data
        games = Game.populateGame(teams, gameData);

        // Integrate player stats into games
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            List<PlayerGameStats> playerStatsList = PlayerGameStats.populatePlayerGameStats(game, players, playerStatsOfAllGames[i]);
            for (PlayerGameStats stats : playerStatsList) {
                game.addPlayerGameStats(stats);
            }
        }

        return games;  // Return the list of games
    }

    // Method to create teams and call createPlayers
    public static List<Team> createTeams() {

        String[][] teamData = {
                {"Philadelphia 76ers", "Conference 1"},
                {"Los Angeles Lakers", "Conference 2"}
        };

        // Populate teams
        List<Team> teams = Team.populateTeams(teamData);

        // Create players for each team
        createPlayers(teams);  // Call createPlayers to associate players with teams

        return teams;
    }

    // Method to create players and associate them with teams
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

        // Players population into teams
        int teamCounter = 0;
        for (Team team : teams) {
            List<Player> teamPlayers = Player.populatePlayer(playerData, teamCounter);
            for (Player player : teamPlayers) {
                team.addPlayer(player);
                players.add(player);
            }
            teamCounter++;
        }

        return players;
    }

    // Mehthod tpo display game results (summary + highest scorer)
    public static void printGameResults(){
        int gameIndex = 1; // Initialize game index

        for (Game game : games) {
            System.out.println("Game # " + gameIndex + ":");
            System.out.println(game.gameSummary());
            System.out.println("-------------------------------------------------------");

            // Print highest scorers from each team
            Map<Team, Player> teamHighestScorer = game.highestScorer();
            for (Map.Entry<Team, Player> entry : teamHighestScorer.entrySet()) {
                Team team = entry.getKey();
                Player scorer = entry.getValue();
                System.out.println("Team: " + team.getName() + ", Highest Scorer: " + scorer.getName());
            }
            System.out.println("-------------------------------------------------------");
            gameIndex++;

        }
    }
}