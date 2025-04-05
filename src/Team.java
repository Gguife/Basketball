import java.util.ArrayList;
import java.util.List;

public class Team {
   public String name;
   public String conference;
   private List<Player> players;

   public Team(String name, String conference) {
      this.name = name;
      this.conference = conference;
      this.players = new ArrayList<>();
   }

   // Getters and setters
   public String getName() {
      return name;
   }

   public String getConference() {
      return conference;
   }

   public List<Player> getPlayers() {
      return players;
   }

   public void addPlayer(Player player) {
      players.add(player);
   }


   // Method to check if a given player is part of the team
   public boolean hasPlayer(Player player) {
      return players.contains(player);  // Returns true if the player is in the team's list
   }

   // Function to populate team
   public static List<Team> populateTeams(String[][] teamData) {
      List<Team> teams = new ArrayList<>();
      for (String[] data : teamData) {
         String name = data[0];
         String conference = data[1];
         Team team = new Team(name, conference);
         teams.add(team);
      }
      return teams;
   }
}