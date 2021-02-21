import java.lang.Math;
import java.util.*;
/**
 * Monster class represents the monsters that will be encountered
 * in the adventure.  Generally, they will be instantiated inside a
 * CavernNode.
 * 
 * @author M.Ansell
 * @version 1.4
 */
public class Monster {
	
	
	
	/**
	 * The level of the monster.  Plays into strength and health.
	 * 
	 * @since 1.0
	 */
	private int level;
	
	/**
	 * Hit modifier for the creature when attacking.
	 * 
	 * @since 1.4
	 */
	private int hitMod;
	
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
	 * fields based on the level.  Monster stats such that an adventurer
	 * of the same level should be able to beat the monster in an average of
	 * 3 hits.  Beating three monsters of adventurer level should level up.
	 * 
	 * @param level The level of the Monster
	 * @param type The type of creature
	 * @param desc The descibing word for this monster 
	 * @since 1.4
	 */
	public Monster(int level, String type, String desc){
		this.level = level;
		
		this.maxHealth = (((int)Math.round((double)(9 + this.level)/ 2.0)) + level)*3;
		this.health = maxHealth;
		
		this.strength = this.level + 9;
		this.ac = this.level +9;
		this.defeatXP = this.maxHealth;
		this.hitMod = level - (int)Math.round(strength/7.0);
		
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
	
	/**
	 * Monster attack check.  Outputs the attack, to be compared to
	 * adventurer AC.
	 * 
	 * @param d20Roll The result of a d20 roll
	 * @return The monster's attack
	 * @since 1.4
	 */
	public int attack(int d20Roll){
		
		return d20Roll + hitMod;
		
	}
	
	
	/**
	 * Deals monster damage.  Returns the number of points of damage dealt,
	 * including consideration for a critical strike.
	 * 
	 * @param rand A Random object used to generate damage rolls
	 * @param isCrit Boolean representation of whether the strike is critical.
	 * @return The damage dealt by this strike.
	 * @since 1.4
	 */
	public int giveDamage(Random rand, boolean isCrit){
		
		if(isCrit){
			return (int )0.75*(this.strength) + rand.nextInt(this.strength) + 1;
		}else{
			return (rand.nextInt(this.strength)+1 + rand.nextInt(this.strength)+1)/2;
		}
		
	}	
	
	
}//monster class
