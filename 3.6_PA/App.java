public class App {
    public static void main(String[] args) {

        System.out.println("Your Name - Week 3 Abstraction Performance Assessment\n");

        CardGame rummy = new CardGame("Rummy", 2, 13);
        DiceGame monopoly = new DiceGame("Monopoly", 4, 12);
        SpinnerGame life = new SpinnerGame("Life", 3, 10);

        System.out.println("Card Game Information");
        System.out.println(rummy);

        System.out.println("Board Game with Dice Information");
        System.out.println(monopoly);

        System.out.println("Board Game with Spinner Information");
        System.out.println(life);
    }
}
