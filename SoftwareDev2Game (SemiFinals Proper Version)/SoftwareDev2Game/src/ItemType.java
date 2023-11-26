public enum ItemType {
    WEAPON("WEAPON"),
    CONSUMABLE("CONSUMABLE"),
   //worked on 11/25/23
    FOODCONSUMABLE("FOODCONSUMABLE"),
    ARMOR("ARMOR");




    private String value;

    ItemType(String type){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
