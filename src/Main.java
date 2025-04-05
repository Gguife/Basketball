import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        // Call the createTournament method to start the process
        Tournament tournament = createTournament();

        // Print tournament details here
        calculateTournamentStats(tournament);
    }

    // Create a tournament and populate its components
    public static Tournament createTournament() {

        List<Team> teams = createTeams();
        List<Player> players = createPlayers(teams);

        // Create tournament
        Tournament tournament = new Tournament("Philadelphia 76ers at Los Angeles Lakers", "2001");

        // Add teams to the tournament
        for (Team team : teams) {
            tournament.addTeam(team);
        }

        // Create games and add to tournament
        List<Game> games = createGames(teams, players);
        for (Game game : games) {
            tournament.addGame(game);
        }

        return tournament;
    }

    // Create and populate teams
    public static List<Team> createTeams() {

        String[][] teamData = {
                {"Philadelphia 76ers", "Conference 1"},
                {"Los Angeles Lakers", "Conference 2"}
        };

        return Team.populateTeams(teamData);
    }

    // Create players and assign to teams
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

    // Create games and add player stats
    public static List<Game> createGames(List<Team> teams, List<Player> players) {
        List<Game> games = new ArrayList<>();

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
                        {9,	0, 1, 3, 6,	0},
                        {1,	0, 3, 0, 3, 2},
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

        return games;
    }

    // Helper method to add player stats to the game
    public static void addPlayerStatsToGame(Game game, List<PlayerGameStats> playerStatsList) {
        for (PlayerGameStats stats : playerStatsList) {
            game.addPlayerGameStats(stats);  // Add each player's stats to the game
        }
    }

    // Method to calculate and print tournament statistics
    public static void calculateTournamentStats(Tournament tournament) {

        // Print tournament details
        System.out.println("\nTournament: " + tournament.getName());
        System.out.println("-------------------------------------------------------\n");

        List<Game> games = tournament.getGames();

        // Calculate and print game-wise stats
        System.out.println("---Game-wise Stats---");

        for (Game game : games) {
            System.out.println("Game # " + game.getGameNumber() + ":");
            System.out.println(game.gameSummary());
            System.out.println("-------------------------------------------------------\n");

            //Print highest scorers for each team
            printTeamHighestScorers(game);
            System.out.println("-------------------------------------------------------\n");
        }

        // Calculate and print the tournmant winner and tournament-wise stats
        printTournamentWinnerAndHighestScorer(tournament, games);

        // Display MVP stats
        printMVPStats(tournament);
        System.out.println("-------------------------------------------------------\n");
    }

    public static void printTeamHighestScorers(Game game) {
        // Get the highest scorer for each team
        Map<Team, Player> teamHighestScorer = game.highestScorer();

        // Print the highest scorer for each team
        for (Map.Entry<Team, Player> entry : teamHighestScorer.entrySet()) {
            Team team = entry.getKey();
            Player scorer = entry.getValue();
            System.out.println("Team: " + team.getName() + ", Highest Scorer: " + scorer.getName());
        }
    }

    // Method to print MVP stats
    public static void printMVPStats(Tournament tournament) {
        // Get the MVP stats
        Map<String, String> mvpStats = tournament.mvpStats(new ArrayList<>(tournament.getGames()));

        // Print the MVP stats
        System.out.println("MVP Stats:");
        for (Map.Entry<String, String> entry : mvpStats.entrySet()) {
            System.out.println("-- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    // Function to print tournament winner and highest scorer
    public static void printTournamentWinnerAndHighestScorer(Tournament tournament, List<Game> games) {
        // Get the tournament winner
        Team tournamentWinner = tournament.getTournamentWinner();

        if (tournamentWinner != null) {
            System.out.println("Tournament Winner: " + tournamentWinner.getName());
        } else {
            System.out.println("The tournament ended in a draw.");
        }

        // Get the highest scorer in the tournament
        Player highestScorer = tournament.highestScore(games);

        System.out.println("Highest Scorer of the Tournament: " + highestScorer.getName());
    }
}