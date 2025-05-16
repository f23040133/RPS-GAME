import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameHistory {
    private static final class GameRecord {
        private final Date timestamp;
        private final Move playerMove;
        private final Move computerMove;
        private final GameResult result;
        private final GameMode mode;
        
        public GameRecord(Move playerMove, Move computerMove, GameResult result, GameMode mode) {
            this.timestamp = new Date();
            this.playerMove = playerMove;
            this.computerMove = computerMove;
            this.result = result;
            this.mode = mode;
        }
        
        public Date getTimestamp() {
            return timestamp;
        }
        
        public Move getPlayerMove() {
            return playerMove;
        }
        
        public Move getComputerMove() {
            return computerMove;
        }
        
        public GameResult getResult() {
            return result;
        }
        
        public GameMode getMode() {
            return mode;
        }
        
        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
            return String.format("%s - %s: Player(%s) vs Computer(%s) - %s",
                    dateFormat.format(timestamp),
                    mode == GameMode.CLASSIC ? "Classic" : "Extended",
                    playerMove,
                    computerMove,
                    result.getMessage());
        }
    }
    
    private final List<GameRecord> history;
    
    public GameHistory() {
        this.history = new ArrayList<>();
    }
    
    public void addRecord(Move playerMove, Move computerMove, GameResult result, GameMode mode) {
        history.add(new GameRecord(playerMove, computerMove, result, mode));
    }
    
    public String getFormattedHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><style>");
        sb.append("body { width: 400px; background-color: #28A028; color: white; font-family: \"Segoe UI\", Arial, sans-serif; font-size: 12px; }");
        sb.append("h3 { color: white; font-family: \"Segoe UI\", Arial, sans-serif; font-size: 14px; }");
        sb.append("table { width: 100%; border-collapse: collapse; border: 1px solid #AAFFAA; border-radius: 4px; box-shadow: 0 0 5px rgba(0,0,0,0.2); }");
        sb.append("th, td { border: 1px solid #AAFFAA; }");
        sb.append("</style></head><body>");
        sb.append("<h3>Game History</h3>");
        sb.append("<table>");
        sb.append("<tr style='background-color: #1A701A;'>");
        sb.append("<th style='padding: 4px; color: white; font-size: 12px;'>Time</th>");
        sb.append("<th style='padding: 4px; color: white; font-size: 12px;'>Mode</th>");
        sb.append("<th style='padding: 4px; color: white; font-size: 12px;'>Player</th>");
        sb.append("<th style='padding: 4px; color: white; font-size: 12px;'>Computer</th>");
        sb.append("<th style='padding: 4px; color: white; font-size: 12px;'>Result</th>");
        sb.append("</tr>");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        boolean alternate = false;
        
        for (int i = history.size() - 1; i >= 0; i--) {
            GameRecord record = history.get(i);            
            alternate = !alternate;
            String rowColor = alternate ? "#1E8A1E" : "#28A028"; // Different shades of green for alternating rows
            
            sb.append("<tr style='background-color: ").append(rowColor).append("'>");
            sb.append("<td style='padding: 3px; color: white; font-size: 11px;'>").append(dateFormat.format(record.getTimestamp())).append("</td>");
            sb.append("<td style='padding: 3px; color: white; font-size: 11px;'>").append(record.getMode() == GameMode.CLASSIC ? "Classic" : "Extended").append("</td>");            
            sb.append("<td style='padding: 3px; color: white; font-size: 11px;'>").append(record.getPlayerMove()).append("</td>");
            sb.append("<td style='padding: 3px; color: white; font-size: 11px;'>").append(record.getComputerMove()).append("</td>");
            String resultColor = switch (record.getResult()) {
                case WIN -> "#80FFFF"; // Lighter blue for win (better visibility on green)
                case LOSE -> "#FFA0A0"; // Light red/pink for lose
                case DRAW -> "#FFFF80"; // Light yellow for draw
                default -> "#FFFFFF"; // White for any other case
            };
            
            sb.append("<td style='padding: 3px; color: ").append(resultColor).append("; font-size: 11px; font-weight: bold;'>")
              .append(record.getResult().getMessage()).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</body></html>");
        
        return sb.toString();
    }
    
    public void clearHistory() {
        history.clear();
    }
      public int size() {
        return history.size();
    }
    
    /**
     * This method is used only for internal access
     * Returns a copy of the history list to avoid modification
     * @return Copy of the game history list
     */
    private List<GameRecord> getHistoryInternal() {
        return new ArrayList<>(history);
    }
    
    public Move getPlayerMove(int index) {
        if (index >= 0 && index < history.size()) {
            return history.get(index).getPlayerMove();
        }
        return null;
    }
    
    public Move getComputerMove(int index) {
        if (index >= 0 && index < history.size()) {
            return history.get(index).getComputerMove();
        }
        return null;
    }
    
    public GameResult getResult(int index) {
        if (index >= 0 && index < history.size()) {
            return history.get(index).getResult();
        }
        return null;
    }
    
    public GameMode getMode(int index) {
        if (index >= 0 && index < history.size()) {
            return history.get(index).getMode();
        }
        return null;
    }
}
