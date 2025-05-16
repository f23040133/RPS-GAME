
/**
 * This class contains an enhanced version of the endMatch method
 * that can be copied into the GameGUI class.
 */
public class EnhancedEndMatch {
    
    /**
     * Sample code for an enhanced endMatch method with match statistics.
     * Replace the existing endMatch method in GameGUI with this one.
     */
    public static String getEndMatchSampleCode() {
        return """
        private void endMatch() {
            String winner;
            String player1Name = gameType == GameType.PVP ? player.getName() : "You";
            String player2Name = gameType == GameType.PVP ? player2.getName() : "Computer";
            
            if (scoreBoard.getPlayerWins() > scoreBoard.getComputerWins()) {
                winner = player1Name;
                soundManager.playSound("win");
            } else {
                winner = player2Name;
                soundManager.playSound("lose");
            }
            
            // Create an enhanced match summary with statistics
            int totalRounds = scoreBoard.getPlayerWins() + scoreBoard.getComputerWins() + scoreBoard.getDraws();
            double player1WinPercentage = totalRounds > 0 ? 
                (double) scoreBoard.getPlayerWins() / totalRounds * 100 : 0;
            double player2WinPercentage = totalRounds > 0 ? 
                (double) scoreBoard.getComputerWins() / totalRounds * 100 : 0;
            double drawPercentage = totalRounds > 0 ? 
                (double) scoreBoard.getDraws() / totalRounds * 100 : 0;
            
            StringBuilder statsBreakdown = new StringBuilder();
            if (gameHistory.size() > 0) {
                Map<Move, Integer> player1Moves = new HashMap<>();
                Map<Move, Integer> player2Moves = new HashMap<>();
                
                // Count move usage
                for (int i = 0; i < gameHistory.size(); i++) {
                    Move p1Move = gameHistory.getPlayerMove(i);
                    Move p2Move = gameHistory.getComputerMove(i);
                    
                    if (p1Move != null) {
                        player1Moves.put(p1Move, player1Moves.getOrDefault(p1Move, 0) + 1);
                    }
                    if (p2Move != null) {
                        player2Moves.put(p2Move, player2Moves.getOrDefault(p2Move, 0) + 1);
                    }
                }
                
                // Add move statistics to summary
                statsBreakdown.append("\\n\\nMove Statistics:\\n");
                statsBreakdown.append(player1Name).append("'s moves: ");
                for (Move move : Move.values()) {
                    int count = player1Moves.getOrDefault(move, 0);
                    if (count > 0) {
                        double percentage = (double) count / totalRounds * 100;
                        statsBreakdown.append(move).append(" (").append(String.format("%.1f", percentage)).append("%) ");
                    }
                }
                
                statsBreakdown.append("\\n").append(player2Name).append("'s moves: ");
                for (Move move : Move.values()) {
                    int count = player2Moves.getOrDefault(move, 0);
                    if (count > 0) {
                        double percentage = (double) count / totalRounds * 100;
                        statsBreakdown.append(move).append(" (").append(String.format("%.1f", percentage)).append("%) ");
                    }
                }
            }
            
            // Create the complete message
            String message = String.format(
                "🏆 %s wins the match! 🏆\\n\\n" +
                "Final Score:\\n" +
                "%s: %d (%.1f%%)\\n" +
                "%s: %d (%.1f%%)\\n" +
                "Draws: %d (%.1f%%)\\n\\n" +
                "Total rounds played: %d%s",
                winner,
                player1Name, scoreBoard.getPlayerWins(), player1WinPercentage,
                player2Name, scoreBoard.getComputerWins(), player2WinPercentage,
                scoreBoard.getDraws(), drawPercentage,
                totalRounds,
                statsBreakdown.toString()
            );
            
            // Create a custom dialog with styling
            JDialog resultsDialog = new JDialog(this, "Match Results", true);
            resultsDialog.setSize(450, 400);
            resultsDialog.setLocationRelativeTo(this);
            
            JPanel dialogPanel = new JPanel(new BorderLayout(10, 10));
            dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            dialogPanel.setBackground(backgroundColor);
            
            // Create a styled title
            JLabel titleLabel = new JLabel("Match Results", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(accentColor);
            dialogPanel.add(titleLabel, BorderLayout.NORTH);
            
            // Create the results text area
            JTextArea resultsArea = new JTextArea(message);
            resultsArea.setEditable(false);
            resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            resultsArea.setBackground(backgroundColor);
            resultsArea.setForeground(textColor);
            resultsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JScrollPane scrollPane = new JScrollPane(resultsArea);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            dialogPanel.add(scrollPane, BorderLayout.CENTER);
            
            // Add New Game button
            JButton newGameButton = new JButton("New Game");
            newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
            newGameButton.setBackground(accentColor);
            newGameButton.setForeground(backgroundColor);
            newGameButton.addActionListener(e -> {
                resultsDialog.dispose();
                resetGame();
            });
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false);
            buttonPanel.add(newGameButton);
            dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            resultsDialog.add(dialogPanel);
            resultsDialog.setVisible(true);
        }
        """;
    }
}
