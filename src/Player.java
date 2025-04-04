import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int number;

    Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }


    public static List<Player> populatePlayer(String[][] playerData, int teamCounter) {
        List<Player> players = new ArrayList<>();
        for(String[] data : playerData) {
            int teamId = Integer.parseInt(data[0]);
            if(teamCounter == teamId) {
                Player player = new Player(data[1], Integer.parseInt(data[2]));
                players.add(player);
            }
        }

        return players;
    }

    public void printPlayer() {
        System.out.println("Player name: " + this.getName() + ", Player number: " + this.getNumber());
    }

}
