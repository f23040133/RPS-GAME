public enum GameResult {
    WIN("You win!"),
    LOSE("Computer wins!"),
    DRAW("It's a draw!"),
    INVALID("Invalid game state!");
    
    private final String defaultMessage;
    
    GameResult(String message) {
        this.defaultMessage = message;
    }
    
    public String getMessage() {
        return defaultMessage;
    }
    
    /**
     * Get a customized message for the game result based on the game type
     * 
     * @param player1Name Name of player 1
     * @param player2Name Name of player 2 or "Computer" for PvC mode
     * @return Customized message for the game result
     */
    public String getCustomMessage(String player1Name, String player2Name) {
        return switch (this) {
            case WIN -> player1Name + " wins!";
            case LOSE -> player2Name + " wins!";
            case DRAW -> "It's a draw!";
            case INVALID -> "Invalid game state!";
        };
    }
}