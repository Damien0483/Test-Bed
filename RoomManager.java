/*========================================================================
Name: Damien Harmon
Date: February, 28 2026
Assignment: SDC230L Project Flooring Calculator
Description: Stores and manages multiple rooms. Supports adding, removing,
retrieving, and listing rooms by name.
=========================================================================*/
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomManager {

    private Map<String, Room> rooms = new LinkedHashMap<>();

    //Adds and stores a room to the room manager.
    public void addRoom(Room room) {
        rooms.put(room.name, room);
    }

    //Retrieves a room by name.
    public Room getRoom(String name) {
        return rooms.get(name);
    }

    //Removes a room by name.
    public void removeRoom(String name) {
        rooms.remove(name);
    }

    //Prints all stored room names.
    public void listRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms stored yet.");
            return;
        }
        System.out.println("Stored Rooms:");
        for (String name : rooms.keySet()) {
            System.out.println(" - " + name);
        }
    }
}
