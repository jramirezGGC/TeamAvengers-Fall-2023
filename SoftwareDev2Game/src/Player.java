import java.util.Comparator;
import java.util.List;

public class Player {
	private String name;
	private String description;
	private String currentRoomName;
	private String prevRoomName;
	private List<Item> inventory;
	private List<Item> equipped;
	private List<Item> selected;
	private int HP;
	private int DMG;

	private List<Item> weapons;


	public Player(String name, String currentRoomName, List<Item> inventory,List<Item> equipped, Integer HP, List<Item> selected,String description) { // add DMG
		this.name = name;
		this.HP = HP;
		this.currentRoomName = currentRoomName;
		this.prevRoomName = null;
		this.inventory = inventory;
		this.equipped = equipped;
		this.selected = selected;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getHP(){
		return HP;
	}
	public int setHP(int HP){
		this.HP = HP;
		return HP;
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
		Item itemSelected = checkIfItemInSelected(itemName);
		Item itemEquipped = checkIfItemEquipped(itemName);
		if(item == null && itemSelected == null && itemEquipped == null){
			return null;

		}else{
			inventory.remove(item);
			selected.remove(item);
			equipped.remove(item);
			//            inventory.removeIf(i->i.getName().toLowerCase().equals(itemName));
			return item;
		}
		//        if(itemSelected == null){
		//            return null;
		//        }else{
		//            selected.remove(item);
		//            return item;
		//        }
		//        if(itemEquipped == null){
		//            return null;
		//        }else{
		//            equipped.remove(item);
		//            return item;
		//        }


	}

	public Item checkIfItemInInventory(String itemName){
		Item item = inventory.stream().filter(i->i.getName().toLowerCase().equals(itemName.toLowerCase())).findFirst().orElse(null);
		return item;
	}
	public Item checkIfItemInSelected(String itemName){
		Item item = selected.stream().filter(i1->i1.getName().toLowerCase().equals(itemName.toLowerCase())).findFirst().orElse(null);
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
		return equipped.stream().filter(w->w.getItemType().equals(ItemType.WEAPON)).findFirst().orElse(null);
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
	public void addSelected(Item item){
		selected.add(item);
	}
	public List<Item> getSelected() {
		return selected;
	}

	public List<Item> getEquipped() {
		return equipped;
	}

	public void setEquipped(List<Item> equipped) {
		this.equipped = equipped;
	}
}
