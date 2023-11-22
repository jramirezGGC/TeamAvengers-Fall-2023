import java.util.*;


public class TextBasedGame {

    private Scanner scanner;

    public TextBasedGame() {
        scanner = new Scanner(System.in);
    }

    public Player generatePlayer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the game! This game is set in a dystopian future where you are fighting for survival.");//Gland added welcome message
        System.out.println("Please enter your name: ");
        String playerName = input.nextLine();  
        System.out.println("Please enter a description for yourself: ");//Gland added player description
        String playerDescription = input.nextLine();//inv               //equiped
        Player player = new Player(playerName,"B1/Pantry",new ArrayList<>(),new ArrayList<>(),100,new ArrayList<>(),playerDescription);
        return player;
    }

    public void playGame(Map<String,Room> rooms) {
        Player player = generatePlayer();
        System.out.println("Hello " + player.getName());
        boolean running = true;
        while (running) {
            Room room = rooms.get(player.getCurrentRoomName());
            System.out.println("Your name is : " + player.getName());
            System.out.println(player.getName()+"'s description : " + player.getDescription());
            System.out.println("HP: " + player.getHP());
            System.out.println("You are in " + room.getName());
            System.out.println("Your current HP is " + player.getHP());
            System.out.println("Items in your inventory : " + Item.getInvAsString(player.getInventory())); // logging
            System.out.println("Item selected : " + Item.getSelectedAsString(player.getSelected()));
            System.out.println("Items equipped : " + Item.getEquippedAsString(player.getEquipped()));
            System.out.println(room.getName() +"Description: " + room.getDescription());
            System.out.println("Available connections: " + room.getConnections().keySet());// logging
            System.out.println("Room Type: " + room.getRoomType());// logging
            System.out.println("Items in the room : " + Item.getInvAsString(room.getItems())); // logging
            System.out.println("Puzzles in the room : " + Puzzle.getPuzzleAsString(room.getPuzzles())); // logging error with dupe monster
            System.out.println("Puzzle desc : " + Puzzle.getPuzzleDesc(room.getPuzzles()));
            System.out.println("Monsters in the room : " + Monster.getMonsterAsString(room.getMonsters())); // logging error with dupe monster
            System.out.println();
            for (String direction : room.getConnections().keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println("Enter the direction you want to go (move : N, S, W, E), 'exit' to exit the game ");
            System.out.println("Use (help) for a list of all commands and applicable actions ");
            System.out.println();
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] inputParts = input.split(" ");
            String command = inputParts[0];
            System.out.println();
            switch (command){
                case "exit":
                    running = false;
                    System.out.println("Coward.");
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
                   System.out.println("What do you want to do? (Keep or Discard).");//Gland implemented keep and discard commands
                	 //Item itemBeingPickedUp = player.checkIfItemInInventory(selectName.toLowerCase());
                    if(itemBeingPickedUp != null){
                    //	player.addItem(itemBeingPickedUp);
                    	
                    	String input2 = scanner.nextLine();
                        input2 = input2.toLowerCase();
                        String[] input2Parts = input2.split(" ");
                        String command2 = input2Parts[0];
                        
                        switch (command2){
                            case "keep":
                            	player.addItem(itemBeingPickedUp);
                            	 System.out.println("You kept the item.");
                            	 break;
                            case "discard":
                            	
                            	 room.addItemToRoom(itemBeingPickedUp);
                            	System.out.println("You discarded the item.");
                            	 break;
                        }
//                    	input = scanner.nextLine();
//                    	 input = input.toLowerCase();
//                    	if (input == "keep") {
//    						 player.addItem(itemBeingPickedUp);
//    						 System.out.println("You kept the item.");
//    					}
//    					if (input == "discard")  {
//    						System.out.println("You discarded the item.");
//    					}	
                       
                    }else{
                        System.out.println("Item is not in room");
                        System.out.println();
                    }
                    break;
                case "discard"://need to be able to discard from inventory AND hand
                    String[] argumentsDrop = input.split(" "); // Extract the item name
                    Item itemBeingDropped = player.dropItem(argumentsDrop[1]);
                    if (itemBeingDropped != null) {
                    	
                        room.addItemToRoom(itemBeingDropped);
                   
                    	
                    } else {
                        System.out.println("Item is not in inventory or hand");
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
                    System.out.println(room.getDescription() + " " + room.getItems());
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
                case "select":
                   // String[] selectName = input.split(" "); // extract equipment Name
                	System.out.println("Which item would you like to select?" );
                	Scanner inputSelect = new Scanner(System.in);
                	String selectName = inputSelect.nextLine(); ;
                    Item itemBeingSelected = player.checkIfItemInInventory(selectName.toLowerCase()); //[1]
                    if(player.getInventory().contains(itemBeingSelected)){
                        player.getInventory().remove(itemBeingSelected);
                        System.out.println("You have selected " + itemBeingSelected.getName());
                        player.addSelected(itemBeingSelected);
                    }
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
                        System.out.println("Combat : combat(initiates fight), inspect, attack, dodge, shield, run, ignore(removes monster) (TBA:  Skill)");
                        System.out.println("Item interaction : grab, discard, analyze, consume, all of these require the item's associated name, and inventory (TBA: select)");//keep command is done
                        System.out.println("Navigation : move (N,S,E,W) not case sensitive");
                        System.out.println("Puzzle interaction : say(only command for now), jump(not yet implemented), keep(not yet implemented)");
                        System.out.println("Misc : scan(for all the rooms properties), TBA(Save,Load,Start)");
                    System.out.println();
                    break;
                case "combat":
                        System.out.println("You are now fighting the monster");
                        Monster monster = room.getMonsters().stream().findFirst().orElse(null);
                        if(monster == null){
                            System.out.println("There are no monsters in this room.");
                        }
                        FightOutcome fightOutcome = FightManager.fight(player,monster);
                        player = fightOutcome.player;
                        if(player.getHP() == 0){
                            System.out.println("You have lost. You are a loser.");
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
                    Item itemBeingConsumed = player.checkIfItemInInventory(consumeName[1].toLowerCase());//Gland changed consume command so player can consume items from inventory instead of equipped items
                    if(itemBeingConsumed != null){
                        int playerHP = player.setHP(player.getHP() + itemBeingConsumed.getEffect());
                        player.getInventory().remove(itemBeingConsumed);
                       //include check for health over 100
                        System.out.println("You have consumed : " + itemBeingConsumed);
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
        Map<String,Monster> monsters = MonsterReader.readMonstersFromFile("monsters.txt");//Gland entered monsters data in monsters.txt
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
