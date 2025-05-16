public enum GameType {
    PVC("Player vs Computer"),
    PVP("Player vs Player");
    
    private final String description;
    
    GameType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return description;
    }
}
