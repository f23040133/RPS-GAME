import java.util.*;

public class Game {
    private final Player player;
    private final Computer computer;
    private final ScoreBoard scoreBoard;
    private GameMode currentMode;
    
    public Game() {
        this.player = new Player();
        this.computer = new Computer();
        this.scoreBoard = new ScoreBoard();
        this.currentMode = GameMode.CLASSIC;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Advanced Rock Paper Scissors!");
        
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1: playRound(); break;
                case 2: changeGameMode(); break;
                case 3: displayStats(); break;
                case 4:
                    System.out.println("Thanks for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Play a round");
        System.out.println("2. Change game mode");
        System.out.println("3. View scoreboard");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void playRound() {
        Move playerMove = player.getMove();
        Move computerMove = computer.generateMove();
        
        System.out.println("\nYour move: " + playerMove);
        System.out.println("Computer's move: " + computerMove);
        
        GameResult result = currentMode.determineWinner(playerMove, computerMove);
        System.out.println(result.getMessage());
        
        scoreBoard.updateScore(result);
    }
    
    private void displayStats() {
        System.out.println("\nCurrent Scoreboard:");
        System.out.println("Player Wins: " + scoreBoard.getPlayerWins());
        System.out.println("Computer Wins: " + scoreBoard.getComputerWins());
        System.out.println("Draws: " + scoreBoard.getDraws());
    }

    private void changeGameMode() {
        System.out.println("\nSelect Game Mode:");
        System.out.println("1. Classic");
        System.out.println("2. Extended (Rock, Paper, Scissors, Lizard, Spock)");
        
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        
        currentMode = (mode == 2) ? GameMode.EXTENDED : GameMode.CLASSIC;
        System.out.println("Game mode changed to: " + currentMode);
    }
}
