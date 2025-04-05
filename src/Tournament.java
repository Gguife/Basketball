import java.util.*;

public class Tournament {
   public String name;
   public String season;
   public List<Team> teams;
   public static List<Game> games;
   public Player mvp;

   public Tournament(String name, String season) {
      this.name = name;
      this.season = season;
      this.teams = new ArrayList<>();
      this.games = new ArrayList<>();
   }

   public void addTeam(Team team) {
      teams.add(team);
   }
   public List<Team> getTeams() {
      return teams;
   }
   public String getName() {
      return name;
   }
   public void addGame(Game game) {
      games.add(game);
   }
   public static List<Game> getGames() {
      return games;
   }
   public void setMvp(Player player) {
      this.mvp = player;
   }


   //Method to get highest scorer of the tournament
   public Player highestScore(List<Game> gamesList) {
      Map<Player, Integer> playerScores = new HashMap<>();

      //Aggregate scores for all players
      for(Game game1 : gamesList) {
         for(PlayerGameStats stats : game1.getPlayerGameStats()) {
            Player player = stats.getPlayer();
            int playerScore = stats.getPlayerGameScore();

            if(playerScores.containsKey(player)) {
               playerScore += playerScores.get(player);
            }

            playerScores.put(player, playerScore);
         }
      }

      // Find the player with the highest score
      Player hScore = null;
      int highestScore = Integer.MIN_VALUE;
      for(Map.Entry<Player, Integer> entry: playerScores.entrySet()) {
         int value = entry.getValue();
         if(value > highestScore) {
            highestScore = value;
            hScore = entry.getKey();
         }
      }

      setMvp(hScore);
      return hScore;
   }


   //Method to get MVP stats
   public Map<String, String> mvpStats(ArrayList<Game> games) {
      Map<String, String> stats = new LinkedHashMap<>();
      int totalGamesPlayed = 0;
      int totalPointsScored = 0;
      int totalRebounds = 0;
      int totalAssists = 0;
      String playerName = "";

      for(Game game: games) {
         for(PlayerGameStats gameStats: game.getPlayerGameStats()) {
            Player player = gameStats.getPlayer();
            if(player.equals(mvp)) {
               totalGamesPlayed++;
               totalPointsScored += gameStats.getPlayerGameScore();
               totalRebounds += gameStats.getOffensiveRebounds() + gameStats.getDefensiveRebounds();
               totalAssists += gameStats.getAssists();
               playerName = player.getName();
            }
         }
      }

      System.out.println("-------------------------------------------------------");
      stats.put("MVP Player", playerName);
      stats.put("Total Rebounds", String.valueOf(totalRebounds));
      stats.put("Total Assists", String.valueOf(totalAssists));
      stats.put("Total Games Played", String.valueOf(totalGamesPlayed));
      stats.put("Total Points Scored", String.valueOf(totalPointsScored));

      return stats;
   }

   //Method to get tournament winner team
   public Team getTournamentWinner() {
      Map<Team, Integer> teamWins = new HashMap<>();

      //Iterate over games to count wins
      for(Game game: games) {
         Team winningTeam = game.getWinningTeam();
         if(winningTeam !=null) {
            teamWins.put(winningTeam, teamWins.getOrDefault(winningTeam, 0) + 1);
         }
      }

      //Find the time with the most wins
      Team potentialWinner = null;
      int maxWins = 0;
      boolean draw = false;

      for(Map.Entry<Team, Integer> entry : teamWins.entrySet()) {
         if(entry.getValue() > maxWins) {
            maxWins = entry.getValue();
            potentialWinner = entry.getKey();
            draw = false;
         } else if (entry.getValue() == maxWins) {
            draw = true; //if multiple teams have the same maxWins
         }
      }

      if(draw) {
         return null;
      }

      return potentialWinner;
   }

}
