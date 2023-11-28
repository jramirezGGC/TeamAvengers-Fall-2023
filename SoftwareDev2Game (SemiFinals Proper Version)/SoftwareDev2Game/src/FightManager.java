
import java.util.List;
import java.util.Scanner;

public class FightManager {

	public static FightOutcome fight(Player player, Monster monster) {
	    FightOutcome fightOutcome = new FightOutcome();
	    boolean inCombat = true;
	    @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

	    while (inCombat) {
	        if (monster.getHp() <= 0) {
	            fightOutcome.monsterDefeated = true;
	            inCombat = false;
	            System.out.println("You have tamed " + monster.getName());
	            continue;
	        }
	        if (player.getHP() <= 0) {
	            fightOutcome.monsterDefeated = false;
	            inCombat = false;
	            System.out.println("You have died");
	            continue;
	        }
	        System.out.println("You are fighting  " + monster.getName() + " it has : " + monster.getHp() + "HP");
	        System.out.println("Commands are attack, skill, shield, and run");
	        String input = scanner.nextLine();
	        input = input.toLowerCase();
	        String[] inputParts = input.split(" ");
	        String command = inputParts[0];
	        System.out.println();

	        switch (command) {
	            case "attack":
	                Item playerWeapon = player.getWeapon();
	                if (playerWeapon == null) {
	                    System.out.println("Invalid use, try a different command");
	                    continue;
	                }

	                System.out.println("You attack with " + playerWeapon.getName());
	                handleWeaponEffects(player, playerWeapon, monster);
	                

	                // Check if the monster is still alive
	                if (monster.getHp() <= 0) {
	                    System.out.println("You have tamed " + monster.getName());
	                    inCombat = false;
	                    continue;
	                }

	                System.out.println("The monster attacks!");
	                int playerHP = player.setHP(player.getHP() - monster.getAtk());
	                System.out.println("Your HP: " + playerHP);
	                break;
                case "analyze":
                    String monsterBeingExamined = monster.getName();
                    if (monsterBeingExamined != null) {
                        System.out.println("Monster Desc: " + monster.getDesc() + " MonsterHP : " + monster.getHp() + " MonsterDMG : " + monster.getAtk());
                        break;
                    }
                    break;
                case "run":
                    inCombat = false;
                    fightOutcome.ranAway = true;
                    break;
                case "skill":
                    // Display item effects when the user uses the "skill" command
                    displayItemEffects(player, player.getEquipped());
                    break;
                default:
                    System.out.println("Don't run coward, your options are 'fight' or 'run'(coward)");
                    break;
            }
            
        }

        fightOutcome.player = player;
        return fightOutcome;
    }

    private static int handleBleedEffect(Player player, Monster monster) {
        if (monster.isBleeding()) {
            System.out.println("Monster is bleeding!");
            int damagePerTick = 2;
            int totalBleedDamage = damagePerTick * monster.getBleedTicks();

            monster.setHp(monster.getHp() - totalBleedDamage);
            System.out.println("Bleed damage: " + totalBleedDamage);
            System.out.println("After bleed effect, monster HP: " + monster.getHp());

            monster.setBleedTicks(0); // Reset bleed ticks
            monster.setBleeding(false);

            return totalBleedDamage;
        }

        return 0;
    }
    
    private static void handleItemEffect(Player player, Item item, Monster monster) {
        if (item instanceof Weapon) {
            handleWeaponEffects(player, (Weapon) item, monster);
        } else if (item instanceof ArmorVest) {
            handleArmorVestEffect(player, (ArmorVest) item, monster);
        }
    }
    
    private static void handleWeaponEffects(Player player, Item playerWeapon, Monster monster) {
        Item weapon = player.getWeapon();
        if (weapon == null) {
            System.out.println("Invalid use, try a different command");
            return;
        }
        
        
        
        // Apply specific weapon effects
        if (weapon.getName().equalsIgnoreCase("Knife")) {
        	handleBleedEffect(player, monster);
            applyBleedEffect(player, monster);
        } else if (weapon.getName().equalsIgnoreCase("Handgun")) {
            useHandgunActiveSkill(player, monster, weapon);
        } else if (weapon.getName().equalsIgnoreCase("Bat")) {
        	applyStunEffect(player, monster, weapon);
        } else if (weapon.getName().equalsIgnoreCase("Crowbar")) {
        	handleCrowbarEffect(player, monster);
        } else if (weapon.getName().equalsIgnoreCase("Chainsaw")) {
        	handleChainsawEffect(player, monster);
        	applyBleedEffect(player, monster);
        } else if (weapon.getName().equalsIgnoreCase("Bow")) {
        	handleBowEffect(player, monster);
        } else if (weapon instanceof Item) {
        	handleItemEffect(player, weapon, monster);
        }
    }
    
    

    private static void applyBleedEffect(Player player, Monster monster) {
        System.out.println("Applying Bleed effect!");
        
        // Check if the monster is already bleeding
        if (!monster.isBleeding()) {
            monster.setBleeding(true);
            monster.setBleedTicks(1); // Start with 1 tick
        } else {
            int currentTicks = monster.getBleedTicks();
            monster.setBleedTicks(currentTicks + 1);
        }

        System.out.println("Total Bleed Damage Applied: " + monster.getBleedTicks() * 2);
    }

    private static void useHandgunActiveSkill(Player player, Monster monster, Item weapon) {
        if (!player.isHandgunSkillUsed()) {
            System.out.println("Using Handgun's active skill: 'Rapid Fire'!");
            int shots = 3;
            int damagePerShot = 18;

            for (int i = 0; i < shots; i++) {
                monster.setHp(monster.getHp() - damagePerShot);
                System.out.println("Shot " + (i + 1) + ": " + damagePerShot + " damage");
            }

            // Mark the skill as used
            player.setHandgunSkillUsed(true);

            // Display updated monster's HP after all shots
            System.out.println("After Rapid Fire, monster HP: " + monster.getHp());
        } else {
            System.out.println("Handgun's active skill already used. Continuing with regular baseline damage.");

            // Continue with regular baseline damage
            int baseDamage = player.getWeapon().getEffect();
            monster.setHp(monster.getHp() - baseDamage);

            // Display updated monster's HP
            System.out.println("After regular attack, monster HP: " + monster.getHp());
        }
    }

    private static void displayItemEffects(Player player, List<Item> equippedItems) {
        System.out.println("Displaying item effects:");
        for (Item item : equippedItems) {
            System.out.println(item.getName() + ": " + item.getDesc());
        }
    }
    
    private static void applyStunEffect(Player player, Monster monster, Item item) {
        if (!monster.isStunApplied()) {
            System.out.println("Applying Stun effect from the Bat!");
            monster.setStunned(true);
            monster.setHp(monster.getHp() - item.getStunDamage());
            System.out.println("Stun damage: " + item.getStunDamage());
            monster.setStunApplied(true);
        } else {
            System.out.println("Stun effect already applied. The monster skips its turn.");
        }
    }
    
    private static void handleCrowbarEffect(Player player, Monster monster) {
        Item crowbar = player.getWeapon();

        int baseDamage = crowbar.getEffect();

        // Check if monster's HP is below 30%
        if (monster.getHp() <= 0.3 * monster.getInitialHp()) {
            System.out.println("Dealing additional damage to the weakened monster!");
            int additionalDamage = 2;
            baseDamage += additionalDamage;
            monster.setHp(monster.getHp() - additionalDamage);
        }

        monster.setHp(monster.getHp() - baseDamage);

        // Display updated monster's HP
        System.out.println("After " + crowbar.getName() + " effect, monster HP: " + monster.getHp());
    }
    
    private static void handleChainsawEffect(Player player, Monster monster) {
        Item chainsaw = player.getWeapon();

        int baseDamage = chainsaw.getEffect();
        
        // Apply the passive skill 'Bleed'
        handleBleedEffect(player, monster);

        monster.setHp(monster.getHp() - baseDamage);

        // Display updated monster's HP
        System.out.println("After " + chainsaw.getName() + " effect, monster HP: " + monster.getHp());
    }
    
    private static void handleBowEffect(Player player, Monster monster) {
        Item bow = player.getWeapon();
        
        int baseDamage = bow.getEffect();
        
        // Apply the passive skill 'Pierce'
        applyPierceEffect(player, monster);

        monster.setHp(monster.getHp() - baseDamage);

        // Display updated monster's HP
        System.out.println("After Pierce effect, monster HP: " + monster.getHp());
    }
    
    private static void applyPierceEffect(Player player, Monster monster) {
        System.out.println("Applying Pierce effect!");

        // Check if the monster is already wounded
        if (!monster.isWounded()) {
            System.out.println("The monster is now wounded, taking 1.5x more damage!");
            monster.setWounded(true);
        }

        // Calculate the additional damage when the monster is wounded
        int baseDamage = player.getWeapon().getEffect();
        int additionalDamage = (int) (baseDamage * 0.5); // 1.5x more damage

        monster.setHp(monster.getHp() - additionalDamage);
    }
    
    private static void handleArmorVestEffect(Player player, ArmorVest armorVest, Monster monster) {
        // Check if the active skill is used
        if (!armorVest.isActiveSkillUsed()) {
            System.out.println("Applying Armor Vest effect: additional HP + " + armorVest.getAdditionalHP());
            player.setHP(player.getHP() + armorVest.getAdditionalHP());

            // Use the active skill of Armor Vest (e.g., reduce damage received by 20% for 2 turns)
            armorVest.useActiveSkill(player);

            // Display updated player's HP
            System.out.println("After Armor Vest effect, your HP: " + player.getHP());
        } else {
            System.out.println("Armor Vest effect already applied. Cannot use it again.");
        }
    }
    
    public static void useActiveSkill(Player player, ArmorVest armorVest) {
        if (!armorVest.isActiveSkillUsed()) {
            System.out.println("Using Armor Vest's active skill: 'Fortified'!");
            
            // Reduce damage received by 20% for 2 turns
            player.setArmorFortified(true);
            player.setFortifiedTurns(2); // The effect lasts for 2 turns
            
            // Set the activeSkillUsed to true after using the skill
            armorVest.setActiveSkillUsed(true);
        } else {
            System.out.println("Armor Vest's active skill already used. Cannot use it again.");
        }
    }
}
