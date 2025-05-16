public class ScoreBoard {
    private int playerWins;
    private int computerWins;
    private int draws;
    
    public ScoreBoard() {
        this.playerWins = 0;
        this.computerWins = 0;
        this.draws = 0;
    }
      public void updateScore(GameResult result) {
        switch (result) {
            case WIN -> playerWins++;
            case LOSE -> computerWins++;
            case DRAW -> draws++;
        }
    }
    
    public int getPlayerWins() {
        return playerWins;
    }
    
    public int getComputerWins() {
        return computerWins;
    }
    
    public int getDraws() {
        return draws;
    }
    
    public void displayScores() {
        System.out.println("\n=== SCOREBOARD ===");
        System.out.println("Player wins: " + playerWins);
        System.out.println("Computer wins: " + computerWins);
        System.out.println("Draws: " + draws);
        System.out.println("Total games: " + (playerWins + computerWins + draws));
        
        if (playerWins + computerWins > 0) {
            double winRate = (double) playerWins / (playerWins + computerWins) * 100;
            System.out.printf("Win rate: %.2f%%\n", winRate);
        }
    }
}