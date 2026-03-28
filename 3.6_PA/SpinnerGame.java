public class SpinnerGame extends BoardGame {

    public SpinnerGame(String name, int numPlayers, int maxMove) {
        super(name, numPlayers, maxMove);
    }

    @Override
    public String startGame() {
        return "everyone spins the spinner; closest to " + maxMove + " goes first.";
    }

    @Override
    public String startTurn() {
        return "the player spins the spinner to determine movement.";
    }
}
