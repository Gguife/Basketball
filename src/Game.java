import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Game {
   public int gameNumber;
   public String date;
   public Team homeTeam;
   public Team awayTeam;
   public int attendance;
   public List<PlayerGameStats> playerGameStats;

   int homeTeamScore;
   int awayTeamScore;
   Team winner;

   public Game(int gameNumber, String date, Team homeTeam,
               Team awayTeam, int attendance) {
      this.gameNumber = gameNumber;
      this.date = date;
      this.homeTeam = homeTeam;
      this.awayTeam = awayTeam;
      this.attendance = attendance;
      this.playerGameStats = new ArrayList<>();
   }


   // Getters and setters
   public Team getHomeTeam() {
      return homeTeam;
   }

   public Team getAwayTeam() {
      return awayTeam;
   }

   public int getGameNumber() {
      return gameNumber;
   }

   public void addPlayerGameStats(PlayerGameStats stats) {
      playerGameStats.add(stats);
   }

   public List<PlayerGameStats> getPlayerGameStats() {
      return playerGameStats;
   }


   // Method to populate games
   public static List<Game> populateGame(List<Team> teams, String[][] gameData) {
      List<Game> games = new ArrayList<>();
      for (String[] data : gameData) {
         int gameNumber = Integer.parseInt(data[0]); // Game number
         String date = data[1]; // Game date
         Team homeTeam = teams.get(Integer.parseInt(data[2])); // Get home team
         Team awayTeam = teams.get(Integer.parseInt(data[3])); // Get away team
         int attendance = Integer.parseInt(data[4]); // Attendance
         // Create and add game to list
         Game game = new Game(gameNumber, date, homeTeam, awayTeam, attendance);
         games.add(game);
      }
      return games;
   }

   // Function to get sum of the PlayerGameScore for each player
   // in the game to compute the team score
   public void teamsScore() {
      homeTeamScore = calculateTeamScore(homeTeam);
      awayTeamScore = calculateTeamScore(awayTeam);
      if (homeTeamScore > awayTeamScore) {
         System.out.println("Team " + homeTeam.getName() + " score: " + homeTeamScore);
         System.out.println("Team " + awayTeam.getName() + " score: " + awayTeamScore);
      } else {
         System.out.println("Team " + awayTeam.getName() + " score: " + awayTeamScore);
         System.out.println("Team " + homeTeam.getName() + " score: " + homeTeamScore);
      }
   }

   // Helper function to get game score
   public int calculateTeamScore(Team team) {
      int teamScore = 0;
      for (PlayerGameStats stats : playerGameStats) {
         Player player = stats.getPlayer();
         if (team.hasPlayer(player)) {
            teamScore += stats.getPlayerGameScore();
         }
      }
      return teamScore;
   }

   // Function to get game summary
   public String gameSummary() {
      teamsScore();
      if (homeTeamScore > awayTeamScore) {
         winner = homeTeam;
         return homeTeam.getName() + " wins against " +
                 awayTeam.getName() + " with a score of " +
                 homeTeamScore + " - " + awayTeamScore;
      } else if (homeTeamScore < awayTeamScore) {
         winner = awayTeam;
         return awayTeam.getName() + " wins against " +
                 homeTeam.getName() + " with a score of " +
                 awayTeamScore + " - " + homeTeamScore;
      } else {
         winner = null;
         return "The game between " + homeTeam.getName() +
                 " and " + awayTeam.getName() +
                 " ended in a draw with a score of " +
                 homeTeamScore + " - " + awayTeamScore;
      }
   }

   // Function to get highest team scorer
   public Player highestTeamScorer(Team team) {
      Player highestScorer = null;
      int highestScore = Integer.MIN_VALUE;
      for (PlayerGameStats stats : playerGameStats) {
         Player player = stats.getPlayer();
         if (team.hasPlayer(player)) {
            int playerScore = stats.getPlayerGameScore();
            if (playerScore > highestScore) {
               highestScore = playerScore;
               highestScorer = player;
            }
         }
      }
      return highestScorer;
   }

   // Helper function to get highest scrorers in a game
   public Map<Team, Player> highestScorer() {
      Map<Team, Player> highestScorers = new HashMap<>();

      Player homeTeamScorer = highestTeamScorer(homeTeam);
      Player awayTeamScorer = highestTeamScorer(awayTeam);

      highestScorers.put(homeTeam, homeTeamScorer);
      highestScorers.put(awayTeam, awayTeamScorer);

      return highestScorers;
   }


   //Helper method to get winning team
   public Team getWinningTeam() {
      if(homeTeamScore > awayTeamScore) {
         return homeTeam;
      } else if (awayTeamScore > homeTeamScore) {
         return awayTeam;
      }

      return null; //Draw
   }
}