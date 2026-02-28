/*======================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description:  Main program loop for Damien's Flooring Calculator.Allows 
users to create rooms, store them, delete them, and print detailed 
summaries using inheritance-based room types (FeetRoom, InchesRoom).
======================================================================*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RoomManager manager = new RoomManager();

        System.out.println("====================================================");
        System.out.println("      Welcome to Damien’s Flooring Calculator!!!");
        System.out.println("====================================================");
        System.out.println();
        System.out.println("Measuring a room always feels a bit like preparing for a home‑improvement safari.");
        System.out.println("You stride in with your tape measure like an explorer entering uncharted territory,");
        System.out.println("hoping the floor won’t reveal any surprise “bonus” corners the architect dreamed up");
        System.out.println("on a Friday afternoon.");
        System.out.println();
        System.out.println("You’ll want the usual gear:");
        System.out.println("- A tape measure long enough to reach the far wall without snapping back");
        System.out.println("- A pencil you will absolutely lose within five minutes");
        System.out.println("- Paper for sketching the room, even though it will end up looking like a treasure map");
        System.out.println("- A calculator (or this program) to make sense of the numbers");
        System.out.println("- And a healthy sense of optimism, because every room is 10% bigger once you start paying for flooring");
        System.out.println();
        System.out.println("Armed with those tools, you're ready to measure like a pro—or at least look convincingly busy while doing it.");
        System.out.println();

        boolean running = true;

        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Create New Room");
            System.out.println("2. View Room Summary");
            System.out.println("3. Clear/Delete a Room");
            System.out.println("4. List All Rooms");
            System.out.println("5. Quit");
            System.out.print("Selection: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: {
                    System.out.print("Enter room name: ");
                    String name = sc.nextLine();

                    System.out.println("Choose measurement unit:");
                    System.out.println("1 = Feet");
                    System.out.println("2 = Inches");
                    System.out.print("Selection: ");
                    int unit = sc.nextInt();

                    Room room = (unit == 1) ? new FeetRoom(name) : new InchesRoom(name);

                    System.out.print("How many rectangular sections? ");
                    int sections = sc.nextInt();

                    for (int i = 1; i <= sections; i++) {
                        System.out.println("Section " + i + ":");
                        System.out.print("  Length (" + room.unitName() + "): ");
                        double length = sc.nextDouble();
                        System.out.print("  Width (" + room.unitName() + "): ");
                        double width = sc.nextDouble();
                        room.addSection(length, width);
                    }

                    System.out.print("Enter waste percentage (0–30): ");
                    room.setWastePercent(sc.nextDouble());

                    System.out.println("Choose material type:");
                    System.out.println("1 = Laminate");
                    System.out.println("2 = Hardwood");
                    System.out.println("3 = Tile");
                    System.out.println("4 = Carpet");
                    System.out.print("Selection: ");
                    int m = sc.nextInt();

                    String type = "";
                    boolean yards = false;

                    switch (m) {
                        case 1: type = "Laminate"; break;
                        case 2: type = "Hardwood"; break;
                        case 3: type = "Tile"; break;
                        case 4: type = "Carpet"; yards = true; break;
                    }

                    System.out.print("Enter cost per unit (" + (yards ? "sq yard" : "sq ft") + "): ");
                    double cost = sc.nextDouble();

                    room.setMaterial(new Material(type, cost, yards));

                    manager.addRoom(room);
                    System.out.println("Room saved!");
                    break;
                }

                case 2: {
                    System.out.print("Enter room name to view summary: ");
                    String name = sc.nextLine();
                    Room r = manager.getRoom(name);
                    if (r != null) r.printSummary();
                    else System.out.println("Room not found.");
                    break;
                }

                case 3: {
                    System.out.print("Enter room name to delete: ");
                    String name = sc.nextLine();
                    manager.removeRoom(name);
                    System.out.println("Room removed (if it existed).");
                    break;
                }

                case 4:
                    manager.listRooms();
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }

        System.out.println("Thank you for using my Flooring Calculator. See you in SDC330...Goodbye!");
        sc.close();
    }
}
