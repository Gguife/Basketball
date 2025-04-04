import java.util.ArrayList;
import java.util.List;

public class Game {
   private int gameNumber;
   private String date;
   private Team homeTeam;
   private Team awayTeam;
   private int attendance;


   public Game(int gameNumber, String date, Team homeTeam, Team awayTeam, int attendance) {
      this.gameNumber = gameNumber;
      this.date = date;
      this.homeTeam = homeTeam;
      this.awayTeam = awayTeam;
      this.attendance = attendance;
   }

   public Team getHomeTeam() {
      return homeTeam;
   }
   public Team getAwayTeam() {
      return awayTeam;
   }


   //Populate Game
   public static List<Game> populateGames(List<Team> teams, String[][] gameData) {
      List<Game> games = new ArrayList<>();
      for(String[] data: gameData) {
         int gameNumber = Integer.parseInt(data[0]);
         String date = data[1];
         Team homeTeam = teams.get(Integer.parseInt(data[2]));
         Team awayTeam = teams.get(Integer.parseInt(data[3]));
         int attendance = Integer.parseInt(data[4]);

         Game game = new Game(gameNumber, date, homeTeam, awayTeam, attendance);
         games.add(game);
      }

      return games;
   }

   //Print Game Details
   public void printGameDetails() {
      System.out.println("Game #" + gameNumber);
      System.out.println("Date: " + date);
      System.out.println("Attendance: " + attendance);

      System.out.print("Home team:");
      Team.printTeamData(homeTeam);

      System.out.println("Away team:");
      Team.printTeamData(awayTeam);
   }
}
