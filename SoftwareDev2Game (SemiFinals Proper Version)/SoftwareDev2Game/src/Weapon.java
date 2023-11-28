
public class Weapon extends Item {
	
	private boolean activeSkillUsed;
	private boolean hasActiveSkill;
	
	public Weapon(String name, String desc, ItemType itemType, int effect, boolean isWeapon, boolean isStun, int stunDamage, boolean activeSkillUsed, boolean hasActiveSkill) {
		super(name, desc, itemType, effect, isWeapon, isStun, stunDamage);
		this.activeSkillUsed = false;
		this.hasActiveSkill = hasActiveSkill;
		// TODO Auto-generated constructor stub
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
	 * @return the hasActiveSkill
	 */
	public boolean hasActiveSkill() {
		return hasActiveSkill;
	}

	/**
	 * @param hasActiveSkill the hasActiveSkill to set
	 */
	public void setHasActiveSkill(boolean hasActiveSkill) {
		this.hasActiveSkill = hasActiveSkill;
	}

}
