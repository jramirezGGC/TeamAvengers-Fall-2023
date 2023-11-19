public enum ItemType {
    WEAPON("WEAPON"),
    CONSUMABLE("CONSUMABLE"),
    FOOD("FOOD"),
    ARMOR("ARMOR");



    private String value;

    ItemType(String type){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
