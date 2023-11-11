import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapReader {
    public static Map<String, Room> loadMapFromFile(String filePath, Map<String,Item> items, Map<String, Puzzle> puzzles, Map<String,Monster> monsters) {
        Map<String,Room> rooms = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 7) { // increase if we add more parts will prob end up being 6 or 7 length
                    String roomName = parts[0].trim();
                    RoomType roomType = RoomType.valueOf(parts[1].trim());
                   // System.out.println("Roomname : " + roomName);
                    String description = parts[2].trim();
                   // System.out.println("Description" + description);
                    String[] connections = parts[3].trim().split(",");
                   // System.out.println("Connections : " + parts[2].trim());
                    String roomItemsRaw = parts[4].trim();
                    String[] roomItemsSplit = roomItemsRaw.split(",");
                    String roomPuzzlesName = parts[5].trim();
                    String[] roomPuzzleSplit = roomPuzzlesName.split(",");
                    String roomMonsterName = parts[6].trim();
                    String[] roomMonsterSplit = roomMonsterName.split(",");
                    // String[] roomVisited = parts[5].trim();
                    List<Item> roomItems = new ArrayList<>();
                    List<Puzzle> roomPuzzle = new ArrayList<>();
                    List<Monster> roomMonster = new ArrayList<>();
                    for(String itemName : roomItemsSplit){
                        Item matchingItem = items.get(itemName);
                        if(matchingItem != null){
                            roomItems.add(matchingItem);
                        }

                        for(String puzzleName : roomPuzzleSplit){
                            Puzzle matchingPuzzle = puzzles.get(puzzleName);
                            if(matchingPuzzle != null) {
                                roomPuzzle.add(matchingPuzzle);
                            }
                        }
                        for(String monsterName : roomMonsterSplit){
                            Monster matchingMonster = monsters.get(monsterName);
                            if(matchingMonster != null){
                                roomMonster.add(matchingMonster);
                            }
                        }
                    }
                    Room room = new Room(roomName, roomType,description,roomItems,roomPuzzle,roomMonster);
                    for (String connection : connections) {
                        String[] connParts = connection.trim().split("=");
                        if (connParts.length == 2) {
                            String direction = connParts[0].trim();
                            direction = direction.toLowerCase();
                            String connectedRoom = connParts[1].trim();
                            room.addConnection(direction, connectedRoom);
//                            System.out.println("con 0" + connParts[0]);
//                            System.out.println("Con 1" + connParts[1]);
                        }
                    }
                    rooms.put(roomName, room);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the map file: " + e.getMessage());
        }
        return rooms;
    }
}
