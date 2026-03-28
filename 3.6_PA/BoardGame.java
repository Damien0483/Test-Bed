public abstract class BoardGame extends Game {
    protected int maxMove;

    public BoardGame(String name, int numPlayers, int maxMove) {
        super(name, numPlayers);
        this.maxMove = maxMove;
    }

    @Override
    public String endTurn() {
        return "the player moves their piece the required number of spaces.";
    }

    @Override
    public abstract String startGame();

    @Override
    public abstract String startTurn();
}
