import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Enhanced themed version of the game GUI with a professional dark theme,
 * particle effects, animated gradient backgrounds, and smooth transitions.
 */
public class EnhancedThemeGUI extends EnhancedGameGUI {
    // Modern color scheme
    private static final Color DARK_BG = new Color(20, 22, 33);
    private static final Color PRIMARY_COLOR = new Color(87, 90, 194);
    private static final Color SECONDARY_COLOR = new Color(99, 55, 182);
    private static final Color TEXT_COLOR = new Color(240, 240, 245);
    private static final Color ACCENT_COLOR = new Color(230, 69, 112);
    private static final Color BUTTON_HOVER = new Color(65, 68, 153);
    private static final Color BUTTON_PRESS = new Color(47, 51, 128);
    
    // Neon accent colors for different moves
    private static final Color ROCK_COLOR = new Color(230, 69, 112);
    private static final Color PAPER_COLOR = new Color(87, 187, 221);
    private static final Color SCISSORS_COLOR = new Color(244, 176, 66);
    private static final Color LIZARD_COLOR = new Color(63, 208, 133);
    private static final Color SPOCK_COLOR = new Color(170, 90, 255);
    
    // Fonts
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
      // UI Enhancement Elements
    private Timer pulseAnimationTimer;
    private int pulseStage = 0;
    private ParticleSystem particleSystem;
    private AnimatedGradientPanel backgroundPanel;

    /**
     * Constructor that initializes the enhanced themed GUI
     */
    public EnhancedThemeGUI() {
        super();
        applyTheme();
        initAnimations();
    }
      /**
     * Apply the professional dark theme to the game
     */
    private void applyTheme() {
        // Modify the frame
        setTitle("★ Rock Paper Scissors Ultimate: Galactic Edition ★");
        
        // Create an animated gradient background panel
        backgroundPanel = new AnimatedGradientPanel(DARK_BG, DARK_BG.darker().darker());
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);
        
        // Create a main container panel with our themed look
        JPanel mainContainer = createThemedPanel(new BorderLayout(15, 15));
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Initialize the particle system
        particleSystem = new ParticleSystem(mainContainer, ACCENT_COLOR);
        
        // Setup the UI components in our themed container
        setupThemedUI(mainContainer);
        
        // Add the main container to our background
        backgroundPanel.add(mainContainer, BorderLayout.CENTER);
        
        // Make sure everything is visible and properly sized
        revalidate();
        repaint();
    }
      /**
     * Set up the themed UI components
     */
    private void setupThemedUI(JPanel mainPanel) {
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header Panel with title and round info
        JPanel headerPanel = createThemedPanel(new BorderLayout(10, 10));
        JLabel titleLabel = new JLabel("Rock Paper Scissors Lizard Spock", JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Right side with time and round info
        JPanel timeRoundPanel = createThemedPanel(new GridLayout(2, 1, 5, 5));
        
        // Create custom time and round labels as the reflection might fail
        JLabel timeLabel = new JLabel("Time: 00:00", JLabel.CENTER);
        timeLabel.setFont(LABEL_FONT);
        timeLabel.setForeground(TEXT_COLOR);
        
        JLabel roundLabel = new JLabel("Round: 0", JLabel.CENTER);
        roundLabel.setFont(LABEL_FONT);
        roundLabel.setForeground(TEXT_COLOR);
        
        // Try to get the original labels
        try {
            JLabel origTimeLabel = getTimeLabel();
            JLabel origRoundLabel = getRoundLabel();
            
            if (origTimeLabel != null) {
                timeLabel = origTimeLabel;
                timeLabel.setFont(LABEL_FONT);
                timeLabel.setForeground(TEXT_COLOR);
            }
            
            if (origRoundLabel != null) {
                roundLabel = origRoundLabel;
                roundLabel.setFont(LABEL_FONT);
                roundLabel.setForeground(TEXT_COLOR);
            }
        } catch (Exception e) {
            System.err.println("Error getting original labels: " + e.getMessage());
        }
        
        timeRoundPanel.add(timeLabel);
        timeRoundPanel.add(roundLabel);
        headerPanel.add(timeRoundPanel, BorderLayout.EAST);
          // Score Panel
        JPanel scorePanel = createThemedPanel(new GridLayout(1, 3, 15, 0));
        
        // Create default score labels
        JLabel playerScoreLabel = new JLabel("Player: 0", JLabel.CENTER);
        playerScoreLabel.setFont(LABEL_FONT);
        playerScoreLabel.setForeground(TEXT_COLOR);
        playerScoreLabel.setBorder(createNeonBorder(PRIMARY_COLOR));
        
        JLabel computerScoreLabel = new JLabel("Computer: 0", JLabel.CENTER);
        computerScoreLabel.setFont(LABEL_FONT);
        computerScoreLabel.setForeground(TEXT_COLOR);
        computerScoreLabel.setBorder(createNeonBorder(SECONDARY_COLOR));
        
        JLabel drawsLabel = new JLabel("Draws: 0", JLabel.CENTER);
        drawsLabel.setFont(LABEL_FONT);
        drawsLabel.setForeground(TEXT_COLOR);
        drawsLabel.setBorder(createNeonBorder(ACCENT_COLOR));
        
        // Try to get the original labels
        try {
            JLabel origPlayerScoreLabel = getPlayerScoreLabel();
            JLabel origComputerScoreLabel = getComputerScoreLabel();
            JLabel origDrawsLabel = getDrawsLabel();
            
            if (origPlayerScoreLabel != null) {
                playerScoreLabel = origPlayerScoreLabel;
                playerScoreLabel.setFont(LABEL_FONT);
                playerScoreLabel.setForeground(TEXT_COLOR);
                playerScoreLabel.setBorder(createNeonBorder(PRIMARY_COLOR));
            }
            
            if (origComputerScoreLabel != null) {
                computerScoreLabel = origComputerScoreLabel;
                computerScoreLabel.setFont(LABEL_FONT);
                computerScoreLabel.setForeground(TEXT_COLOR);
                computerScoreLabel.setBorder(createNeonBorder(SECONDARY_COLOR));
            }
            
            if (origDrawsLabel != null) {
                drawsLabel = origDrawsLabel;
                drawsLabel.setFont(LABEL_FONT);
                drawsLabel.setForeground(TEXT_COLOR);
                drawsLabel.setBorder(createNeonBorder(ACCENT_COLOR));
            }
        } catch (Exception e) {
            System.err.println("Error getting original score labels: " + e.getMessage());
        }
        
        scorePanel.add(playerScoreLabel);
        scorePanel.add(computerScoreLabel);
        scorePanel.add(drawsLabel);
          // Game Panel
        JPanel gamePanel = createThemedPanel(new GridLayout(3, 1, 10, 10));
        
        // Create default result and move labels
        JLabel resultLabel = new JLabel("Choose your move!", JLabel.CENTER);
        resultLabel.setFont(TITLE_FONT);
        resultLabel.setForeground(ACCENT_COLOR);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel playerMoveLabel = new JLabel("Player: Not played yet", JLabel.CENTER);
        playerMoveLabel.setFont(LABEL_FONT);
        playerMoveLabel.setForeground(PRIMARY_COLOR);
        
        JLabel computerMoveLabel = new JLabel("Computer: Not played yet", JLabel.CENTER);
        computerMoveLabel.setFont(LABEL_FONT);
        computerMoveLabel.setForeground(SECONDARY_COLOR);
        
        // Try to get the original result and move labels
        try {
            JLabel origResultLabel = getResultLabel();
            if (origResultLabel != null) {
                resultLabel = origResultLabel;
                resultLabel.setFont(TITLE_FONT);
                resultLabel.setForeground(ACCENT_COLOR);
                resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }
            
            JLabel origPlayerMoveLabel = getPlayerMoveLabel();
            if (origPlayerMoveLabel != null) {
                playerMoveLabel = origPlayerMoveLabel;
                playerMoveLabel.setFont(LABEL_FONT);
                playerMoveLabel.setForeground(PRIMARY_COLOR);
            }
            
            JLabel origComputerMoveLabel = getComputerMoveLabel();
            if (origComputerMoveLabel != null) {
                computerMoveLabel = origComputerMoveLabel;
                computerMoveLabel.setFont(LABEL_FONT);
                computerMoveLabel.setForeground(SECONDARY_COLOR);
            }
        } catch (Exception e) {
            System.err.println("Error getting original game labels: " + e.getMessage());
        }
        
        gamePanel.add(resultLabel);
        gamePanel.add(playerMoveLabel);
        gamePanel.add(computerMoveLabel);
        
        // Replace the move buttons panel
        JPanel moveButtonsPanel = createThemedPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        loadThemedButtons(moveButtonsPanel);
        
        // Add all panels to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scorePanel, BorderLayout.CENTER);
        mainPanel.add(gamePanel, BorderLayout.SOUTH);
        mainPanel.add(moveButtonsPanel, BorderLayout.WEST);
        
        // Create a controls panel for game modes
        JPanel controlsPanel = createThemedPanel(new GridLayout(3, 1, 5, 5));
        
        // Add game mode buttons
        JButton classicModeBtn = createThemedButton("Classic Mode", PRIMARY_COLOR);
        classicModeBtn.addActionListener(e -> changeGameMode(GameMode.CLASSIC));
        
        JButton extendedModeBtn = createThemedButton("Extended Mode", SECONDARY_COLOR);
        extendedModeBtn.addActionListener(e -> changeGameMode(GameMode.EXTENDED));
          JButton resetBtn = createThemedButton("Reset Game", ACCENT_COLOR);
        resetBtn.addActionListener(e -> {
            try {
                java.lang.reflect.Method method = EnhancedGameGUI.class.getDeclaredMethod("resetGame");
                method.setAccessible(true);
                method.invoke(this);
            } catch (Exception ex) {
                System.err.println("Error resetting game: " + ex.getMessage());
            }
        });
        
        controlsPanel.add(classicModeBtn);
        controlsPanel.add(extendedModeBtn);
        controlsPanel.add(resetBtn);
        
        mainPanel.add(controlsPanel, BorderLayout.EAST);
    }
    
    /**
     * Create a themed button with neon glow effect
     */
    private JButton createThemedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Background with gradient
                GradientPaint gradient = new GradientPaint(
                    0, 0, color.darker().darker(), 
                    0, getHeight(), color
                );
                g2d.setPaint(gradient);
                
                // Draw rounded rectangle
                RoundRectangle2D roundRect = new RoundRectangle2D.Float(
                    0, 0, getWidth() - 1, getHeight() - 1, 15, 15
                );
                g2d.fill(roundRect);
                
                // Draw neon glow border
                g2d.setStroke(new BasicStroke(2f));
                g2d.setColor(color.brighter());
                g2d.draw(roundRect);
                
                // Draw text with shadow
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                
                // Draw shadow
                g2d.setColor(color.darker().darker());
                g2d.drawString(getText(), x + 1, y + 1);
                
                // Draw text
                g2d.setColor(TEXT_COLOR);
                g2d.drawString(getText(), x, y);
            }
        };
        
        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(150, 40));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
                button.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
                button.repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(color.darker());
                button.repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(color);
                button.repaint();
            }
        });

        // Fix hover state
        button.addPropertyChangeListener("hovered", e -> {
            boolean hovering = Boolean.TRUE.equals(e.getNewValue());
            if (hovering) {
                ((JButton)button).putClientProperty("hovered", Boolean.TRUE);
            } else {
                ((JButton)button).putClientProperty("hovered", Boolean.FALSE);
            }
        });
        
        // Check for property access
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton)e.getSource()).putClientProperty("hovered", Boolean.TRUE);
                button.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton)e.getSource()).putClientProperty("hovered", Boolean.FALSE);
                button.repaint();
            }
        });
        
        return button;
    }
    
    /**
     * Create a themed panel with transparent background
     */
    private JPanel createThemedPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Slightly transparent panel background
                Color panelBg = new Color(DARK_BG.getRed(), DARK_BG.getGreen(), DARK_BG.getBlue(), 200);
                g2d.setColor(panelBg);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Subtle border
                g2d.setColor(new Color(60, 63, 65));
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }
        };
        
        panel.setOpaque(false);
        return panel;
    }
    
    /**
     * Create a neon border for panels and labels
     */
    private Border createNeonBorder(Color color) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 1),
            BorderFactory.createLineBorder(color, 1)
        );
    }
    
    /**
     * Load themed buttons for game moves
     */
    private void loadThemedButtons(JPanel moveButtonsPanel) {
        // Create themed move buttons
        JButton rockBtn = createMoveButton("Rock", getRockIcon(), ROCK_COLOR);
        JButton paperBtn = createMoveButton("Paper", getPaperIcon(), PAPER_COLOR);
        JButton scissorsBtn = createMoveButton("Scissors", getScissorsIcon(), SCISSORS_COLOR);
        JButton lizardBtn = createMoveButton("Lizard", getLizardIcon(), LIZARD_COLOR);
        JButton spockBtn = createMoveButton("Spock", getSpockIcon(), SPOCK_COLOR);
        
        // Add buttons to panel
        moveButtonsPanel.add(rockBtn);
        moveButtonsPanel.add(paperBtn);
        moveButtonsPanel.add(scissorsBtn);
        moveButtonsPanel.add(lizardBtn);
        moveButtonsPanel.add(spockBtn);
        
        // Show/hide buttons based on game mode
        updateButtonVisibility(moveButtonsPanel);
    }
      /**
     * Create a styled move button with an icon and color scheme
     */
    private JButton createMoveButton(String text, ImageIcon icon, Color color) {
        JButton button = new JButton(text, icon) {
            // Store hover state for animations
            private boolean hovered = false;
            private float pulseEffect = 0.0f;
            private Timer pulseTimer = new Timer(50, e -> {
                pulseEffect += 0.1f;
                if (pulseEffect > 2 * Math.PI) {
                    pulseEffect = 0;
                }
                repaint();
            });
            
            {
                // Initialize and start the pulse animation
                pulseTimer.start();
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Use a circular background for move buttons
                int size = Math.min(getWidth(), getHeight()) - 10;
                // Make button larger when hovered
                if (hovered) {
                    size += 6;
                }
                int x = (getWidth() - size) / 2;
                int y = (getHeight() - size) / 2;
                
                // Background with radial gradient
                RadialGradientPaint gradient = new RadialGradientPaint(
                    new Point(getWidth()/2, getHeight()/2),
                    size/2,
                    new float[] {0.0f, 1.0f},
                    new Color[] {color, color.darker().darker()}
                );
                g2d.setPaint(gradient);
                g2d.fillOval(x, y, size, size);
                
                // Draw neon glow border with pulse effect
                float pulseIntensity = (float)(0.7f + 0.3f * Math.sin(pulseEffect));
                Stroke borderStroke = new BasicStroke(hovered ? 3f : 2f);
                g2d.setStroke(borderStroke);
                
                // Create a brighter color for glow
                Color glowColor = new Color(
                    Math.min(255, (int)(color.getRed() * 1.5)),
                    Math.min(255, (int)(color.getGreen() * 1.5)),
                    Math.min(255, (int)(color.getBlue() * 1.5))
                );
                
                // Apply pulsing to alpha for glow effect
                Color pulsingGlow = new Color(
                    glowColor.getRed(),
                    glowColor.getGreen(), 
                    glowColor.getBlue(),
                    (int)(255 * pulseIntensity)
                );
                
                g2d.setColor(pulsingGlow);
                g2d.drawOval(x, y, size, size);
                
                // Draw the icon centered
                if (getIcon() != null) {
                    int iconX = (getWidth() - getIcon().getIconWidth()) / 2;
                    int iconY = (getHeight() - getIcon().getIconHeight()) / 2 - 5;
                    getIcon().paintIcon(this, g2d, iconX, iconY);
                }
                
                // Draw text at the bottom
                FontMetrics metrics = g2d.getFontMetrics(getFont());
                int textX = (getWidth() - metrics.stringWidth(getText())) / 2;
                int textY = y + size + metrics.getAscent() - 5;
                
                // Draw shadow
                g2d.setColor(color.darker().darker());
                g2d.drawString(getText(), textX + 1, textY + 1);
                
                // Draw text
                g2d.setColor(TEXT_COLOR);
                g2d.drawString(getText(), textX, textY);
            }
        };
        
        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(100, 100));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
          // Add action listener
        button.addActionListener(e -> {
            getSoundManager().playSound("button_click");
            
            // Add particle effect at button location
            Point p = button.getLocationOnScreen();
            SwingUtilities.convertPointFromScreen(p, backgroundPanel);
            particleSystem.start(p.x + button.getWidth()/2, p.y + button.getHeight()/2);
            
            // Play the move
            playMoveWithAnimation(Move.valueOf(text.toUpperCase()));
        });
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
                button.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
                button.repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(color.darker());
                button.repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(color);
                button.repaint();
            }
        });

        // Fix hover state
        button.addPropertyChangeListener("hovered", e -> {
            boolean hovering = Boolean.TRUE.equals(e.getNewValue());
            if (hovering) {
                ((JButton)button).putClientProperty("hovered", Boolean.TRUE);
            } else {
                ((JButton)button).putClientProperty("hovered", Boolean.FALSE);
            }
        });
        
        // Check for property access
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton)e.getSource()).putClientProperty("hovered", Boolean.TRUE);
                button.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton)e.getSource()).putClientProperty("hovered", Boolean.FALSE);
                button.repaint();
            }
        });
        
        return button;
    }
    
    /**
     * Update button visibility based on game mode
     */
    private void updateButtonVisibility(JPanel moveButtonsPanel) {
        Component[] components = moveButtonsPanel.getComponents();
        if (components.length >= 5) {
            // First three buttons (rock, paper, scissors) always visible
            components[0].setVisible(true);
            components[1].setVisible(true);
            components[2].setVisible(true);
            
            // Lizard and Spock only visible in extended mode
            GameMode mode = getGameMode();
            boolean extended = (mode == GameMode.EXTENDED);
            components[3].setVisible(extended);
            components[4].setVisible(extended);
        }
    }
    
    /**
     * Method to change game mode with visual feedback
     */
    private void changeGameMode(GameMode mode) {
        // Access the super's method using reflection since it's private
        try {
            java.lang.reflect.Method method = GameGUI.class.getDeclaredMethod("changeGameMode", GameMode.class);
            method.setAccessible(true);
            method.invoke(this, mode);
            
            // Update button visibility
            for (Component comp : getContentPane().getComponents()) {
                if (comp instanceof JPanel) {
                    updatePanelButtons((JPanel)comp);
                }
            }
            
            // Visual feedback that mode changed
            JLabel resultLabel = getResultLabel();
            resultLabel.setText("Mode changed to " + mode.toString());
            resultLabel.setForeground(mode == GameMode.CLASSIC ? PRIMARY_COLOR : SECONDARY_COLOR);
            
            // Start pulse animation for visual feedback
            pulseStage = 0;
            pulseAnimationTimer.start();
            
        } catch (Exception e) {
            System.err.println("Error changing game mode: " + e.getMessage());
        }
    }
    
    /**
     * Recursively update buttons in all panels
     */
    private void updatePanelButtons(JPanel panel) {
        Component[] components = panel.getComponents();
        
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                // If it's a move buttons panel, update visibility
                if (((JPanel)comp).getLayout() instanceof FlowLayout) {
                    updateButtonVisibility((JPanel)comp);
                }
                // Recursively check child panels
                updatePanelButtons((JPanel)comp);
            }
        }
    }
    
    /**
     * Initialize animations for UI effects
     */
    private void initAnimations() {
        // Pulse animation for visual feedback
        pulseAnimationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pulseStage++;
                
                // Pulse the result label
                JLabel resultLabel = getResultLabel();
                float alpha = Math.abs((float)Math.sin(pulseStage * 0.1));
                resultLabel.setForeground(new Color(
                    resultLabel.getForeground().getRed(),
                    resultLabel.getForeground().getGreen(),
                    resultLabel.getForeground().getBlue(),
                    (int)(alpha * 255)
                ));
                
                // Stop after complete cycle
                if (pulseStage > 60) {
                    pulseAnimationTimer.stop();
                    resultLabel.setForeground(ACCENT_COLOR);
                }
            }
        });
    }
    
    // Accessor method to get the current game mode
    private GameMode getGameMode() {
        try {
            java.lang.reflect.Field field = GameGUI.class.getDeclaredField("currentMode");
            field.setAccessible(true);
            return (GameMode) field.get(this);
        } catch (Exception e) {
            System.err.println("Error accessing game mode: " + e.getMessage());
            return GameMode.CLASSIC; // Default
        }
    }

    /**
     * Method to render the particle effects on top of everything else
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Render particle effects on top of everything
        if (particleSystem != null) {
            Graphics2D g2d = (Graphics2D) g;
            particleSystem.draw(g2d);
        }
    }    // Particle effect system
    private static class ParticleSystem {
        private static final int MAX_PARTICLES = 100;
        private final List<Particle> particles = new ArrayList<>();
        private final Random random = new Random();
        private final Timer animationTimer;
        private final Color particleColor;
        private boolean active = false;
        
        public ParticleSystem(JPanel parentPanel, Color particleColor) {
            this.particleColor = particleColor;
            
            animationTimer = new Timer(16, e -> {
                if (active) {
                    updateParticles();
                    parentPanel.repaint();
                }
            });
        }
        
        public void start(int x, int y) {
            // Clear any existing particles
            particles.clear();
            
            // Create new particles
            for (int i = 0; i < MAX_PARTICLES; i++) {
                double angle = random.nextDouble() * Math.PI * 2;
                double speed = 1 + random.nextDouble() * 5;
                double size = 2 + random.nextDouble() * 6;
                
                Particle p = new Particle(
                    x, y,
                    Math.cos(angle) * speed,
                    Math.sin(angle) * speed,
                    size,
                    new Color(
                        particleColor.getRed(),
                        particleColor.getGreen(),
                        particleColor.getBlue(),
                        200 + random.nextInt(55)
                    ),
                    20 + random.nextInt(40)
                );
                
                particles.add(p);
            }
            
            active = true;
            animationTimer.start();
        }
        
        public void stop() {
            active = false;
            animationTimer.stop();
        }
        
        private void updateParticles() {
            boolean anyActive = false;
            
            for (Particle p : particles) {
                if (p.update()) {
                    anyActive = true;
                }
            }
            
            if (!anyActive) {
                stop();
            }
        }
        
        public void draw(Graphics2D g2d) {
            if (!active) return;
            
            for (Particle p : particles) {
                p.draw(g2d);
            }
        }
        
        // Inner Particle class
        private static class Particle {
            private double x, y;
            private final double vx, vy;
            private final double size;
            private final Color color;
            private int life;
            private final int maxLife;
            
            public Particle(double x, double y, double vx, double vy, double size, Color color, int maxLife) {
                this.x = x;
                this.y = y;
                this.vx = vx;
                this.vy = vy;
                this.size = size;
                this.color = color;
                this.life = maxLife;
                this.maxLife = maxLife;
            }
            
            public boolean update() {
                if (life <= 0) return false;
                
                x += vx;
                y += vy;
                life--;
                
                return life > 0;
            }
            
            public void draw(Graphics2D g2d) {
                if (life <= 0) return;
                
                int alpha = (int)((double)life / maxLife * 255);
                Color fadeColor = new Color(
                    color.getRed(), 
                    color.getGreen(), 
                    color.getBlue(), 
                    Math.min(color.getAlpha(), alpha)
                );
                
                g2d.setColor(fadeColor);
                g2d.fillOval(
                    (int)(x - size/2), 
                    (int)(y - size/2), 
                    (int)size, 
                    (int)size
                );
            }
        }
    }
    
    // Animated background system
    private static class AnimatedGradientPanel extends JPanel {
        private final Color color1;
        private final Color color2;
        private float gradientPosition = 0.0f;
        private final Timer animationTimer;
        
        public AnimatedGradientPanel(Color color1, Color color2) {
            this.color1 = color1;
            this.color2 = color2;
            setOpaque(false);
            
            animationTimer = new Timer(50, e -> {
                gradientPosition += 0.01f;
                if (gradientPosition > 1.0f) {
                    gradientPosition = 0.0f;
                }
                repaint();
            });
            
            animationTimer.start();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            
            // Create a moving gradient
            float x1 = getWidth() * gradientPosition;
            float y1 = 0;
            float x2 = x1 + getWidth();
            float y2 = getHeight();
            
            GradientPaint gp = new GradientPaint(x1, y1, color1, x2, y2, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            // Add a subtle grid pattern for a tech look
            g2d.setColor(new Color(255, 255, 255, 10));
            int gridSize = 20;
            for (int i = 0; i < getWidth(); i += gridSize) {
                g2d.drawLine(i, 0, i, getHeight());
            }
            for (int i = 0; i < getHeight(); i += gridSize) {
                g2d.drawLine(0, i, getWidth(), i);
            }
        }
    }
}
