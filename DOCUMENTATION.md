# Rock-Paper-Scissors-Lizard-Spock: Technical Documentation

## Overview
This document provides technical details about the implementation of the Rock-Paper-Scissors-Lizard-Spock game, including class hierarchies, design patterns, and the relationships between components.

## Core Classes

### Main
Entry point to the application that initializes the game GUI.
```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        GameGUI game = new EnhancedThemeGUI();
        game.setVisible(true);
    });
}
```

### GameGUI
The base GUI class that extends JFrame and provides the fundamental game interface.
- Manages the main game window and components
- Handles player and computer moves
- Updates the UI based on game state

### EnhancedGameGUI
Extends GameGUI to provide enhanced visual elements and additional features:
- Enhanced image management
- Improved sound effects
- Animation support
- Better game flow

### EnhancedThemeGUI
The most advanced GUI implementation, extending EnhancedGameGUI with:
- Professional dark theme with neon accents
- Particle effects system
- Animated gradient backgrounds
- Interactive button hover effects
- Improved visual feedback

### Move
An enum representing the possible moves in the game:
- ROCK
- PAPER
- SCISSORS
- LIZARD
- SPOCK

Each move has defined relationships with other moves to determine the winner.

### GameMode
An enum defining the available game modes:
- CLASSIC (Rock, Paper, Scissors)
- EXTENDED (Rock, Paper, Scissors, Lizard, Spock)

### Player / Computer
Classes representing the player and computer entities in the game:
- Track moves
- Calculate scores
- Determine game strategy (for Computer)

### Game
Contains the core game logic:
- Rules for determining winners
- Score tracking
- Game state management

### GameImageManager / EnhancedGameImageManager / FixedGameImageManager
Hierarchy of classes for managing game images:
- Loading images from resource files
- Providing access to move icons
- Handling image scaling and caching

### SoundManager
Manages sound effects in the game:
- Loading sounds from files
- Playing appropriate sounds for game events
- Volume control

## Visual Effect Components

### ParticleSystem
Custom implementation in EnhancedThemeGUI that creates particle effects:
- Generated when moves are played
- Particles inherit the color of the selected move
- Fade out over time with smooth animations

### AnimatedGradientPanel
Custom JPanel that provides an animated background:
- Gradient colors that slowly shift
- Subtle grid pattern for visual depth
- Serves as the base layer for the game interface

## Design Patterns Used

### Strategy Pattern
Used in the Computer class to implement different AI strategies for selecting moves.

### Observer Pattern
Implemented to update UI components when the game state changes.

### Factory Method Pattern
Used in image and sound management to create and provide resources.

### Singleton Pattern
Applied to managers like SoundManager to ensure a single instance.

## How to Extend the Game

### Adding New Moves
1. Add the new move to the Move enum
2. Define its relationships with existing moves
3. Create or obtain an image for the new move
4. Update the UI to accommodate the new move

### Creating New Themes
1. Create a new class extending EnhancedGameGUI
2. Define custom colors, fonts, and visual elements
3. Override painting methods for custom rendering
4. Implement any additional animations or effects

### Adding Game Modes
1. Add the new mode to the GameMode enum
2. Implement logic for the new mode in the Game class
3. Update the UI to support the new mode
4. Add buttons or controls to switch to the new mode
