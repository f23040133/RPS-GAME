import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * A singleton class to manage game images for rock, paper, scissors, lizard, and spock.
 * This version loads the images from files instead of generating them programmatically.
 */
public class EnhancedGameImageManager {
    private static EnhancedGameImageManager instance;
    private final Map<String, ImageIcon> imageCache;
    
    // Standard image sizes
    private static final int DEFAULT_WIDTH = 60;
    private static final int DEFAULT_HEIGHT = 60;
    private static final int LARGE_WIDTH = 120;
    private static final int LARGE_HEIGHT = 120;
    // Path to resource folder
    private static final String RESOURCE_PATH = "resource/";
    private static final String ABSOLUTE_RESOURCE_PATH = System.getProperty("user.dir") + File.separator + "resource" + File.separator;
    
    private EnhancedGameImageManager() {
        imageCache = new HashMap<>();
        initializeImages();
    }
    
    public static synchronized EnhancedGameImageManager getInstance() {
        if (instance == null) {
            instance = new EnhancedGameImageManager();
        }
        return instance;
    }
    
    private void initializeImages() {
        // Load and cache the standard sized images
        loadAndCacheImage("rock", RESOURCE_PATH + "rock.png", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loadAndCacheImage("paper", RESOURCE_PATH + "paper.png", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loadAndCacheImage("scissors", RESOURCE_PATH + "scissors.png", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loadAndCacheImage("lizard", RESOURCE_PATH + "lizard.png", DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loadAndCacheImage("spock", RESOURCE_PATH + "spoke.png", DEFAULT_WIDTH, DEFAULT_HEIGHT); // Note: filename is "spoke.png" (typo in original file)
        
        // Load and cache the large sized images
        loadAndCacheImage("rock_large", RESOURCE_PATH + "rock.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadAndCacheImage("paper_large", RESOURCE_PATH + "paper.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadAndCacheImage("scissors_large", RESOURCE_PATH + "scissors.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadAndCacheImage("lizard_large", RESOURCE_PATH + "lizard.png", LARGE_WIDTH, LARGE_HEIGHT);
        loadAndCacheImage("spock_large", RESOURCE_PATH + "spoke.png", LARGE_WIDTH, LARGE_HEIGHT); // Note: filename is "spoke.png" (typo in original file)
    }
    
    private void loadAndCacheImage(String key, String imagePath, int width, int height) {
        // Try multiple possible paths
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            // Try absolute path
            imageFile = new File(ABSOLUTE_RESOURCE_PATH + new File(imagePath).getName());
            System.out.println("Trying absolute path: " + imageFile.getAbsolutePath());
        }
        
        try {
            if (imageFile.exists()) {
                BufferedImage originalImage = ImageIO.read(imageFile);
                Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                
                // Convert resized image to BufferedImage for better quality
                BufferedImage bufferedResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedResized.createGraphics();
                setupGraphics(g2d);
                g2d.drawImage(resizedImage, 0, 0, null);
                g2d.dispose();
                
                imageCache.put(key, new ImageIcon(bufferedResized));
                System.out.println("Successfully loaded image: " + imageFile.getAbsolutePath() + " for key: " + key);
            } else {
                System.err.println("Image file not found: " + imagePath);
                System.err.println("Absolute path check: " + imageFile.getAbsolutePath() + " does not exist");
                
                // Fallback to generated icon if file not found
                imageCache.put(key, FixedGameImageManager.getInstance().getImage(key));
                System.out.println("Using fallback image for key: " + key);
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + imagePath + " - " + e.getMessage());
            // Log the stack trace details without using printStackTrace()
            for (StackTraceElement element : e.getStackTrace()) {
                System.err.println("    at " + element);
            }
            // Fallback to generated icon if error occurs
            imageCache.put(key, FixedGameImageManager.getInstance().getImage(key));
            System.out.println("Using fallback image for key: " + key + " due to error: " + e.getMessage());
        }
    }
    
    private void setupGraphics(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }
    
    public ImageIcon getImage(String imageName) {
        ImageIcon icon = imageCache.get(imageName);
        if (icon == null) {
            // If image not found in cache, return a fallback from FixedGameImageManager
            return FixedGameImageManager.getInstance().getImage(imageName);
        }
        return icon;
    }
    
    // Convenience methods
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
