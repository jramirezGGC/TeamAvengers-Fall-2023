
import java.util.*;

class Room {
    private String name;
    private String description;
    private Map<String, String> connections; // Direction -> Connected Room
    private List<Item> items;
    private List<Puzzle> puzzles;

    private List<Monster> monsters;
    private RoomType roomType;
    public Room(String name,RoomType roomType,String description, List<Item> items, List<Puzzle> puzzles, List<Monster> monsters) {
        this.name = name;
        this.roomType = roomType;
        this.description = description;
        this.connections = new HashMap<>();
        this.items = items;
        this.puzzles = puzzles;
        this.monsters = monsters;
    }
    public String getName() {
        return name;
    }
    public RoomType getRoomType(){
        return roomType;
    }
    public String getDescription() {
        return description;
    }

    public Map<String, String> getConnections() {
        return connections;
    }

    public List<Item> getItems() {
        return items;
    }


    public void addConnection(String direction, String connectedRoom) {
        connections.put(direction, connectedRoom);
    }

    public Item pickUpItem(String itemName) {
        Item item = checkIfItemInRoom(itemName);
        if(item == null){
            return null;
        }else{
            items.remove(item);
            return item;
        }
    }

    public Item checkIfItemInRoom(String itemName){
        Item item = items.stream().filter(i->i.getName().toLowerCase().equals(itemName.toLowerCase())).findFirst().orElse(null);
        return item;
    }

    public List<Puzzle> getPuzzles(){
        return puzzles;
    }

    public List<Monster> getMonsters(){
        return monsters;
    }

    public Puzzle checkIfPuzzleInRoom(String puzzleName){
        Puzzle puzzle = puzzles.stream().filter(p->p.getName().toLowerCase().equals(puzzleName.toLowerCase())).findFirst().orElse(null);
        return puzzle;
    }

    public Monster checkIfMonsterInRoom(String monsterName){
        Monster monster = monsters.stream().filter(m->m.getName().toLowerCase().equals(monsterName.toLowerCase())).findFirst().orElse(null);
        return monster;
    }



    public void addItemToRoom(Item item){
        items.add(item);
    }

    public String getConnectedRoomName(String direction){
        return connections.get(direction);
    }

    public void addPuzzlesToRoom(Puzzle puzzleName){
        puzzles.add(puzzleName);
    }

    public String puzzleAns(Puzzle puzzleAns){
        return Puzzle.getPuzzleAns(puzzles);
    }


    public void removeMonster(String monsterName){
        this.monsters.removeIf(m->m.getName().toLowerCase().equals(monsterName.toLowerCase()));
    }
    public void removePuzzle(String puzzleName){
        this.puzzles.removeIf(p->p.getName().toLowerCase().equals(puzzleName.toLowerCase()));
    }
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", connections=" + connections +
                ", items=" + items +
                ", puzzles=" + puzzles +
                '}';
    }
    // make enum logic here
}
