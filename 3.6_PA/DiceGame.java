public class DiceGame extends BoardGame {

    public DiceGame(String name, int numPlayers, int maxMove) {
        super(name, numPlayers, maxMove);
    }

    @Override
    public String startGame() {
        return "all players roll a die; highest roll goes first.";
    }

    @Override
    public String startTurn() {
        return "the player rolls the dice to determine movement.";
    }
}
