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


    public static List<Player> populatePlayer(String[][] playerData) {
        List<Player> players = new ArrayList<>();
        for(String[] data : playerData) {
            String name = data[0];
            int number = Integer.parseInt(data[1]);
            Player player = new Player(name, number);
            players.add(player);
        }

        return players;
    }

    public void printPlayer() {
        System.out.println("Player name: " + this.getName() + ", Player number: " + this.getNumber());
    }

}
