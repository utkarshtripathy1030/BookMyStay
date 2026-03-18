import java.util.*;

class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}

class Inventory {
    private Map<String, Integer> roomAvailability = new HashMap<>();

    public void addRoom(String type, int count) {
        roomAvailability.put(type, count);
    }

    public int getAvailableRooms(String type) {
        return roomAvailability.getOrDefault(type, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return roomAvailability;
    }
}

class SearchService {

    public void searchAvailableRooms(Inventory inventory, Map<String, Room> roomData) {

        System.out.println("\nAvailable Rooms:\n");

        for (String type : inventory.getAllAvailability().keySet()) {

            int count = inventory.getAvailableRooms(type);

            if (count > 0) { // Validation logic

                Room room = roomData.get(type);

                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: " + room.getPrice());
                System.out.println("Amenities: " + room.getAmenities());
                System.out.println("Available: " + count);
                System.out.println("------------------------");
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addRoom("Single", 3);
        inventory.addRoom("Double", 0);
        inventory.addRoom("Suite", 2);

        Map<String, Room> roomData = new HashMap<>();
        roomData.put("Single", new Room("Single", 2000, "WiFi, TV"));
        roomData.put("Double", new Room("Double", 3500, "WiFi, AC, TV"));
        roomData.put("Suite", new Room("Suite", 6000, "WiFi, AC, TV, Mini Bar"));

        SearchService searchService = new SearchService();

        searchService.searchAvailableRooms(inventory, roomData);
    }
}