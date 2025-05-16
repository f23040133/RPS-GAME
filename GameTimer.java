import java.time.Duration;

public class GameTimer {
    private long startTime = 0;
    private long totalTime = 0;
    private boolean isRunning = false;
    
    // Removed the overridable method call in constructor
    public GameTimer() {
        // Fields initialized directly above
    }
    
    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }
    
    public void stop() {
        if (isRunning) {
            totalTime += System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }
    
    public void reset() {
        startTime = 0;
        totalTime = 0;
        isRunning = false;
    }
    
    public String getFormattedTime() {
        long currentTime = totalTime;
        if (isRunning) {
            currentTime += System.currentTimeMillis() - startTime;
        }
        
        Duration duration = Duration.ofMillis(currentTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}