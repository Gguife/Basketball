import java.util.ArrayList;
import java.util.List;

public class Team {
   private String name;
   private String conference;
   private List<Player> players;

   public Team(String name, String conference){
      this.name = name;
      this.conference = conference;
      this.players = new ArrayList<>();
   }

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

   public static List<Team> populateTeams(String[][] teamData) {
      List<Team> teams = new ArrayList<>();
      for(String[] data: teamData) {
         String name = data[0];
         String conference = data[1];
         Team team = new Team(name, conference);
         teams.add(team);
      }

      return teams;
   }

   public void printTeam() {
      System.out.println("Team name: " + getName() + ", Conference: " + getConference());
   }

   public static void printTeamData(Team team) {
      System.out.println(" " +  team.getName() + "\n--Players:");
      for (Player player : team.getPlayers()) {
         System.out.println(" --- " + player.getName());
      }
   }
}
