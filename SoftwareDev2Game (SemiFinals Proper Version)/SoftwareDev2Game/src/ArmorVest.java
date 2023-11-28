package default2;

public class ArmorVest extends Item {
	public ArmorVest(String name, String desc, ItemType itemType, int effect, boolean isWeapon, boolean isStun,
			int stunDamage) {
		super(name, desc, itemType, effect, isWeapon, isStun, stunDamage);
		// TODO Auto-generated constructor stub
	}
	private boolean activeSkillUsed = false;
	private int additionalHP;
	private boolean fortified;
	private int fortifyTurns;
	/**
	 * @return the fortified
	 */
	public boolean isFortified() {
		return fortified;
	}
	/**
	 * @param fortified the fortified to set
	 */
	public void setFortified(boolean fortified) {
		this.fortified = fortified;
	}
	/**
	 * @return the fortifyTurns
	 */
	public int getFortifyTurns() {
		return fortifyTurns;
	}
	/**
	 * @param fortifyTurns the fortifyTurns to set
	 */
	public void setFortifyTurns(int fortifyTurns) {
		this.fortifyTurns = fortifyTurns;
	}
	/**
	 * @return the activeSkillUsed
	 */
	public boolean isActiveSkillUsed() {
		return activeSkillUsed;
	}
	/**
	 * @param activeSkillUsed the activeSkillUsed to set
	 */
	public void setActiveSkillUsed(boolean activeSkillUsed) {
		this.activeSkillUsed = activeSkillUsed;
	}
	/**
	 * @return the additionalHP
	 */
	public int getAdditionalHP() {
		return additionalHP;
	}
	/**
	 * @param additionalHP the additionalHP to set
	 */
	public void setAdditionalHP(int additionalHP) {
		this.additionalHP = additionalHP;
	}
	public void useActiveSkill(Player player) {
        if (!activeSkillUsed) {
            System.out.println("Using Armor Vest's active skill: 'Fortified'!");

            // Increase player's HP
            int previousHP = player.getHP();
            player.setHP(previousHP + additionalHP);

            // Reduce damage received by 20% for 2 turns
            player.setArmorFortified(true);
            player.setFortifiedTurns(2); // The effect lasts for 2 turns

            // Set the activeSkillUsed to true after using the skill
            activeSkillUsed = true;
        } else {
            System.out.println("Armor Vest's active skill already used. Cannot use it again.");
        }
    }
	
}
