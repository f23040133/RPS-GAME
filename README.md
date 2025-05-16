# Rock Paper Scissors Lizard Spock Game

A professional Java implementation of the extended Rock-Paper-Scissors game popularized by the TV show "The Big Bang Theory". This version features both Classic mode (Rock, Paper, Scissors) and Extended mode (Rock, Paper, Scissors, Lizard, Spock) with an enhanced dark-themed UI, animations, and sound effects.

![Game Screenshot](resource/screenshot.png)

## Features

### Game Modes
- **Classic Mode**: Traditional Rock-Paper-Scissors gameplay
- **Extended Mode**: Rock-Paper-Scissors-Lizard-Spock with additional moves

### Enhanced Visual Design
- Professional dark theme with neon accents
- Animated gradient background with subtle tech-inspired grid
- Particle effects when moves are played
- Circular move buttons with custom glow effects
- Interactive hover and click animations
- Pulsing feedback on game state changes

### Game Elements
- Real-time score tracking
- Round counter
- Game timer
- Move history
- Win/lose/draw status display

### Sound Effects
- Button click feedback
- Game start sound
- Win/lose/draw sound effects

## Game Rules

### Classic Mode (Rock-Paper-Scissors)
- Rock crushes Scissors
- Scissors cuts Paper
- Paper covers Rock

### Extended Mode (Rock-Paper-Scissors-Lizard-Spock)
- Rock crushes Scissors and Lizard
- Paper covers Rock and disproves Spock
- Scissors cuts Paper and decapitates Lizard
- Lizard eats Paper and poisons Spock
- Spock vaporizes Rock and smashes Scissors

## How to Play

1. Launch the game using the Main class
2. Select your desired game mode (Classic or Extended)
3. Click on a move button to play your move
4. The computer will randomly select its move
5. Results will be displayed and scores updated
6. Continue playing for as many rounds as you wish
7. Reset the game at any time to start over

## Technical Details

### Architecture
The game is built with a modular architecture following object-oriented principles:
- **Model**: Game logic, rules, and state (Game, Move, Player, Computer)
- **View**: UI components and visual presentation (GameGUI, EnhancedThemeGUI)
- **Controller**: Game flow and event handling (Main, GameTimer)

### Visual Effects Implementation
- Custom painting using Java2D
- Double buffering for smooth animations
- Particle system for interactive effects
- Timer-based animations for dynamic elements

### Requirements
- Java 8 or higher
- Swing for the GUI components
- No external dependencies required

## Getting Started

### Running the Game
1. Compile the Java files:
   ```
   javac Main.java
   ```
2. Run the compiled Main class:
   ```
   java Main
   ```

### Controls
- Use the mouse to select game modes and make moves
- Game status is displayed in the central panel
- Scores are tracked and displayed continuously

## Credits
- Original concept: The Big Bang Theory TV show
- Implementation: [Your Name/Team]
- Images: Custom designed for this project
- Sound Effects: Open source sound library

## License
[Specify your license information here]
