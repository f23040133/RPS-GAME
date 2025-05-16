public enum Move {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK;
    
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}