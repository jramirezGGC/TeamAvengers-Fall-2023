This is an overview of the files being read and their attributes (Subject to change)

Map.txt is read like this
(roomName:roomDesc:connections:items:puzzles:monsters) to add (visitedRoom?:SafeRoom/HealingRooms)

items.txt is read like this
(itemName:itemDesc) to add (itemBuff,itemEquipable?,itemConsumable?)

puzzles.txt is read like this
(puzzleName:puzzlePrompt:puzzleAns:puzzleHint) to add (remove from room feature)

monsters.txt is read like this
(monsterName:monsterDesc:monsterATK:monsterHP) to add (?)


Key things to look at 
If we make an addition to any text file the associated reader must include the new attribute 

EX.
 String[] parts = line.split(":");
                String monsterName = parts[0].trim();
                String monsterDesc = parts[1].trim();
                String monsterAtk = parts[2].trim();
                String monsterHP = parts[3].trim();
                Monster monster = new Monster(monsterName,monsterDesc,monsterAtk,monsterHP);

would then look like
 String[] parts = line.split(":");
                String monsterName = parts[0].trim();
                String monsterDesc = parts[1].trim();
                String monsterAtk = parts[2].trim();
                String monsterHP = parts[3].trim();
		String monsterBanter = parts[4].trim();
                Monster monster = new Monster(monsterName,monsterDesc,monsterAtk,monsterHP,monsterBanter); 

This is the case for all reading classes

Things to add : for project
Fight class : shield, attack, skil, dodge
Monster interaction
Remove puzzle after correct input is applied
Remove monster after hp <= 0
mapReader class : Safe room attributed, vitisted rooms, healing rooms
itemReader class : itemBuff,itemEquipable,itemConsumable?,consumableEffect
puzzleReader class : removeFromRoom
monsterReader class : ?

commands : jump,Analyze(inspect for monsters), save/load, start, help(display all commands), grap(pickup with an option of keep or discard), Scan(basically a modified explore), equip,consume(basically use)

Things to work on : Individual project 3
The puzzles have had an issue now that there are two puzzle in the room even if I tell the room there is only 1.
This is causing an error and making the game crash but they give the correct "solved puzzle" prompt.
The inventory for now is the equiped so any item in your inventory such as a sword is equiped automatically.



