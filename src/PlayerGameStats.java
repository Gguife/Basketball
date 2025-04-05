import java.util.ArrayList;
import java.util.List;

public class PlayerGameStats {
   public Game game;
   public Player player;
   public int fieldGoals;
   public int threePoints;
   public int freeThrows;
   public int offensiveRebounds;
   public int defensiveRebounds;
   public int assists;


   public PlayerGameStats(
           Game game,
           Player player,
           int fieldGoals,
           int threePoints,
           int freeThrows,
           int offensiveRebounds,
           int defensiveRebounds,
           int assists
   ) {
      this.game = game;
      this.player = player;
      this.fieldGoals = fieldGoals;
      this.threePoints = threePoints;
      this.freeThrows = freeThrows;
      this.offensiveRebounds = offensiveRebounds;
      this.defensiveRebounds = defensiveRebounds;
      this.assists = assists;
   }

   public Player getPlayer() {
      return player;
   }

   public int getOffensiveRebounds() {
      return offensiveRebounds;
   }
   public int getDefensiveRebounds() {
      return defensiveRebounds;
   }
   public int getAssists() {
      return assists;
   }

   public int getPlayerGameScore() {
      return fieldGoals * 2 + threePoints + freeThrows;
   }

   public static List<PlayerGameStats> populatePlayerGameStats(Game game, List<Player> players, int[][] playerStats) {
      List<PlayerGameStats> playerGameStatsList = new ArrayList<>();

      for (int i = 0; i < players.size(); i++) {
         Player player = players.get(i);
         int[] stats = playerStats[i];
         PlayerGameStats playerGameStats = new PlayerGameStats(
                 game,
                 player,
                 stats[0], // Points
                 stats[1], // Assists
                 stats[2], // Rebounds
                 stats[3], // Steals
                 stats[4], // Blocks
                 stats[5] // Turnovers
         );
         playerGameStatsList.add(playerGameStats);
      }

      return playerGameStatsList;
   }

}
