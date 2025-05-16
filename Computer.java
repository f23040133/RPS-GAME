import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Computer {
    private final Random random;
    private final Map<Move, Integer> playerMoveHistory;
    private final List<Move> previousPlayerMoves;
    private int roundsPlayed;
    
    public Computer() {
        this.random = new Random();
        this.playerMoveHistory = new HashMap<>();
        for (Move move : Move.values()) {
            playerMoveHistory.put(move, 0);
        }
        this.previousPlayerMoves = new ArrayList<>();
        this.roundsPlayed = 0;
    }
    
    public Move generateMove() {
        // First few rounds: use random move
        if (roundsPlayed < 3) {
            return generateRandomMove();
        }
        
        // After a few rounds, try to predict player's move based on history
        return generateSmartMove();
    }
    
    private Move generateRandomMove() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }
      private Move generateSmartMove() {
        // Find the most frequent move
        Move mostFrequentMove = null;
        int maxCount = 0;
        
        for (Map.Entry<Move, Integer> entry : playerMoveHistory.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentMove = entry.getKey();
            }
        }
        
        // Implement more advanced AI strategies
        
        // Strategy 1: Analyze patterns in last 5 moves if available
        if (previousPlayerMoves.size() >= 5) {
            // Get the last 5 moves
            List<Move> last5Moves = previousPlayerMoves.subList(
                previousPlayerMoves.size() - 5, 
                previousPlayerMoves.size()
            );
            
            // Check if player tends to alternate moves (rock-paper-rock-paper, etc.)
            boolean alternating = true;
            for (int i = 0; i < 3; i++) {
                if (last5Moves.get(i) != last5Moves.get(i + 2)) {
                    alternating = false;
                    break;
                }
            }
            
            if (alternating) {
                // If player alternates, predict their next move and counter it
                Move lastMove = last5Moves.get(4);
                Move secondLastMove = last5Moves.get(3);
                
                // Their next move is likely to be the same as their move two rounds ago
                Move predictedMove = last5Moves.get(2);
                return getWinningMoveAgainst(predictedMove);
            }
            
            // Check if player has a tendency to cycle through 3 specific moves
            Map<Move, Integer> moveCounts = new HashMap<>();
            for (Move move : last5Moves) {
                moveCounts.put(move, moveCounts.getOrDefault(move, 0) + 1);
            }
            
            // If they only use 3 or fewer move types in the last 5 rounds
            if (moveCounts.size() <= 3) {
                // Calculate which move they haven't used recently
                List<Move> unusedMoves = new ArrayList<>();
                for (Move move : Move.values()) {
                    if (!moveCounts.containsKey(move)) {
                        unusedMoves.add(move);
                    }
                }
                
                // Player might try a move they haven't used recently
                if (!unusedMoves.isEmpty()) {
                    Move likelyNextMove = unusedMoves.get(random.nextInt(unusedMoves.size()));
                    return getWinningMoveAgainst(likelyNextMove);
                }
            }
        }
        
        // Strategy 2: Check for patterns in last 3 moves
        if (previousPlayerMoves.size() >= 3) {
            Move lastMove = previousPlayerMoves.get(previousPlayerMoves.size() - 1);
            Move secondLastMove = previousPlayerMoves.get(previousPlayerMoves.size() - 2);
            Move thirdLastMove = previousPlayerMoves.get(previousPlayerMoves.size() - 3);
            
            // If player repeated the same move twice
            if (lastMove == secondLastMove) {
                // 70% chance they'll use the same move again, 30% they'll change
                if (random.nextDouble() < 0.7) {
                    return getWinningMoveAgainst(lastMove);
                } else {
                    // Predict they might change to counter what we'd play against their repeated move
                    Move predictedCounterToOurCounter = getWinningMoveAgainst(getWinningMoveAgainst(lastMove));
                    return getWinningMoveAgainst(predictedCounterToOurCounter);
                }
            }
            
            // If player is cycling through moves
            if (lastMove != secondLastMove && secondLastMove != thirdLastMove && thirdLastMove != lastMove) {
                // Try to predict the next in cycle
                Move nextPredicted = null;
                
                // Look for rock-paper-scissors cycle in either direction
                if ((lastMove == Move.ROCK && secondLastMove == Move.SCISSORS && thirdLastMove == Move.PAPER) ||
                    (lastMove == Move.ROCK && secondLastMove == Move.PAPER && thirdLastMove == Move.SCISSORS)) {
                    nextPredicted = Move.SCISSORS; // If player cycling R-S-P or R-P-S, they might play scissors next
                }
                else if ((lastMove == Move.PAPER && secondLastMove == Move.ROCK && thirdLastMove == Move.SCISSORS) ||
                         (lastMove == Move.PAPER && secondLastMove == Move.SCISSORS && thirdLastMove == Move.ROCK)) {
                    nextPredicted = Move.ROCK; // If player cycling P-R-S or P-S-R, they might play rock next
                }
                else if ((lastMove == Move.SCISSORS && secondLastMove == Move.PAPER && thirdLastMove == Move.ROCK) ||
                         (lastMove == Move.SCISSORS && secondLastMove == Move.ROCK && thirdLastMove == Move.PAPER)) {
                    nextPredicted = Move.PAPER; // If player cycling S-P-R or S-R-P, they might play paper next
                }
                
                if (nextPredicted != null) {
                    return getWinningMoveAgainst(nextPredicted);
                }
            }
        }
        
        // Strategy 3: Check what the player played after we played a specific move
        if (previousPlayerMoves.size() >= 3) {
            Move ourPreviousMove = getWinningMoveAgainst(previousPlayerMoves.get(previousPlayerMoves.size() - 2));
            Move playerResponseToOurMove = previousPlayerMoves.get(previousPlayerMoves.size() - 1);
            
            // Player tends to respond to our previous move in a specific way
            // Counter their likely response
            return getWinningMoveAgainst(playerResponseToOurMove);
        }
        
        // Strategy 4: Counter the most frequent move with a higher probability
        if (mostFrequentMove != null) {
            // 80% chance to counter most frequent move, 20% chance to play randomly
            if (random.nextDouble() < 0.8) {
                return getWinningMoveAgainst(mostFrequentMove);
            }
        }
        
        // If no clear pattern or we want to randomize sometimes, use random move
        // This prevents the AI from becoming too predictable
        return generateRandomMove();
    }    private Move getWinningMoveAgainst(Move opponentMove) {
        return switch (opponentMove) {
            case ROCK -> {
                // Rock is beaten by Paper or Spock
                yield random.nextBoolean() ? Move.PAPER : Move.SPOCK;
            }
            case PAPER -> {
                // Paper is beaten by Scissors or Lizard
                yield random.nextBoolean() ? Move.SCISSORS : Move.LIZARD;
            }
            case SCISSORS -> {
                // Scissors is beaten by Rock or Spock
                yield random.nextBoolean() ? Move.ROCK : Move.SPOCK;
            }
            case LIZARD -> {
                // Lizard is beaten by Rock or Scissors
                yield random.nextBoolean() ? Move.ROCK : Move.SCISSORS;
            }
            case SPOCK -> {
                // Spock is beaten by Paper or Lizard
                yield random.nextBoolean() ? Move.PAPER : Move.LIZARD;
            }
            default -> generateRandomMove();
        };
    }
    
    public void recordPlayerMove(Move playerMove) {
        if (playerMove != null) {
            playerMoveHistory.put(playerMove, playerMoveHistory.get(playerMove) + 1);
            previousPlayerMoves.add(playerMove);
            roundsPlayed++;
        }
    }
    
    public void reset() {
        for (Move move : Move.values()) {
            playerMoveHistory.put(move, 0);
        }
        previousPlayerMoves.clear();
        roundsPlayed = 0;
    }
}