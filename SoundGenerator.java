import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundGenerator {
    public static void main(String[] args) {        try {
            // Create win sound
            createWinSound("sounds/win.wav");
            
            // Create lose sound
            createLoseSound("sounds/lose.wav");
            
            // Create draw sound
            createDrawSound("sounds/draw.wav");
            
            // Create button click sound
            createClickSound("sounds/button_click.wav");
            
            // Create game start sound
            createStartSound("sounds/game_start.wav");
            
            System.out.println("All sound files generated successfully!");
            
        } catch (IOException e) {
            System.err.println("Error generating sound files: " + e.getMessage());
            System.err.println("Error details: " + e);
        }
    }
    
    private static void createWinSound(String filename) throws IOException {
        // Win sound - happy tone (higher frequency)
        byte[] buffer = createTone(440, 150, 0.8f); // A4 note
        byte[] buffer2 = createTone(523, 150, 0.8f); // C5 note
        byte[] buffer3 = createTone(659, 300, 0.8f); // E5 note
        
        byte[] combined = new byte[buffer.length + buffer2.length + buffer3.length];
        System.arraycopy(buffer, 0, combined, 0, buffer.length);
        System.arraycopy(buffer2, 0, combined, buffer.length, buffer2.length);
        System.arraycopy(buffer3, 0, combined, buffer.length + buffer2.length, buffer3.length);
        
        saveWav(combined, filename);
    }
    
    private static void createLoseSound(String filename) throws IOException {
        // Lose sound - sad tone (lower frequency)
        byte[] buffer = createTone(392, 150, 0.8f); // G4 note
        byte[] buffer2 = createTone(349, 150, 0.8f); // F4 note
        byte[] buffer3 = createTone(294, 300, 0.8f); // D4 note
        
        byte[] combined = new byte[buffer.length + buffer2.length + buffer3.length];
        System.arraycopy(buffer, 0, combined, 0, buffer.length);
        System.arraycopy(buffer2, 0, combined, buffer.length, buffer2.length);
        System.arraycopy(buffer3, 0, combined, buffer.length + buffer2.length, buffer3.length);
        
        saveWav(combined, filename);
    }
    
    private static void createDrawSound(String filename) throws IOException {
        // Draw sound - neutral tone
        byte[] buffer = createTone(349, 150, 0.8f); // F4 note
        byte[] buffer2 = createTone(349, 300, 0.8f); // F4 note again
        
        byte[] combined = new byte[buffer.length + buffer2.length];
        System.arraycopy(buffer, 0, combined, 0, buffer.length);
        System.arraycopy(buffer2, 0, combined, buffer.length, buffer2.length);
        
        saveWav(combined, filename);
    }
    
    private static void createClickSound(String filename) throws IOException {
        // Click sound - very short
        byte[] buffer = createTone(880, 50, 0.5f); // A5 note, short duration
        saveWav(buffer, filename);
    }
    
    private static void createStartSound(String filename) throws IOException {
        // Start sound - welcoming tone
        byte[] buffer = createTone(523, 100, 0.6f); // C5 note
        byte[] buffer2 = createTone(659, 100, 0.7f); // E5 note
        byte[] buffer3 = createTone(784, 300, 0.8f); // G5 note
        
        byte[] combined = new byte[buffer.length + buffer2.length + buffer3.length];
        System.arraycopy(buffer, 0, combined, 0, buffer.length);
        System.arraycopy(buffer2, 0, combined, buffer.length, buffer2.length);
        System.arraycopy(buffer3, 0, combined, buffer.length + buffer2.length, buffer3.length);
        
        saveWav(combined, filename);
    }
    
    private static byte[] createTone(int frequency, int msLength, float volume) {
        // Sample rate and bits
        float sampleRate = 44100.0f;
        int sampleSizeInBits = 8;
        
        int samples = (int)((float)msLength / 1000.0f * sampleRate);
        byte[] output = new byte[samples];
        
        // Generate sine wave tone
        double period = (double)sampleRate / frequency;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte)(Math.sin(angle) * volume * 127.0);
        }
        
        return output;
    }
      private static void saveWav(byte[] buffer, String filename) throws IOException {
        // Audio format
        AudioFormat format = new AudioFormat(44100, 8, 1, true, false);
        
        // Create audio stream and ensure proper resource closing with try-with-resources
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
             AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length)) {
            
            // Save to file
            File file = new File(filename);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
            
            System.out.println("Created sound file: " + filename);
        }
    }
}
