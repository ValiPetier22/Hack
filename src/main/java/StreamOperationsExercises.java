import java.util.*;
import java.util.stream.Collectors;

public class StreamOperationsExercises {
    private static List<Player> players=new ArrayList<>();

    private static void displayPlayers(){
        for (Player player:players) {
            System.out.println(player);
        }
    }

    private static List<Player> displayPlayersForCountry(String country){
        List<Player> playerList=players
                .stream()
                .filter(player -> player.getHighestScore()>100)
                .filter(player -> player.getCountry().getCountryName().equals(country))
                .collect(Collectors.toList());

        return playerList;
    }

    private static LinkedList<String> getPlayerNames(){
        LinkedList<String> playerList=StreamOperationsExercises
                .players
                .stream()
                .filter(player -> player.getRuns()>5000)
                .sorted(Comparator.comparing(Player::getPlayerName).reversed())
                .map(Player::getPlayerName)
                .collect(Collectors.toCollection(LinkedList::new));

        return playerList;
    }

    //getAverageRunsByCountry(String country)
    //Return the average runs scored by players from a particular Country

    private static double getAverageRunsByCountry(String country){
        List<Player> playerList=players.
                stream()
                .filter(player -> player.getCountry().getCountryName().equals(country))
                .collect(Collectors.toList());
        double average=0;
        double S=0;
        for (int i=0;i<playerList.size();i++){
            S+=playerList.get(i).getRuns();
        }
        average=S/ playerList.size();

        return average;
    }

    //getPlayerNamesSorted
    //Return a list with names of Players sorted as per country and then by matchesPlayed(descending)

    private static List<String> getPlayerNamesSorted(){
        List<String> playerList=players
                .stream()
                .sorted(Comparator.comparing(player -> player.getCountry().getCountryName()))
                .sorted(Comparator.comparing(player -> -player.getMatchesPlayed()))
                .map(Player::getPlayerName)
                .collect(Collectors.toList());

        return playerList;

    }

    //getPlayerCountry
    //return a map with the PlayerName and CountryName of all players who have played more than 200 matches

    private static Map<String,String> getPlayerCountry(){
        return players
                .stream()
                .filter(player -> player.getMatchesPlayed()>200)
                .collect(Collectors.groupingBy(player -> player.getCountry().getCountryName(),
                        Collectors.mapping(player -> player,Collectors.toList()));
    }

//    getMaxRunsPlayer
//    Return the player who has scored maximum runs

    private static String getMaxRunsPlayer(){
        List<Player> player=players
                .stream()
                .sorted(Comparator.comparing(player1 -> player1.getRuns()))
                .collect(Collectors.toList());
        return player.get(player.size()-1).getPlayerName();
    }

    //findPlayer(String name, String country)
    //Search and return the player for a given name and country

    private static List<Player> findPlayer(String name, String country){
        List<Player> playerList=players
                .stream()
                .filter(player -> player.getPlayerName().equals(name))
                .filter(player -> player.getCountry().getCountryName().equals(country))
                .collect(Collectors.toList());
        return playerList;
    }

    //checkHighScorerByCountry(String country)
    //Find whether there is any player in the given country who has scored more than 10000 runs. Return a boolean.

    private static boolean checkHighScorerByCountry(String country){
        List<Player> playerList=players
                .stream()
                .filter(player -> player.getRuns()>10000)
                .collect(Collectors.toList());
        if (playerList.isEmpty())
            return false;
        return true;
    }
    public static void main(String[] args) {
        Player player1=new Player("Tavi Popescu",279,11000,30, new Country(2,"Romania"));
        Player player2=new Player("Liviu Guta",5,278,22,new Country(1,"Pakistan"));
        Player player3=new Player("Mirel Radoi",10,258,56,new Country(4,"Poland"));

        players.add(player1);
        players.add(player2);
        players.add(player3);

        //displayPlayers();
        //System.out.println(getPlayerCountry());
        //System.out.println(getMaxRunsPlayer());
        //System.out.println(findPlayer("Tavi Popescu","Romania"));
        System.out.println(checkHighScorerByCountry("Romania"));

    }
}
