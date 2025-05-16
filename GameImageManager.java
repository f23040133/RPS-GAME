import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A singleton class to manage game images for rock, paper, scissors, etc.
 */
public class GameImageManager {
    private static GameImageManager instance;
    private Map<String, ImageIcon> imageCache;
    
    // Standard image sizes
    private static final int DEFAULT_WIDTH = 60;
    private static final int DEFAULT_HEIGHT = 60;
    private static final int LARGE_WIDTH = 120;
    private static final int LARGE_HEIGHT = 120;
    
    private GameImageManager() {
        imageCache = new HashMap<>();
        initializeImages();
    }
    
    public static synchronized GameImageManager getInstance() {
        if (instance == null) {
            instance = new GameImageManager();
        }
        return instance;
    }
    
    private void initializeImages() {
        // Load standard move images
        loadImage("rock", "resource/rock.png");
        loadImage("paper", "resource/paper.png");
        loadImage("scissors", "resource/scissors.png");
        loadImage("lizard", "resource/lizard.png");
        loadImage("spock", "resource/spock.png");
        
        // Load large versions
        loadImage("rock_large", "resource/rock.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadImage("paper_large", "resource/paper.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadImage("scissors_large", "resource/scissors.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadImage("lizard_large", "resource/lizard.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadImage("spock_large", "resource/spock.png", LARGE_WIDTH, LARGE_HEIGHT);
    }
    
    private void loadImage(String imageName, String path) {
        loadImage(imageName, path, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    private void loadImage(String imageName, String path, int width, int height) {
        try {
            // Try loading from resources first
            URL imageUrl = getClass().getResource("/" + path);
            ImageIcon icon;
            
            if (imageUrl != null) {
                icon = new ImageIcon(imageUrl);
            } else {
                // Fall back to file system
                File file = new File(path);
                if (file.exists()) {
                    icon = new ImageIcon(file.getAbsolutePath());
                } else {
                    System.err.println("Image file not found: " + path);
                    // Create a fallback icon with text
                    icon = createFallbackIcon(path, width, height);
                    imageCache.put(imageName, icon);
                    return;
                }
            }
            
            // Scale the image
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            imageCache.put(imageName, scaledIcon);
            
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            // Create a fallback icon with text
            ImageIcon fallbackIcon = createFallbackIcon(path, width, height);
            imageCache.put(imageName, fallbackIcon);
        }
    }
      private ImageIcon createFallbackIcon(String path, int width, int height) {
        // Extract name from path for the fallback text
        String name = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.'));
        
        // Create a panel with a background color to make it visible
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 200, 255));
        panel.setPreferredSize(new Dimension(width, height));
        
        // Add text label
        JLabel label = new JLabel(name.toUpperCase());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(label);
        
        // Create a temporary image from the panel
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        g.dispose();
        
        return new ImageIcon(bi);
    }
    
    public ImageIcon getImage(String imageName) {
        return imageCache.getOrDefault(imageName, new ImageIcon());
    }
    
    public void preloadImages() {
        // This can be called to ensure all images are loaded
        System.out.println("Preloading game images...");
    }
    
    public ImageIcon getRockIcon() {
        return getImage("rock");
    }
    
    public ImageIcon getPaperIcon() {
        return getImage("paper");
    }
    
    public ImageIcon getScissorsIcon() {
        return getImage("scissors");
    }
    
    public ImageIcon getLizardIcon() {
        return getImage("lizard");
    }
    
    public ImageIcon getSpockIcon() {
        return getImage("spock");
    }
    
    public ImageIcon getLargeRockIcon() {
        return getImage("rock_large");
    }
    
    public ImageIcon getLargePaperIcon() {
        return getImage("paper_large");
    }
    
    public ImageIcon getLargeScissorsIcon() {
        return getImage("scissors_large");
    }
    
    public ImageIcon getLargeLizardIcon() {
        return getImage("lizard_large");
    }
    
    public ImageIcon getLargeSpockIcon() {
        return getImage("spock_large");
    }
}