import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class SoundManager {
    private final Map<String, Clip> soundClips;
    private boolean soundEnabled = true;
    
    public SoundManager() {
        soundClips = new HashMap<>();
        initializeSounds();
    }
    
    private void initializeSounds() {
        // Load sound files
        loadSound("win", "sounds/win.wav");
        loadSound("lose", "sounds/lose.wav");
        loadSound("draw", "sounds/draw.wav");
        loadSound("button_click", "sounds/button_click.wav");
        loadSound("game_start", "sounds/game_start.wav");
    }
    
    private void loadSound(String soundName, String filename) {
        try {
            // Try loading from resources first
            URL url = getClass().getResource("/" + filename);
            AudioInputStream audioStream;
            
            if (url != null) {
                audioStream = AudioSystem.getAudioInputStream(url);
            } else {
                // Fall back to file system
                File file = new File(filename);
                if (file.exists()) {
                    audioStream = AudioSystem.getAudioInputStream(file);
                } else {
                    System.err.println("Sound file not found: " + filename);
                    return;
                }
            }
            
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);            soundClips.put(soundName, clip);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + filename);
            System.err.println("Error details: " + e);
        }
    }
    
    public void playSound(String soundName) {
        if (!soundEnabled) return;
        
        Clip clip = soundClips.get(soundName);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    public void toggleSound() {
        soundEnabled = !soundEnabled;
        if (!soundEnabled) {
            // Stop all playing sounds
            for (Clip clip : soundClips.values()) {
                if (clip.isRunning()) {
                    clip.stop();
                }
            }
        }
    }
    
    public boolean isSoundEnabled() {
        return soundEnabled;
    }
    
    public void cleanup() {
        for (Clip clip : soundClips.values()) {
            clip.close();
        }
    }
}
