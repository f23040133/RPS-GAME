import java.awt.event.KeyEvent;
import java.util.*;

public class Player {
    private Scanner scanner;
    private Move selectedMove;
    private boolean hasMadeMove;
    private boolean hasCheated;
    private int moveCount; // For cheat detection
    private String name;
    private int playerNumber; // 1 for Player 1, 2 for Player 2
    
    // Key mappings
    private int rockKey;
    private int paperKey;
    private int scissorsKey;
    private int lizardKey;
    private int spockKey;
    
    public Player() {
        this("Player 1", 1);
    }
    
    public Player(String name, int playerNumber) {
        this.scanner = new Scanner(System.in);
        this.selectedMove = null;
        this.hasMadeMove = false;
        this.hasCheated = false;
        this.moveCount = 0;
        this.name = name;
        this.playerNumber = playerNumber;
        
        // Set default key bindings based on player number
        if (playerNumber == 1) {
            // Player 1 uses A, S, D, W, E
            rockKey = KeyEvent.VK_A;
            paperKey = KeyEvent.VK_S;
            scissorsKey = KeyEvent.VK_D;
            lizardKey = KeyEvent.VK_W;
            spockKey = KeyEvent.VK_E;
        } else {
            // Player 2 uses J, K, L, I, O
            rockKey = KeyEvent.VK_J;
            paperKey = KeyEvent.VK_K;
            scissorsKey = KeyEvent.VK_L;
            lizardKey = KeyEvent.VK_I;
            spockKey = KeyEvent.VK_O;
        }
    }
    
    public void processKeyPress(int keyCode) {
        // Check if player is trying to make a move after already making one
        if (hasMadeMove) {
            // Only mark as cheating if they try to make another move
            if (keyCode == rockKey || keyCode == paperKey || keyCode == scissorsKey || 
                keyCode == lizardKey || keyCode == spockKey) {
                hasCheated = true;
            }
            return;
        }

        // Process the first move
        if (keyCode == rockKey) {
            selectedMove = Move.ROCK;
            hasMadeMove = true;
        } else if (keyCode == paperKey) {
            selectedMove = Move.PAPER;
            hasMadeMove = true;
        } else if (keyCode == scissorsKey) {
            selectedMove = Move.SCISSORS;
            hasMadeMove = true;
        } else if (keyCode == lizardKey) {
            selectedMove = Move.LIZARD;
            hasMadeMove = true;
        } else if (keyCode == spockKey) {
            selectedMove = Move.SPOCK;
            hasMadeMove = true;
        }
        
        // Increment move count for cheat detection
        moveCount++;
    }
    
    public Move getSelectedMove() {
        return selectedMove;
    }
    
    public boolean hasMadeMove() {
        return hasMadeMove;
    }
    
    public boolean hasCheated() {
        return hasCheated;
    }
    
    public void setCheated(boolean cheated) {
        this.hasCheated = cheated;
    }
    
    public void reset() {
        selectedMove = null;
        hasMadeMove = false;
        hasCheated = false;
        moveCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Manually set a move for this player.
     * 
     * @param move The move to set
     */
    public void setSelectedMove(Move move) {
        if (!hasMadeMove) {
            this.selectedMove = move;
            this.hasMadeMove = true;
        } else {
            // Attempting to make a second move is considered cheating
            this.hasCheated = true;
        }
    }
    
    // Legacy method for console-based gameplay
    public Move getMove() {
        while (true) {
            System.out.println("\nEnter your move:");
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");
            System.out.println("4. Lizard (Extended Mode)");
            System.out.println("5. Spock (Extended Mode)");
              int choice = scanner.nextInt();
            
            return switch (choice) {
                case 1 -> Move.ROCK;
                case 2 -> Move.PAPER;
                case 3 -> Move.SCISSORS;
                case 4 -> Move.LIZARD;
                case 5 -> Move.SPOCK;
                default -> {
                    System.out.println("Invalid move. Please try again.");
                    // Continue the loop by recursively calling getMove()
                    yield getMove();
                }
            };
        }
    }
    
    public int getRockKey() {
        return rockKey;
    }
    
    public int getPaperKey() {
        return paperKey;
    }
    
    public int getScissorsKey() {
        return scissorsKey;
    }
    
    public int getLizardKey() {
        return lizardKey;
    }
    
    public int getSpockKey() {
        return spockKey;
    }
    
    public void setRockKey(int key) {
        this.rockKey = key;
    }
    
    public void setPaperKey(int key) {
        this.paperKey = key;
    }
    
    public void setScissorsKey(int key) {
        this.scissorsKey = key;
    }
    
    public void setLizardKey(int key) {
        this.lizardKey = key;
    }
    
    public void setSpockKey(int key) {
        this.spockKey = key;
    }
}