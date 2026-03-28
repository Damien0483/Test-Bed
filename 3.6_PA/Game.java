public abstract class Game {
    private String name;
    private int numPlayers;

    public Game(String name, int numPlayers) {
        this.name = name;
        this.numPlayers = numPlayers;
    }

    public abstract String startGame();
    public abstract String startTurn();
    public abstract String endTurn();

    @Override
    public String toString() {
        return "Welcome to " + name + ". We have " + numPlayers + " players today.\n"
                + "To start the game, " + startGame() + "\n"
                + "To start a turn, " + startTurn() + "\n"
                + "The turn is over when " + endTurn() + "\n";
    }
}
