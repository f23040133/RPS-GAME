public enum GameMode {
    CLASSIC {
        @Override
        public GameResult determineWinner(Move playerMove, Move computerMove) {            if (playerMove == computerMove) {
                return GameResult.DRAW;
            }
            
            return switch (playerMove) {
                case ROCK -> (computerMove == Move.SCISSORS) ? GameResult.WIN : GameResult.LOSE;
                case PAPER -> (computerMove == Move.ROCK) ? GameResult.WIN : GameResult.LOSE;
                case SCISSORS -> (computerMove == Move.PAPER) ? GameResult.WIN : GameResult.LOSE;
                default -> GameResult.INVALID;
            };
        }
    },
    
    EXTENDED {
        @Override
        public GameResult determineWinner(Move playerMove, Move computerMove) {            if (playerMove == computerMove) {
                return GameResult.DRAW;
            }
            
            return switch (playerMove) {
                case ROCK -> (computerMove == Move.SCISSORS || computerMove == Move.LIZARD) ? 
                       GameResult.WIN : GameResult.LOSE;
                case PAPER -> (computerMove == Move.ROCK || computerMove == Move.SPOCK) ? 
                       GameResult.WIN : GameResult.LOSE;
                case SCISSORS -> (computerMove == Move.PAPER || computerMove == Move.LIZARD) ? 
                       GameResult.WIN : GameResult.LOSE;                case LIZARD -> (computerMove == Move.PAPER || computerMove == Move.SPOCK) ? 
                       GameResult.WIN : GameResult.LOSE;
                case SPOCK -> (computerMove == Move.ROCK || computerMove == Move.SCISSORS) ? 
                       GameResult.WIN : GameResult.LOSE;
                default -> GameResult.INVALID;
            };
        }
    };
    
    public abstract GameResult determineWinner(Move playerMove, Move computerMove);
}