public class Player {
    private String playerName;
    private int goalsScored;
    private int goalsConceded;

    public Player(String playerName, int goalsScored, int goalsConceded) {
        this.playerName = playerName;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
    }
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }


    public String getPlayerName() {
        return playerName;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalConceded() {
        return goalsConceded;
    }
}