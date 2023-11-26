import java.util.ArrayList;
import java.util.List;

public class Item { // F2 to rename all instances

    private String name;
    private String desc;
    private int effect;
    private ItemType itemType;
    public Item(String name, String desc,ItemType itemType, int effect) {
        this.name = name;
        this.desc = desc;
        this.itemType = itemType;
        this.effect = effect;
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
    public static String getSelectedAsString(List<Item> selected){
        List<String> selectedNames = new ArrayList<>();
        selected.stream().forEach(item ->{
        	selectedNames.add(item.getName());
        });
        return String.join(", ", selectedNames);
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
}