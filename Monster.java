/**
 * Monster class represents the monsters that will be encountered
 * in the adventure.  Generally, they will be instantiated inside a
 * CavernNode.
 * 
 * @author M.Ansell
 * @version 1.2
 */
public class Monster {
	
	/*TODO
	 * Figure out relationship between level and other parameters*/
	
	/**
	 * The level of the monster.  Plays into strength and health.
	 * 
	 * @since 1.0
	 */
	private int level;
	
	/**
	 * The health of the monster.
	 * 
	 * @since 1.0
	 */
	private int health, maxHealth;
	
	/**
	 * The strength of the monster
	 */
	private int strength;
	
	/**
	 * The experience the player gains from defeating this monster.
	 * 
	 * @since 1.2
	 */
	private int defeatXP;
	
	/**
	 * How hard it is to hit this monster.
	 * 
	 * @since 1.2
	 */
	private int ac;
	
	/**
	 * Whether it is the monster's turn in combat.
	 * 
	 * @since 1.0
	 */
	private boolean myTurn;
	
	/**
	 * The type and description of the monster.
	 * 
	 * @since 1.0
	 */
	private String type, description;
	
	/**
	 * Monster constructor builds a Monster of a certain level, populating
	 * fields based on the level.
	 * 
	 * @param level The level of the Monster
	 * @param type The type of creature
	 * @param desc The descibing word for this monster 
	 * @since 1.0
	 */
	public Monster(int level, String type, String desc){
		this.level = level;
		this.health = -1;
		this.maxHealth = -1;
		this.strength = -1;
		this.myTurn = false;
		this.type = type;
		this.description = desc;
	}
	
	/**
	 * Applies damage to creature by taking away from health
	 * 
	 * @param damage The amount of damage to deal
	 * @since 1.1
	 */
	public void takeDamage(int damage){
		this.health -= damage;
		return;
	}
	
	/**
	 * Returns the current health of the monster.
	 * 
	 * @return Current monster health.
	 * @since 1.1
	 */
	public int getHealth(){
		return this.health;
	}
	
}//monster class
