public enum ItemType {
    WEAPON("WEAPON"),
    CONSUMABLE("CONSUMABLE"),
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
