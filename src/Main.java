import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        List<Player> players = createPlayer();
        printPlayers(players);
    }

    public static List<Player> createPlayer() {
        String[][] playerData = {
            {"Allen Iverson", "1"},
            {"Aaron McKie", "2"},
            {"Dikembe Mutombo", "3"},
            {"Tyrone Hill", "4"},
            {"Jumaine Jones", "5"},
            {"Kobe Bryant", "6"},
            {"Shaquille O'Neal", "7"},
            {"Rick Fox", "8"},
            {"Horace Grant", "9"},
            {"Derek Fisher", "10"}
        };

        //Call the populatePlayers method
        players = Player.populatePlayer(playerData);

        return players;
    }

    public static void printPlayers(List<Player> players) {
        for(Player player : players) {
            player.printPlayer();
        }
    }
}