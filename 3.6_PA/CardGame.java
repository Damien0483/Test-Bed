public class CardGame extends Game {
    private int numCards;

    public CardGame(String name, int numPlayers, int numCards) {
        super(name, numPlayers);
        this.numCards = numCards;
    }

    @Override
    public String startGame() {
        return "the dealer gives each player " + numCards + " cards.";
    }

    @Override
    public String startTurn() {
        return "the player draws a card or plays one from their hand.";
    }

    @Override
    public String endTurn() {
        return "the player discards a card.";
    }
}
