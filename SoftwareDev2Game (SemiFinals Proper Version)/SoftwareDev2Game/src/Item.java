
import java.util.ArrayList;
import java.util.List;

public class Item { // F2 to rename all instances

    private String name;
    private String desc;
    private int effect;
    private ItemType itemType;
    private boolean isWeapon;
    private boolean isStun;
    private int stunDamage;
    
    public Item(String name, String desc,ItemType itemType, int effect, boolean isWeapon, boolean isStun, int stunDamage) {
        this.name = name;
        this.desc = desc;
        this.itemType = itemType;
        this.effect = effect;
        this.isWeapon = isWeapon;
        this.isStun = isStun;
        this.stunDamage = stunDamage;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public ItemType getItemType(){
        return itemType;
    }
    public int getEffect(){
        return effect;
    }

    public void inspect(){
        System.out.println(this.desc);
    }
    public static String getInvAsString(List<Item> items){
        List<String> itemsNames = new ArrayList<>();
       items.stream().forEach(item -> {
           itemsNames.add(item.getName());
       });
        return String.join(", ",itemsNames);
    }

    public static String getEquippedAsString(List<Item> equipment){
        List<String> equippedNames = new ArrayList<>();
        equipment.stream().forEach(item ->{
            equippedNames.add(item.getName());
        });
        return String.join(", ", equippedNames);
    }
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", effect=" + effect +
                ", itemType=" + itemType +
                '}';
    }
	/**
	 * @return the isWeapon
	 */
	public boolean isWeapon() {
		return isWeapon;
	}
	/**
	 * @param isWeapon the isWeapon to set
	 */
	public void setWeapon(boolean isWeapon) {
		this.isWeapon = isWeapon;
	}
	/**
	 * @return the isStun
	 */
	public boolean isStun() {
		return isStun;
	}
	/**
	 * @param isStun the isStun to set
	 */
	public void setStun(boolean isStun) {
		this.isStun = isStun;
	}
	/**
	 * @return the stunDamage
	 */
	public int getStunDamage() {
		return stunDamage;
	}
	/**
	 * @param stunDamage the stunDamage to set
	 */
	public void setStunDamage(int stunDamage) {
		this.stunDamage = stunDamage;
	}
}