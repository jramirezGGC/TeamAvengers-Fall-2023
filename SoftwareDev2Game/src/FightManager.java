import java.util.Scanner;

public class FightManager{

    // while loop for pHP >0 && monster in room
    // turn 1 & 2
        public static FightOutcome fight(Player player,Monster monster){
            FightOutcome fightOutcome = new FightOutcome();
            boolean inCombat = true;
            Scanner scanner = new Scanner(System.in);
           while(inCombat){
               if(monster.getHp() <= 0){
                   fightOutcome.monsterDefeated = true;
                   inCombat = false;
                   System.out.println("You have tamed " + monster.getName());
                   continue;
               }
               if(player.getHP() <= 0){
                   fightOutcome.monsterDefeated = false;
                   inCombat = false;
                   System.out.println("You have died");
                   continue;
               }
               System.out.println("You are fighting  " + monster.getName() + " it has : " + monster.getHp() + "HP");
               System.out.println("Commands are attack and run");
               String input = scanner.nextLine();
               input = input.toLowerCase();
               String[] inputParts = input.split(" ");
               String command = inputParts[0];
               System.out.println();
               switch (command){
                   case "attack" :
                       Item strongestItem = player.getStrongestWeapon();
                       if(strongestItem == null){
                           System.out.println("Invalid use try a different command");
                           continue;
                       }
                       System.out.println("You attack with " + strongestItem.getName());
                       monster.setHp(monster.getHp()- strongestItem.getEffect());
                       System.out.println("The monster attacks!");
//                       playerHP = player.getHP() - monster.getAtk();
                      int playerHP = player.setHP(player.getHP()- monster.getAtk());
                       System.out.println("Your HP : " + playerHP);

                       break;
                   case "run" :
                       inCombat = false;
                       fightOutcome.ranAway = true;
                       break;
                   default:
                       System.out.println("Dont run coward your options are 'fight' or 'run'(coward)");
                       break;
               }
           }
            fightOutcome.player = player;
            return fightOutcome;
        }
}
