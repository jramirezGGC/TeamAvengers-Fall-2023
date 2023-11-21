import java.util.Scanner;

public class FightManager{

    // while loop for pHP >0 && monster in room
    // turn 1 & 2
        public static FightOutcome fight(Player player,Monster monster){
            FightOutcome fightOutcome = new FightOutcome();
            boolean inCombat = true;
            int remainingDodgeAttempts = 2;
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
               System.out.println("Commands are attack, dodge, shield, skill, and run");
               String input = scanner.nextLine();
               input = input.toLowerCase();
               String[] inputParts = input.split(" ");
               String command = inputParts[0];
               System.out.println();
               switch (command){
                   case "attack" :
                       Item playerWeapon = player.getWeapon();
                       if(playerWeapon == null){
                           System.out.println("Invalid use try a different command");
                           continue;
                       }
                       System.out.println("You attack with " + playerWeapon.getName());
                       monster.setHp(monster.getHp()- playerWeapon.getEffect());
                       System.out.println("The monster attacks!");
//                       playerHP = player.getHP() - monster.getAtk();
                      int playerHP = player.setHP(player.getHP()- monster.getAtk());
                       System.out.println("Your HP : " + playerHP);

                       break;
                   case "analyze":
                       String[] examineMonster = input.split(" ");
//                    Monster monsterBeingExamined = room.getMonsters().stream().filter(monster -> examineMonster[1]
//                            .equals(monster.getName().toLowerCase())).findAny().orElse(null);  CODE FOR MULTIPLES
                       String monsterBeingExamined = monster.getName();
                       if(monsterBeingExamined != null) {
                           System.out.println("Monster Desc: " + monster.getDesc() + "MonsterHP : " + monster.getHp() + "MonsterDMG : " + monster.getAtk());
                           break;
                       }
                       break;
                   case "run" :
                       inCombat = false;
                       fightOutcome.ranAway = true;
                       break;
                   case "dodge"://Gland added dodge command
                	   System.out.println("The monster attacks!");
                	   if (remainingDodgeAttempts > 0) {
                           System.out.println("You dodged the attack!");
                           remainingDodgeAttempts--;
                       } else {
                           System.out.println("You couldn't dodge, you used all your dodge attempts!");
                           playerHP = player.setHP(player.getHP()- monster.getAtk());
                           System.out.println("Your HP : " + playerHP);
                       }
                       break;
//                   case "skill":
//                       break;
//                   case "Shield":
//                       break;
                   default:
                       System.out.println("Are you just gonna let the monster attack you? Fight back! (or run like a coward)");
                       break;
               }
           }
            fightOutcome.player = player;
            return fightOutcome;
        }
}
