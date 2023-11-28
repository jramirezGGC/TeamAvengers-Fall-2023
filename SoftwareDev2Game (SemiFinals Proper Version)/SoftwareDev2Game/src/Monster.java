
import java.util.ArrayList;
import java.util.List;

public class Monster {
    private String name;
    private String desc;
    private int atk;
    private int hp;
    private boolean isBleeding;
    private int bleedTicks;
    private int initialHp;
    private boolean isStunned;
    private boolean stunApplied;
    private boolean isWounded;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Monster(String monsterName, String monsterDesc, int ATK, int HP) {
        this.name = monsterName;
        this.desc = monsterDesc;
        this.atk = ATK;
        this.hp = HP;
        this.initialHp = HP;
        this.bleedTicks = 0;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
    public int getAtk(){
        return atk;
    }
    public void setAtk(int atk) {
    	this.atk = atk;
    }
    public int getHp(){
        return hp;
    }
    public static String getMonsterAsString(List<Monster> monsters){
        List<String> monsterNames = new ArrayList<>();
        monsters.stream().forEach(monster -> {
            monsterNames.add(monster.getName());
        });
        return String.join(",",monsterNames);
    }


    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", atk=" + atk +
                ", hp=" + hp +
                '}';
    }

	/**
	 * @return the isBleeding
	 */
	public boolean isBleeding() {
		return isBleeding;
	}

	/**
	 * @param isBleeding the isBleeding to set
	 */
	public void setBleeding(boolean isBleeding) {
		this.isBleeding = isBleeding;
	}

	/**
	 * @return the bleedTicks
	 */
	public int getBleedTicks() {
		return bleedTicks;
	}

	/**
	 * @param bleedTicks the bleedTicks to set
	 */
	public void setBleedTicks(int bleedTicks) {
		this.bleedTicks = bleedTicks;
	}

	/**
	 * @return the initialHp
	 */
	public int getInitialHp() {
		return initialHp;
	}

	/**
	 * @param initialHp the initialHp to set
	 */
	public void setInitialHp(int initialHp) {
		this.initialHp = initialHp;
	}

	/**
	 * @return the isStunned
	 */
	public boolean isStunned() {
		return isStunned;
	}

	/**
	 * @param isStunned the isStunned to set
	 */
	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}

	/**
	 * @return the stunApplied
	 */
	public boolean isStunApplied() {
		return stunApplied;
	}

	/**
	 * @param stunApplied the stunApplied to set
	 */
	public void setStunApplied(boolean stunApplied) {
		this.stunApplied = stunApplied;
	}

	/**
	 * @return the isWounded
	 */
	public boolean isWounded() {
		return isWounded;
	}

	/**
	 * @param isWounded the isWounded to set
	 */
	public void setWounded(boolean isWounded) {
		this.isWounded = isWounded;
	}
}
