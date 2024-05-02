import java.util.ArrayList;

public class Team {
    public String teamName;
    public int goalsScored;
    public int goalsConceded;
    ArrayList<Player> players;
    public Team(String teamName){
        this.teamName=teamName;
        this.players=new ArrayList<>();
        this.goalsScored=0;
        this.goalsConceded=0;
    }

    public void addPlayer(Player player){
        players.add(player);
        goalsScored +=player.getGoalsScored();
        goalsConceded +=player.getGoalConceded();
    }
    String getTeamName(){
        return teamName;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
}

