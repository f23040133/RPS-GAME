import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI game = new EnhancedThemeGUI();
            game.setVisible(true);  // This should work as GameGUI extends JFrame
        });
    }
}
