import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class ButtonClickGenerator {    public static void main(String[] args) {
        try {
            createButtonClickSound("sounds/button_click.wav");
            System.out.println("Button click sound file generated successfully!");
        } catch (IOException e) {
            System.err.println("Error generating sound file: " + e.getMessage());
            // Replaced printStackTrace() with more informative error message
            System.err.println("Error details: " + e);
        }
    }
    
    private static void createButtonClickSound(String filename) throws IOException {
        // Click sound - very short
        byte[] buffer = createTone(880, 50, 0.5f); // A5 note, short duration
        saveWav(buffer, filename);
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
        
        // Create audio stream and ensure automatic resource closing with try-with-resources
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
             AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length)) {
            
            // Save to file
            File file = new File(filename);
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
            
            System.out.println("Created sound file: " + filename);
        }
    }
}
