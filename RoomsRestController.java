import java.util.*;

public class RoomsRestController {
    
    private Inventory inventory;
    private Map<String, Room> roomData;

    public RoomsRestController(Inventory inventory, Map<String, Room> roomData) {
        this.inventory = inventory;
        this.roomData = roomData;
    }

    // GET /api/rooms - Get all rooms
    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> rooms = new ArrayList<>();
        for (Map.Entry<String, Room> entry : roomData.entrySet()) {
            Room room = entry.getValue();
            int available = inventory.getAvailableRooms(entry.getKey());
            rooms.add(new RoomDTO(room.getType(), room.getPrice(), 
                                room.getAmenities(), available));
        }
        return rooms;
    }

    // GET /api/rooms/{type} - Get room by type
    public RoomDTO getRoomByType(String type) {
        Room room = roomData.get(type);
        if (room != null) {
            int available = inventory.getAvailableRooms(type);
            return new RoomDTO(room.getType(), room.getPrice(), 
                             room.getAmenities(), available);
        }
        return null;
    }

    // POST /api/rooms - Add new room
    public boolean addRoom(String type, double price, String amenities, int count) {
        if (!roomData.containsKey(type)) {
            roomData.put(type, new Room(type, price, amenities));
            inventory.addRoom(type, count);
            return true;
        }
        return false;
    }

    // PUT /api/rooms/{type} - Update room
    public boolean updateRoom(String type, double price, String amenities) {
        if (roomData.containsKey(type)) {
            roomData.put(type, new Room(type, price, amenities));
            return true;
        }
        return false;
    }

    // DELETE /api/rooms/{type} - Delete room
    public boolean deleteRoom(String type) {
        if (roomData.containsKey(type)) {
            roomData.remove(type);
            return true;
        }
        return false;
    }
}

class RoomDTO {
    private String type;
    private double price;
    private String amenities;
    private int available;

    public RoomDTO(String type, double price, String amenities, int available) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
        this.available = available;
    }

    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getAmenities() { return amenities; }
    public int getAvailable() { return available; }
}
