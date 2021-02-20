/**
 * Monster class represents the monsters that will be encountered
 * in the adventure.  Generally, they will be instantiated inside a
 * CavernNode.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class Monster {
	
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
	
}//monster class
