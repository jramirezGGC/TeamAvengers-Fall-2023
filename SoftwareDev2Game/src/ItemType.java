public enum ItemType {
    WEAPON("WEAPON"),
    CONSUMABLE("CONSUMABLE"),
    ARMOR("ARMOR");



    private String value;

    ItemType(String type){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
