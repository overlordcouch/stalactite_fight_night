import java.util.*;
import java.lang.Math;


/**
 * Class that contains information, statistics, and inventory for the player character.
 * 
 * @author M.Ansell
 * @version 1.4
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
		 this.ac = 10;
		 
		 this.inCombat = false;
		 this.myTurn = false;
		 this.inventory = new ArrayList<Item>();
		 this.currentWeapon = DEFAULT_WEAPON;
		 this.currentArmor = DEFAULT_ARMOR;
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
	   
	 
	
}//class Adventurer
