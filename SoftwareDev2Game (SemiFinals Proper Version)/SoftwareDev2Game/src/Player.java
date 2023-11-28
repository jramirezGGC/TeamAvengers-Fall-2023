
import java.util.List;

public class Player {
    private String name;
    private String currentRoomName;
    private String prevRoomName;
    private List<Item> inventory;
    private int HP;
    private boolean handgunSkillUsed;
    private boolean armorFortified = false;
    private int fortifiedTurns = 0;

    //private int maxHp;
    //private int DMG;

    private List<Item> weapons;
    private List<Item> equipped;
    private int maxHP;

    public Player(String name, String currentRoomName, List<Item> inventory,List<Item> equipped, Integer HP) { // add DMG
        this.name = name;
        this.HP = HP;
        this.currentRoomName = currentRoomName;
        this.prevRoomName = null;
        this.inventory = inventory;
        this.equipped = equipped;
        this.maxHP = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //worked on maxHP
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxHP() {
        return maxHP;
    }
    public int calculateAndSetHP(int newHP) {
        int difference = newHP - HP;

        if (newHP > maxHP) {
            HP = maxHP;
        } else {
            HP = newHP;
        }

        return difference;
    }

    public int getHP(){
        return HP;
    }
    public int setHP(int HP){
       // this.HP = HP;
        //return HP;
        int difference = calculateAndSetHP(HP);
        return HP - difference;
    }

    public String getCurrentRoomName() {
        return currentRoomName;
    }

    public String getPrevRoomName(){
        return prevRoomName;
    }


    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public String moveToRoom(String roomName){
        if(roomName == null){
            System.out.println("Invalid room");
            return null;
        }
        this.prevRoomName = currentRoomName;
        this.currentRoomName = roomName;
        return roomName;
    }

    public Item dropItem(String itemName) {
        Item item = checkIfItemInInventory(itemName);
        if(item == null){
            return null;
        }else{
            inventory.remove(item);
//            inventory.removeIf(i->i.getName().toLowerCase().equals(itemName));
            return item;
        }
    }

    public Item checkIfItemInInventory(String itemName){
        Item item = inventory.stream().filter(i->i.getName().toLowerCase().equals(itemName.toLowerCase())).findFirst().orElse(null);
        return item;
    }

    public Item checkIfItemEquipped(String itemName){
        Item item = equipped.stream().filter(item1 -> item1.getName().toLowerCase().equals(itemName.toLowerCase())).findFirst().orElse(null);
        return item;
    }

//    public Item getStrongestWeapon(){
//        return inventory.stream().filter(w->w.getItemType().equals(ItemType.WEAPON)).max(Comparator.comparing(Item::getEffect)).orElse(null);
//    }

public Item getWeapon(){
        return equipped.stream().filter(Item::isWeapon).findFirst().orElse(null);
}
    public void addItem(Item item){
        inventory.add(item);
    }

    public void putEquipBack(Item item){
        inventory.add(item);
    }
    public void addEquipment(Item item){
        equipped.add(item);
    }
    public List<Item> getEquipped() {
        return equipped;
    }

    public void setEquipped(List<Item> equipped) {
        this.equipped = equipped;
    }

	/**
	 * @return the weapons
	 */
	public List<Item> getWeapons() {
		return weapons;
	}

	/**
	 * @param weapons the weapons to set
	 */
	public void setWeapons(List<Item> weapons) {
		this.weapons = weapons;
	}

	/**
	 * @return the handgunSkillUsed
	 */
	public boolean isHandgunSkillUsed() {
		return handgunSkillUsed;
	}

	/**
	 * @param handgunSkillUsed the handgunSkillUsed to set
	 */
	public void setHandgunSkillUsed(boolean handgunSkillUsed) {
		this.handgunSkillUsed = handgunSkillUsed;
	}

	/**
	 * @return the armorFortified
	 */
	public boolean isArmorFortified() {
		return armorFortified;
	}

	/**
	 * @param armorFortified the armorFortified to set
	 */
	public void setArmorFortified(boolean armorFortified) {
		this.armorFortified = armorFortified;
	}

	/**
	 * @return the fortifiedTurns
	 */
	public int getFortifiedTurns() {
		return fortifiedTurns;
	}

	/**
	 * @param fortifiedTurns the fortifiedTurns to set
	 */
	public void setFortifiedTurns(int fortifiedTurns) {
		this.fortifiedTurns = fortifiedTurns;
	}

	/**
	 * @return the shieldUsed
	 */
}
