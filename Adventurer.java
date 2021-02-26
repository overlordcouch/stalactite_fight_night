import java.util.*;
import java.lang.Math;


/**
 * Class that contains information, statistics, and inventory for the player character.
 * 
 * @author M.Ansell
 * @version 1.6
 */
public class Adventurer{
	
	/**
	* Player's name
	* 
	* @since 1.0
	*/
	private String name;

	/**
	* Player's level
	* 
	* @since 1.0
	*/
	private int level;

	/**
	* Player experience points
	* 
	* @since 1.0
	*/
	private int xp;
	
	/**
	 * The amount of xp that is the boundary to the next level
	 * 
	 * @since 1.3
	 */
	 private int nextLevelXP;

	/**
	* Player health and maximum help.  Changes with combat and level
	* 
	* @since 1.0
	*/
	private int health, maxHealth;

	/**
	* Player's investigatory ability.
	* 
	* @since 1.0
	*/

	private int investigation;

	/**
	* How hard the player hits things
	* 
	* @since 1.0
	*/
	private int strength;

	/**
	* Armor class.  How protected is the player from getting hit.
	* 
	* @since 1.0
	*/
	private int ac;

	/**
	* Tracks whether the player is in combat
	* 
	* @since 1.0
	*/
	private boolean inCombat;

	/**
	* Tracks if it is the player's turn in combat
	* 
	* @since 1.0
	*/
	private boolean myTurn;

	/**
	* The player's inventory.
	* 
	* @since 1.1
	*/
	private List<Item> inventory;
	     
	/**
	* The current weapon that the player is holding.
	* 
	* @since 1.1
	*/
	private Weapon currentWeapon;
	
	/**
	 * Default weapon when nothing is equipped is the player's puny fists.
	 * 
	 * @since 1.2
	 */
	private static Weapon DEFAULT_WEAPON = new Weapon(1, "fists", "puny");
	
	
	
	/**
	 * The current armor that the player has equipped.
	 * 
	 * @since 1.1
	 */
	 private Armor currentArmor;
	 
	 /**
	  * Default armor when nothing else is equipped is a filthy bathrobe.
	  * 
	  * @since 1.2
	  */
	 private static Armor DEFAULT_ARMOR = new Armor (1, "bathrobe", "filthy");
	 
	 /**
	  * Adventurer constructor creates a level 1 adventurer.
	  * 
	  * @param name The name of the adventurer
	  * @throws IllegalArgumentException if passed a null string as a name.
	  * @since 1.2
	  */
	 public Adventurer(String name) throws IllegalArgumentException{
		 
		 //Check for tricksters
		 if(name == null){
			 throw new IllegalArgumentException ("Must have a name.");
		 }
		 
		 
		 this.name = name;
		 this.level = 1;
		 this.xp = 0;
		 this.nextLevelXP = 54;
		 
		 this.maxHealth = getNewMaxHealth(this.level);
		 this.health = this.maxHealth;
		 
		 this.investigation = 1;
		 this.strength = 10;
		 
		 this.currentWeapon = DEFAULT_WEAPON;
		 this.currentArmor = DEFAULT_ARMOR;
		 
		 this.updateAC();
		 
		 this.inCombat = false;
		 this.myTurn = false;
		 this.inventory = new ArrayList<Item>();
		
	 }
	      
	
	/**
	 * Get the adventurer's current health.
	 * 
	 * @return The adventurer's health
	 * @since 1.3
	 */
	public int getHealth(){
		return this.health;
		
	}
	
	/**
	 * Accessor to return the player's max possible health for the
	 * current level.
	 * 
	 * @return The maximum health capacity of the player
	 * @since 1.6
	 */
	public int getMaxHealth(){
		return this.maxHealth;
	}
	
	/**
	 * Deal damage to the adventurer by reducing health.
	 * 
	 * @param damage The damage to deal to the player
	 * @since 1.3
	 */
	public void takeDamage(int damage){
		this.health -= damage;
		return;
	}
	
	/**
	 * Heal the adventurer for a certain number of points.
	 * 
	 * @param points The number of points to heal
	 * @since 1.3
	 */
	public void heal(int points){
		this.health += points;
		return;
	}
	
	/**
	 * Determines the hitliness of the attack considering chance, player
	 * strength, and and the current weapon equipped.
	 * 
	 * @param d20Roll The result of a d20 roll
	 * @return The total hit number of the attack, to be compared against monster AC.
	 * @since 1.5
	 */
	public int attack(int d20Roll){
		return d20Roll + (int)Math.round(this.strength/ 10.0) + currentWeapon.getHitMod();
	}
	
	/**
	 * Determines the damage done by a successful attack.  Considers critical
	 * hits, as well as current weapon that is equipped.
	 * 
	 * @param rand Random object for determining damage dealt.
	 * @param isCrit Boolean representation of if the strike was a critical hit.
	 * @return The amount of damage to deal to the monster.
	 * @since 1.5  
	 */
	public int giveDamage(Random rand, boolean isCrit){
		
		/*Return damage based on whether it's a critical hit or not.*/
		if(isCrit){
			
			return this.strength + (rand.nextInt(this.strength)+1) + currentWeapon.getDamageMod();
			
		}else{
			
			return rand.nextInt(this.strength)+1 + currentWeapon.getDamageMod();
			
		}
	
	}
	
	/**
	 * Calculate the XP required for the next level up.
	 * NOTE: Must be calculated AFTER level increase occurs.
	 * 
	 * @param currentLevel The current level you are calculating the next bound for.
	 * @return The XP required to level up next
	 * @since 1.4
	 */
	private int getNextXP(int currentLevel){
		return this.nextLevelXP + (((int)Math.round(this.strength/2.0))+this.level - 1)*3;
	}
	
	
	/**
	 * Calculates new maxuimum health based on new level.
	 * 
	 * @param currentLevel The new level of the player.
	 * @return New value for maximum health capacity.
	 * @since 1.4
	 */
	private int getNewMaxHealth(int currentLevel){
		return ((int)Math.round((9 + currentLevel)/2.0) + currentLevel)*4;
	}
	
	/**
	 * Levels the player up.  Increases all stats appropriately.
	 * 
	 * @since 1.4
	 */
	private void levelUp(){
		this.updateAC();
		this.strength++;
		this.level++;
		
		this.nextLevelXP = getNextXP(this.level);
		
		int tempHealth = this.maxHealth;
		this.maxHealth = getNewMaxHealth(this.level);
		
		this.health += (this.maxHealth - tempHealth);
	}
	
	private void updateAC(){
		this.ac = 9 + this.level + currentArmor.getArmor();
	}
	
	/**
	 * Standard toString method, returns the adventurers name only.
	 * 
	 * @return the name of the adventurer.
	 * @since 1.6
	 */
	public String toString(){
		return this.name;
	}
	
	/**
	 * Accessor to return a pointer to the current armor that the 
	 * adventurer has equipped.
	 * 
	 * @return Currently equipped armor.
	 * @since 1.6
	 */
	public Armor getEquippedArmor(){
		return this.currentArmor;
	}
	
	/**
	 * Accessor to return a pointer to the current weapon that the
	 * adventurer is wielding.
	 * 
	 * @return The weapon that is equipped.
	 * @since 1.6
	 */
	public Weapon getEquippedWeapon(){
		return this.currentWeapon;
	}
	
	/**
	 * Accessor to determine the player's current level.  Used for ambient
	 * content generation.
	 * 
	 * @return The adventurer's current level.
	 * @since 1.6
	 */
	 
	 public int getLevel(){
		 return this.level;
	 }
	   
	 
	
}//class Adventurer
