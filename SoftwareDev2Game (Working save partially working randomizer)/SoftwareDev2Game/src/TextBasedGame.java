

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class TextBasedGame{

    private Scanner scanner;

    public TextBasedGame() {
        scanner = new Scanner(System.in);
    }

    public Player generatePlayer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = input.nextLine();                                           //inv               //equiped
        Player player = new Player(playerName,"B1/Pantry",new ArrayList<>(),new ArrayList<>(),100);
        return player;
    }

    public void playGame(Map<String,Room> rooms) {
        Player player = generatePlayer();
        System.out.println("Hello " + player.getName());
        boolean running = true;
        while (running) {
            Room room = rooms.get(player.getCurrentRoomName());
            System.out.println("Your name is : " + player.getName()); // Player name tracked
            System.out.println("HP: " + player.getHP()); // player HP
            System.out.println("Items in your inventory : " + Item.getInvAsString(player.getInventory())); // logging items in inventory
            System.out.println("Items equipped : " + Item.getEquippedAsString(player.getEquipped())); // logging items equipped
            System.out.println("You are in " + room.getName()); // logging current location
            System.out.println(room.getName() +"Description: " + room.getDescription()); // logging room desc & name
            System.out.println("Room Type: " + room.getRoomType());// logging room type
            System.out.println("Available connections: " + room.getConnections().keySet());// logging avalible connections
            System.out.println("Items in the room : " + Item.getInvAsString(room.getItems())); // logging items in room
            System.out.println("Puzzles in the room : " + Puzzle.getPuzzleAsString(room.getPuzzles())); // logging puzzles in room
            System.out.println("Puzzle desc : " + Puzzle.getPuzzleDesc(room.getPuzzles())); // logging puzzles in room
            System.out.println("Monsters in the room : " + Monster.getMonsterAsString(room.getMonsters())); // logging monsters in room
            System.out.println();
            for (String direction : room.getConnections().keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println("Enter the direction you want to go (move : N, S, W, E), 'quit' to exit ");
            System.out.println("Use (help) for a list of all commands and applicable actions ");
            System.out.println();
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] inputParts = input.split(" ");
            String command = inputParts[0];
            Map<String, String> gameStatus = null;
            System.out.println();
            switch (command){
                case "quit":
                    running = false;
                    System.out.println("Coward");
                    break;
                case "move":
                    String[] movement = input.split(" ");
                    String roomName = room.getConnectedRoomName(movement[1]);
                    System.out.println(roomName);
                    if(roomName != null){
                        player.moveToRoom(roomName);
                    }else {
                        System.out.println("You can't go that way.");
                        System.out.println();
                    }
                    break;
                case "grab":
                    String[] argumentsPickup = input.split(" "); // Extract the item name
                    Item itemBeingPickedUp = room.pickUpItem(argumentsPickup[1]);
                    if(itemBeingPickedUp != null){
                        player.addItem(itemBeingPickedUp);
                    }else{
                        System.out.println("Item is not in room");
                        System.out.println();
                    }
                    break;
                case "discard":
                    String[] argumentsDrop = input.split(" "); // Extract the item name
                    Item itemBeingDropped = player.dropItem(argumentsDrop[1]);
                    if (itemBeingDropped != null) {
                        room.addItemToRoom(itemBeingDropped);
                    } else {
                        System.out.println("Item is not in inventory");
                        System.out.println();
                    }
                    break;
                case "inspect":
                    String[] inspectItems = input.split(" ");
                    Item itemInRoom = room.checkIfItemInRoom(inspectItems[1]);
                    Item itemInInventory = player.checkIfItemInInventory(inspectItems[1]);
                    if(itemInRoom == null && itemInInventory == null){
                        System.out.println("This is not in your inventory or not in the room");
                        break;
                    }
                    if(itemInRoom != null){
                        System.out.println("Item Desc: " + itemInRoom);
                        break;
                    }
                    if(itemInInventory != null){
                        System.out.println("Item Desc " + itemInInventory);
                        break;
                    }
                    break;
                case "analyze":
                    String[] examineMonster = input.split(" ");
//                    Monster monsterBeingExamined = room.getMonsters().stream().filter(monster -> examineMonster[1]
//                            .equals(monster.getName().toLowerCase())).findAny().orElse(null);  CODE FOR MULTIPLES
                    Monster monsterBeingExamined = room.getMonsters().stream().findFirst().orElse(null);
                    if(room.getMonsters().contains(monsterBeingExamined)) {
                        System.out.println("Monster Desc: " + monsterBeingExamined.getDesc() + "MonsterHP : " + monsterBeingExamined.getHp() + "MonsterDMG : " + monsterBeingExamined.getAtk());
                    }
                    break;
                case "scan":
                    System.out.println(room.getMonsters());
                    System.out.println(room.getItems());
                    System.out.println(room.getPuzzles());
                    break;
                case "say":
                    // need to get puzzles in room (make new method for puzzle)
                    if(room.getPuzzles() == null || room.getPuzzles().isEmpty()){
                        System.out.println("There is no puzzle in this room");
                        break;
                    }
                    final String userInput = input.trim().toLowerCase();
                    List<Puzzle> roomPuzzles = room.getPuzzles();
                    List<String> puzzleSolved = new ArrayList<>();
                    roomPuzzles.stream().forEach(puzzle -> {
                        String puzzleAns = puzzle.getAns().toLowerCase();
                        if(userInput.equals(puzzleAns)){
                            System.out.println("This puzzle has been solved!");
                            puzzleSolved.add(puzzle.getName());
                        }else{
                            System.out.println("You answered wrong :" + userInput);
                        }
                    });
                    puzzleSolved.stream().forEach(puzzle -> {
                        room.getPuzzles().removeIf(p -> p.getName().equals(puzzle));
                    });
                    break;
                case "hint":
                    String[] puzzleHint = input.split(" ");
                    Puzzle hintPuzzle = room.checkIfPuzzleInRoom(puzzleHint[1]);
                    if(hintPuzzle != null){
                        System.out.println("Hint : " + hintPuzzle.getHint());
                    }else{
                        System.out.println("There is no puzzle of that nature in this room");
                        break;
                    }
                    break;
                case "inventory":
                        System.out.println("Items in your backpack : " + Item.getInvAsString(player.getInventory()));

                    break;
                case "equip":
                    String[] equipmentName = input.split(" "); // extract equipment Name
                    Item itemBeingEquipped = player.checkIfItemInInventory(equipmentName[1].toLowerCase());
                    if(player.getInventory().contains(itemBeingEquipped)){
                        player.getInventory().remove(itemBeingEquipped);
                        System.out.println("You have equipped " + itemBeingEquipped.getName());
                        player.addEquipment(itemBeingEquipped);
                    }
                    break;
                case "help":
                        System.out.println("commands available are : ");
                        System.out.println("Combat : fight(initiates combat), inspect, attack, run, ignore(removes monster) (TBA: Dodge, Skill, Shield)");
                        System.out.println("Item interaction : grab, discard, analyze, consume, all of these require the item's associated name, and inventory (TBA: select, keep");
                        System.out.println("Navigation : move (N,S,E,W) not case sensitive");
                        System.out.println("Puzzle interaction : say(only command for now), jump(not yet implemented), keep(not yet implemented)");
                        System.out.println("Misc : scan(for all the rooms properties), TBA(Save,Load,Start)");
                    System.out.println();
                    break;
                case "combat":
                        System.out.println("You are now fighting monster");
                        Monster monster = room.getMonsters().stream().findFirst().orElse(null);
                        if(monster == null){
                            System.out.println("There are no monsters in this room.");
                        }
                        FightOutcome fightOutcome = FightManager.fight(player,monster);
                        player = fightOutcome.player;
                        if(player.getHP() == 0){
                            System.out.println("You have lost you are a loser");
                            running = false;
                            break;
                        }
                        if(fightOutcome.ranAway){
                            player.moveToRoom(player.getPrevRoomName());
                        }
                        if(fightOutcome.monsterDefeated){
                            room.removeMonster(monster.getName());
                    }
                    break;
                case "ignore" :
                    String[] monsterName = input.split(" ");
                    if(monsterName.length != 2){
                        System.out.println("you must type the monsters name");
                        continue;
                    }
                    Monster monsterInRoom = room.checkIfMonsterInRoom(monsterName[1]);
                    if(monsterInRoom != null){
                        room.removeMonster(monsterInRoom.getName());
                    }
                    break;
                case "consume" :
                    String[] consumeName = input.split(" ");
                    if(consumeName.length != 2){
                        System.out.println("You must type the consumables name");
                        continue;
                    }
                    Item itemBeingConsumed = player.checkIfItemEquipped(consumeName[1].toLowerCase());
                    if(itemBeingConsumed != null){
                        int playerHP = player.setHP(player.getHP() + itemBeingConsumed.getEffect());
                        player.getEquipped().remove(itemBeingConsumed);
                        System.out.println("You have consumed : " + itemBeingConsumed);
                    }
                    break;
                case "save":
//                    MapReader.saveGameToFile(rooms,"saved_game.txt");
//                    System.out.println("Game has been saved");
                    saveGame(player,rooms);
//                    player.savePlayerSession("playerData.txt");
                    System.out.println("Saved game");
                    break;
                case "load":
//                    rooms = MapReader.loadGameFromFile("saved_game.txt");
//                    rooms = loadGame(); working version is this line
                    LoadedGameData loadedGameData = loadGame();
                    if (loadedGameData != null) {
                        player = loadedGameData.getLoadedPlayer();
                        rooms = loadedGameData.getLoadedRooms();
                    }
                    System.out.println("Game loaded successfully");
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
                    System.out.println();
                    break;
            }
        }
    }
    public void saveGame(Player player, Map<String, Room> rooms) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saved_game1.txt"))) {
            oos.writeObject(player);
            oos.writeObject(rooms);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

//    public Map<String, Room> loadGame() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saved_game1.txt"))) {
//            return (Map<String, Room>) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.err.println("Error loading game: " + e.getMessage());
//        }
//        return null;
//    }

    private LoadedGameData loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saved_game1.txt"))) {
            // Load player data
            Player loadedPlayer = (Player) ois.readObject();
            // Load rooms map
            Map<String, Room> loadedRooms = (Map<String, Room>) ois.readObject();
            System.out.println("Game loaded successfully!");
            // Return a custom object containing both the player and rooms
            return new LoadedGameData(loadedPlayer, loadedRooms);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading game: " + e.getMessage());
        }

        return null;
    }


    public static void main(String[] args) {
        TextBasedGame game = new TextBasedGame();
        Map<String,Monster> monsters = MonsterReader.readMonstersFromFile("monsters.txt");
        Map<String,Puzzle> puzzles = PuzzleReader.readPuzzlesFromFile("puzzles.txt");
        Map<String,Item> items = ItemReader.readItemsFromFile("items.txt");
        Map<String,Room> rooms = MapReader.loadMapFromFile("map.txt",items,puzzles,monsters);

//        MapReader.saveGameToFile(rooms,"saved_game.txt");
//        Map<String, Room> loadedRooms = MapReader.loadGameFromFile("saved_game.txt", items, puzzles, monsters);
//        game.playGame(loadedRooms);

        game.playGame(rooms);
    }
}
