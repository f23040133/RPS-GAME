import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class EnhancedGameGUI extends GameGUI {
    private transient final EnhancedGameImageManager enhancedImageManager;
    
    /**
     * Constructor that initializes the enhanced image manager
     */
    public EnhancedGameGUI() {
        super(); // Initialize the parent class
        this.enhancedImageManager = EnhancedGameImageManager.getInstance();
        loadEnhancedImages(); // Load images from files
    }
    
    /**
     * Load images from files in the resource folder
     */
    private void loadEnhancedImages() {
        // Override the icons with enhanced images
        setRockIcon(enhancedImageManager.getRockIcon());
        setPaperIcon(enhancedImageManager.getPaperIcon());
        setScissorsIcon(enhancedImageManager.getScissorsIcon());
        setLizardIcon(enhancedImageManager.getLizardIcon());
        setSpockIcon(enhancedImageManager.getSpockIcon());
        
        // Refresh the UI with new images
        refreshButtons();
    }
    
    /**
     * Method to refresh buttons after changing images
     * Since the parent's updateMoveButtons is private, we'll manually trigger UI refresh
     */
    private void refreshButtons() {
        // Force revalidation and repaint
        if (isVisible()) {
            SwingUtilities.invokeLater(() -> {
                revalidate();
                repaint();
            });
        }
    }
    
    /**
     * Enhanced method to display match results with detailed statistics
     */
    protected void endMatch() {
        String winner;
        String player1Name = getGameType() == GameType.PVP ? getPlayer1().getName() : "You";
        String player2Name = getGameType() == GameType.PVP ? getPlayer2().getName() : "Computer";
        
        if (getScoreBoard().getPlayerWins() > getScoreBoard().getComputerWins()) {
            winner = player1Name;
            getSoundManager().playSound("win");
        } else {
            winner = player2Name;
            getSoundManager().playSound("lose");
        }
        
        // Create an enhanced match summary with statistics
        int totalRounds = getScoreBoard().getPlayerWins() + getScoreBoard().getComputerWins() + getScoreBoard().getDraws();
        double player1WinPercentage = totalRounds > 0 ? 
            (double) getScoreBoard().getPlayerWins() / totalRounds * 100 : 0;
        double player2WinPercentage = totalRounds > 0 ? 
            (double) getScoreBoard().getComputerWins() / totalRounds * 100 : 0;
        double drawPercentage = totalRounds > 0 ? 
            (double) getScoreBoard().getDraws() / totalRounds * 100 : 0;
        
        StringBuilder statsBreakdown = new StringBuilder();
        if (getGameHistory().size() > 0) {
            Map<Move, Integer> player1Moves = new HashMap<>();
            Map<Move, Integer> player2Moves = new HashMap<>();
            
            // Count move usage
            for (int i = 0; i < getGameHistory().size(); i++) {
                Move p1Move = getGameHistory().getPlayerMove(i);
                Move p2Move = getGameHistory().getComputerMove(i);
                
                if (p1Move != null) {
                    player1Moves.put(p1Move, player1Moves.getOrDefault(p1Move, 0) + 1);
                }
                if (p2Move != null) {
                    player2Moves.put(p2Move, player2Moves.getOrDefault(p2Move, 0) + 1);
                }
            }
            
            // Add move statistics to summary
            statsBreakdown.append("\n\nMove Statistics:\n");
            statsBreakdown.append(player1Name).append("'s moves: ");
            for (Move move : Move.values()) {
                int count = player1Moves.getOrDefault(move, 0);
                if (count > 0) {
                    double percentage = (double) count / totalRounds * 100;
                    statsBreakdown.append(move).append(" (").append(String.format("%.1f", percentage)).append("%) ");
                }
            }
            
            statsBreakdown.append("\n").append(player2Name).append("'s moves: ");
            for (Move move : Move.values()) {
                int count = player2Moves.getOrDefault(move, 0);
                if (count > 0) {
                    double percentage = (double) count / totalRounds * 100;
                    statsBreakdown.append(move).append(" (").append(String.format("%.1f", percentage)).append("%) ");
                }
            }
        }
        
        // Create the complete message
        String message = String.format("""
            🏆 %s wins the match! 🏆
            
            Final Score:
            %s: %d (%.1f%%)
            %s: %d (%.1f%%)
            Draws: %d (%.1f%%)
            
            Total rounds played: %d%s
            """,
            winner,
            player1Name, getScoreBoard().getPlayerWins(), player1WinPercentage,
            player2Name, getScoreBoard().getComputerWins(), player2WinPercentage,
            getScoreBoard().getDraws(), drawPercentage,
            totalRounds,
            statsBreakdown.toString()
        );
        
        JOptionPane.showMessageDialog(this, message, "Match Results", JOptionPane.INFORMATION_MESSAGE);
        
        // Reset for a new match
        resetGame();
    }
    
    /**
     * Custom implementation of resetGame since the parent method is private
     */
    private void resetGame() {
        // Call parent method through other means
        SwingUtilities.invokeLater(() -> {
            // Force a new game from the GUI
            revalidate();
            repaint();
            
            // Create a new ScoreBoard instance since there's no reset method
            try {
                // Use reflection to access the private scoreBoard field in GameGUI
                java.lang.reflect.Field scoreBoardField = GameGUI.class.getDeclaredField("scoreBoard");
                scoreBoardField.setAccessible(true);
                scoreBoardField.set(this, new ScoreBoard());
                
                getResultLabel().setText("Make your move!");
                getPlayerMoveLabel().setText("Your Move: -");
                getComputerMoveLabel().setText("Computer's Move: -");
                
                // Update score labels
                updateScoreLabels();
            } catch (Exception e) {
                System.err.println("Error resetting scoreboard: " + e.getMessage());
            }
        });
    }
    
    // Helper method to update score labels
    private void updateScoreLabels() {
        String player1Name = getGameType() == GameType.PVP ? getPlayer1().getName() : "You";
        String player2Name = getGameType() == GameType.PVP ? getPlayer2().getName() : "Computer";
        
        // Update the score display
        getPlayerScoreLabel().setText(player1Name + ": " + getScoreBoard().getPlayerWins());
        getComputerScoreLabel().setText(player2Name + ": " + getScoreBoard().getComputerWins());
        getDrawsLabel().setText("Draws: " + getScoreBoard().getDraws());
    }
      /**
     * Additional protected accessor methods for the themed GUI
     */
    public ImageIcon getRockIcon() {
        return super.getRockIcon();
    }
    
    public ImageIcon getPaperIcon() {
        return super.getPaperIcon();
    }
    
    public ImageIcon getScissorsIcon() {
        return super.getScissorsIcon();
    }
    
    public ImageIcon getLizardIcon() {
        return super.getLizardIcon();
    }
    
    public ImageIcon getSpockIcon() {
        return super.getSpockIcon();
    }
    
    protected JLabel getTimeLabel() {
        // Need to access through reflection as there's no getter in GameGUI
        try {
            java.lang.reflect.Field field = GameGUI.class.getDeclaredField("timeLabel");
            field.setAccessible(true);
            return (JLabel) field.get(this);
        } catch (Exception e) {
            System.err.println("Error accessing timeLabel: " + e.getMessage());
            return new JLabel("Time: --:--");
        }
    }
    
    protected JLabel getRoundLabel() {
        // Need to access through reflection as there's no getter in GameGUI
        try {
            java.lang.reflect.Field field = GameGUI.class.getDeclaredField("roundLabel");
            field.setAccessible(true);
            return (JLabel) field.get(this);
        } catch (Exception e) {
            System.err.println("Error accessing roundLabel: " + e.getMessage());
            return new JLabel("Round: 0");
        }
    }
    
    protected void playMoveWithAnimation(Move move) {
        // Need to access through reflection as the method is private in GameGUI
        try {
            java.lang.reflect.Method method = GameGUI.class.getDeclaredMethod("playMoveWithAnimation", Move.class);
            method.setAccessible(true);
            method.invoke(this, move);
        } catch (Exception e) {
            System.err.println("Error calling playMoveWithAnimation: " + e.getMessage());
        }
    }
}
