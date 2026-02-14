import java.util.Scanner;

public class MenuTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose material type:");
        System.out.println("1 = Laminate");
        System.out.println("2 = Hardwood");
        System.out.println("3 = Tile");
        System.out.println("4 = Carpet");
        System.out.print("Selection: ");
        int materialChoice = sc.nextInt();

        String type = "";
        boolean usesSqYards = false;

        switch (materialChoice) {
            case 1: type = "Laminate"; break;
            case 2: type = "Hardwood"; break;
            case 3: type = "Tile"; break;
            case 4: type = "Carpet"; usesSqYards = true; break;
            default: type = "Unknown"; break;
        }

        System.out.print("Enter cost per unit (" +
                (usesSqYards ? "sq yard" : "sq ft") + "): ");
        double cost = sc.nextDouble();

        System.out.println("\n===== MENU TEST OUTPUT =====");
        System.out.println("Material: " + type);
        System.out.println("Uses Square Yards: " + usesSqYards);
        System.out.println("Cost per Unit: $" + cost);
        System.out.println("============================");

        sc.close();
    }
}
