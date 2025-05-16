import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * A singleton class to manage game images for rock, paper, scissors, etc.
 * This version actually generates the images programmatically instead of loading from files.
 */
public class FixedGameImageManager {
    private static FixedGameImageManager instance;
    private final Map<String, ImageIcon> imageCache;
    
    // Standard image sizes
    private static final int DEFAULT_WIDTH = 60;
    private static final int DEFAULT_HEIGHT = 60;
    private static final int LARGE_WIDTH = 120;
    private static final int LARGE_HEIGHT = 120;
    
    // Colors for the icons
    private static final Color ROCK_COLOR = new Color(110, 110, 110);
    private static final Color PAPER_COLOR = new Color(245, 245, 245);
    private static final Color SCISSORS_COLOR = new Color(220, 220, 220);
    private static final Color LIZARD_COLOR = new Color(50, 205, 50);
    private static final Color SPOCK_COLOR = new Color(100, 149, 237);
    private static final Color OUTLINE_COLOR = new Color(30, 30, 30);
    
    private FixedGameImageManager() {
        imageCache = new HashMap<>();
        initializeImages();
    }
    
    public static synchronized FixedGameImageManager getInstance() {
        if (instance == null) {
            instance = new FixedGameImageManager();
        }
        return instance;
    }
    
    private void initializeImages() {
        // Create standard move images
        imageCache.put("rock", createRockIcon(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        imageCache.put("paper", createPaperIcon(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        imageCache.put("scissors", createScissorsIcon(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        imageCache.put("lizard", createLizardIcon(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        imageCache.put("spock", createSpockIcon(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        
        // Create large versions
        imageCache.put("rock_large", createRockIcon(LARGE_WIDTH, LARGE_HEIGHT));
        imageCache.put("paper_large", createPaperIcon(LARGE_WIDTH, LARGE_HEIGHT));
        imageCache.put("scissors_large", createScissorsIcon(LARGE_WIDTH, LARGE_HEIGHT));
        imageCache.put("lizard_large", createLizardIcon(LARGE_WIDTH, LARGE_HEIGHT));
        imageCache.put("spock_large", createSpockIcon(LARGE_WIDTH, LARGE_HEIGHT));
    }
    
    private ImageIcon createRockIcon(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        setupGraphics(g2d);
        
        // Draw a circle for rock
        Ellipse2D.Double rock = new Ellipse2D.Double(width * 0.1, height * 0.1, width * 0.8, height * 0.8);
        g2d.setColor(ROCK_COLOR);
        g2d.fill(rock);
        
        // Add some texture/detail
        g2d.setColor(ROCK_COLOR.darker());
        g2d.drawOval((int)(width * 0.3), (int)(height * 0.3), (int)(width * 0.2), (int)(height * 0.2));
        g2d.drawOval((int)(width * 0.6), (int)(height * 0.5), (int)(width * 0.15), (int)(height * 0.15));
        
        // Outline
        g2d.setColor(OUTLINE_COLOR);
        g2d.setStroke(new BasicStroke(2f));
        g2d.draw(rock);
        
        g2d.dispose();
        return new ImageIcon(bi);
    }
    
    private ImageIcon createPaperIcon(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        setupGraphics(g2d);
        
        // Draw a rectangle for paper
        Rectangle2D.Double paper = new Rectangle2D.Double(width * 0.15, height * 0.15, width * 0.7, height * 0.7);
        g2d.setColor(PAPER_COLOR);
        g2d.fill(paper);
        
        // Add some lines for text
        g2d.setColor(Color.GRAY);
        g2d.drawLine((int)(width * 0.25), (int)(height * 0.3), (int)(width * 0.75), (int)(height * 0.3));
        g2d.drawLine((int)(width * 0.25), (int)(height * 0.5), (int)(width * 0.75), (int)(height * 0.5));
        g2d.drawLine((int)(width * 0.25), (int)(height * 0.7), (int)(width * 0.75), (int)(height * 0.7));
        
        // Outline
        g2d.setColor(OUTLINE_COLOR);
        g2d.setStroke(new BasicStroke(2f));
        g2d.draw(paper);
        
        g2d.dispose();
        return new ImageIcon(bi);
    }
    
    private ImageIcon createScissorsIcon(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        setupGraphics(g2d);
        
        // Draw scissors with two ovals for handles and blades
        Ellipse2D.Double handle1 = new Ellipse2D.Double(width * 0.2, height * 0.6, width * 0.25, height * 0.3);
        Ellipse2D.Double handle2 = new Ellipse2D.Double(width * 0.55, height * 0.6, width * 0.25, height * 0.3);
        
        // Draw blades
        Path2D.Double blade1 = new Path2D.Double();
        blade1.moveTo(width * 0.3, height * 0.65);
        blade1.lineTo(width * 0.5, height * 0.2);
        
        Path2D.Double blade2 = new Path2D.Double();
        blade2.moveTo(width * 0.7, height * 0.65);
        blade2.lineTo(width * 0.5, height * 0.2);
        
        // Fill handles
        g2d.setColor(SCISSORS_COLOR);
        g2d.fill(handle1);
        g2d.fill(handle2);
        
        // Draw blades
        g2d.setColor(OUTLINE_COLOR);
        g2d.setStroke(new BasicStroke(4f));
        g2d.draw(blade1);
        g2d.draw(blade2);
        
        // Outline handles
        g2d.setStroke(new BasicStroke(2f));
        g2d.draw(handle1);
        g2d.draw(handle2);
        
        g2d.dispose();
        return new ImageIcon(bi);
    }
    
    private ImageIcon createLizardIcon(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        setupGraphics(g2d);
        
        // Create a lizard shape
        Path2D.Double lizard = new Path2D.Double();
        
        // Head
        lizard.moveTo(width * 0.7, height * 0.3);
        lizard.lineTo(width * 0.85, height * 0.2);
        lizard.lineTo(width * 0.85, height * 0.4);
        lizard.lineTo(width * 0.7, height * 0.3);
        
        // Body
        lizard.moveTo(width * 0.7, height * 0.3);
        lizard.lineTo(width * 0.3, height * 0.5);
        
        // Tail
        lizard.lineTo(width * 0.15, height * 0.7);
        
        // Legs
        lizard.moveTo(width * 0.5, height * 0.4);
        lizard.lineTo(width * 0.4, height * 0.3);
        
        lizard.moveTo(width * 0.5, height * 0.4);
        lizard.lineTo(width * 0.6, height * 0.3);
        
        lizard.moveTo(width * 0.4, height * 0.5);
        lizard.lineTo(width * 0.3, height * 0.7);
        
        lizard.moveTo(width * 0.4, height * 0.5);
        lizard.lineTo(width * 0.5, height * 0.7);
        
        // Draw lizard
        g2d.setColor(LIZARD_COLOR);
        g2d.setStroke(new BasicStroke(4f));
        g2d.draw(lizard);
        
        // Draw eye
        g2d.fillOval((int)(width * 0.75), (int)(height * 0.25), (int)(width * 0.05), (int)(height * 0.05));
        
        g2d.dispose();
        return new ImageIcon(bi);
    }
    
    private ImageIcon createSpockIcon(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        setupGraphics(g2d);
        
        // Create a Spock hand shape (Vulcan salute)
        Path2D.Double hand = new Path2D.Double();
        
        // Palm
        hand.moveTo(width * 0.3, height * 0.8);
        hand.lineTo(width * 0.7, height * 0.8);
        hand.lineTo(width * 0.7, height * 0.6);
        hand.lineTo(width * 0.3, height * 0.6);
        hand.closePath();
        
        // Fingers
        // Thumb
        Path2D.Double thumb = new Path2D.Double();
        thumb.moveTo(width * 0.3, height * 0.6);
        thumb.lineTo(width * 0.2, height * 0.4);
        thumb.lineTo(width * 0.25, height * 0.35);
        thumb.lineTo(width * 0.35, height * 0.55);
        
        // Index and middle fingers (separated)
        Path2D.Double indexFinger = new Path2D.Double();
        indexFinger.moveTo(width * 0.4, height * 0.6);
        indexFinger.lineTo(width * 0.4, height * 0.2);
        indexFinger.lineTo(width * 0.45, height * 0.2);
        indexFinger.lineTo(width * 0.45, height * 0.6);
        
        Path2D.Double middleFinger = new Path2D.Double();
        middleFinger.moveTo(width * 0.55, height * 0.6);
        middleFinger.lineTo(width * 0.55, height * 0.2);
        middleFinger.lineTo(width * 0.6, height * 0.2);
        middleFinger.lineTo(width * 0.6, height * 0.6);
        
        // Ring and pinky fingers (separated)
        Path2D.Double ringFinger = new Path2D.Double();
        ringFinger.moveTo(width * 0.65, height * 0.6);
        ringFinger.lineTo(width * 0.65, height * 0.3);
        ringFinger.lineTo(width * 0.7, height * 0.3);
        ringFinger.lineTo(width * 0.7, height * 0.6);
        
        // Fill hand
        g2d.setColor(SPOCK_COLOR);
        g2d.fill(hand);
        g2d.fill(thumb);
        g2d.fill(indexFinger);
        g2d.fill(middleFinger);
        g2d.fill(ringFinger);
        
        // Outline
        g2d.setColor(OUTLINE_COLOR);
        g2d.setStroke(new BasicStroke(2f));
        g2d.draw(hand);
        g2d.draw(thumb);
        g2d.draw(indexFinger);
        g2d.draw(middleFinger);
        g2d.draw(ringFinger);
        
        g2d.dispose();
        return new ImageIcon(bi);
    }
    
    private void setupGraphics(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
    
    public ImageIcon getImage(String imageName) {
        return imageCache.getOrDefault(imageName, createTextIcon(imageName, DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }
    
    private ImageIcon createTextIcon(String text, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        setupGraphics(g2d);
        
        // Background
        g2d.setColor(new Color(200, 200, 255));
        g2d.fillRect(0, 0, width, height);
        
        // Text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g2d.getFontMetrics();
        String displayText = text.toUpperCase();
        int textWidth = fm.stringWidth(displayText);
        g2d.drawString(displayText, (width - textWidth) / 2, height / 2 + fm.getAscent() / 2);
        
        g2d.dispose();
        return new ImageIcon(bi);
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