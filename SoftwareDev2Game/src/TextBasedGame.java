import java.util.*;


public class TextBasedGame {

    private Scanner scanner;

    public TextBasedGame() {
        scanner = new Scanner(System.in);
    }

    public Player generatePlayer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = input.nextLine();                       //inv               //equiped
        Player player = new Player(playerName,"Room1",new ArrayList<>(),new ArrayList<>(),100);
        return player;
    }

    public void playGame(Map<String,Room> rooms) {
        Player player = generatePlayer();
        System.out.println("Hello " + player.getName());
        boolean running = true;
        while (running) {
            Room room = rooms.get(player.getCurrentRoomName());
            System.out.println("You are in " + room.getName());
            System.out.println("Your current HP is " + player.getHP());
            System.out.println("Items in your inventory : " + Item.getInvAsString(player.getInventory())); // logging
            System.out.println("Items equipped : " + Item.getEquippedAsString(player.getEquipped()));
            System.out.println("Description: " + room.getDescription());
            System.out.println("Available connections: " + room.getConnections().keySet());// logging
            System.out.println("Room Type: " + room.getRoomType());// logging
            System.out.println("Items in the room : " + Item.getInvAsString(room.getItems())); // logging
            System.out.println("Puzzles in the room : " + Puzzle.getPuzzleAsString(room.getPuzzles())); // logging error with dupe monster
            System.out.println("Monsters in the room : " + Monster.getMonsterAsString(room.getMonsters())); // logging error with dupe monster
            System.out.println();
            for (String direction : room.getConnections().keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println("Enter the direction you want to go (move : N, S, W, E), 'quit' to exit or : ");
            System.out.println("For items uses these commands(drop, pickup, inspect : itemName)");
            System.out.println();
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] inputParts = input.split(" ");
            String command = inputParts[0];
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
                case "pickup":
                    String[] argumentsPickup = input.split(" "); // Extract the item name
                    Item itemBeingPickedUp = room.pickUpItem(argumentsPickup[1]);
                    if(itemBeingPickedUp != null){
                        player.addItem(itemBeingPickedUp);
                    }else{
                        System.out.println("Item is not in room");
                        System.out.println();
                    }
                    break;
                case "drop":
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
                case "examine":
                    String[] examineMonster = input.split(" ");
//                    Monster monsterBeingExamined = room.getMonsters().stream().filter(monster -> examineMonster[1]
//                            .equals(monster.getName().toLowerCase())).findAny().orElse(null);  CODE FOR MULTIPLES
                    Monster monsterBeingExamined = room.getMonsters().stream().findFirst().orElse(null);
                    if(room.getMonsters().contains(monsterBeingExamined)) {
                        System.out.println(monsterBeingExamined.getDesc());
                    }
                    break;
                case "explore":
                    System.out.println(room.getDescription() + " " + room.getItems() + " " + room.getPuzzles());
                    break;
                case "say":
                    // need to get puzzles in room (make new method for puzzle)
                    if(room.getPuzzles() == null || room.getPuzzles().isEmpty()){
                        System.out.println("There is no puzzle in this room");
                        break;
                    }
                    final String userInput = input.trim().toLowerCase();
                    room.getPuzzles().stream().forEach(puzzle -> {
                        if(userInput.equals(puzzle.getAns().toLowerCase())){
                            System.out.println("This puzzle has been solved!");
                            room.getPuzzles().remove(puzzle);

                        }else{
                            System.out.println("You answered wrong :" + userInput);
                        }
                    });
                    break;
                case "inventory":
                    if(input.equals("inventory")){
                        System.out.println("Items in your backpack : " + Item.getInvAsString(player.getInventory()));
                    }
                    break;
                case "equip":
                    if(input.equals("equip")){
                        String[] equipmentName = input.split(" "); // extract equipment Name
                        Item itemBeingEquipped = player.checkIfItemInInventory(equipmentName[1].toLowerCase());
                        if(player.getInventory().contains(itemBeingEquipped)){
                            System.out.println("this works");
                            player.addEquipment(itemBeingEquipped);
                        }
                    }
                    break;
                case "commands":
                    if(input.equals("commands")){
                        System.out.println("commands available are : ");
                        System.out.println("Combat : fight(initiates combat), attack, run, ignore(removes monster)");
                        System.out.println("Item interaction : pickup, drop, inspect, consume, all of these require the item's associated name");
                        System.out.println("Navigation : move (N,S,E,W) not case sensitive");
                        System.out.println("Puzzle interaction : say(only command for now)");
                    }
                    break;
                case "fight":
                    if(input.equals("fight")){
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
                    Item itemBeingConsumed = player.checkIfItemInInventory(consumeName[1].toLowerCase());
                    if(itemBeingConsumed != null){
                        int playerHP = player.setHP(player.getHP() + itemBeingConsumed.getEffect());
                        player.getInventory().remove(itemBeingConsumed);
                    }
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
                    System.out.println();
                    break;
            }
        }
    }
    public static void main(String[] args) {
        TextBasedGame game = new TextBasedGame();
        Map<String,Monster> monsters = MonsterReader.readMonstersFromFile("monsters.txt");
        Map<String,Puzzle> puzzles = PuzzleReader.readPuzzlesFromFile("puzzles.txt");
        Map<String,Item> items = ItemReader.readItemsFromFile("items.txt");
//        items.entrySet().forEach(i->{
//            System.out.println("This item is  " + i.getKey());
//        });
        Map<String,Room> rooms = MapReader.loadMapFromFile("map.txt",items,puzzles,monsters);
//        rooms.entrySet().forEach(r->{
//            System.out.println("This Room is  " + r.getValue());
//        });
        game.playGame(rooms);
    }
}
