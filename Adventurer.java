import java.util.*;


/**
 * Class that contains information, statistics, and inventory for the player character.
 * 
 * @author M.Ansell
 * @version 1.3
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
		 this.nextLevelXP = -1;
		 this.health = 10;
		 this.maxHealth = 10;
		 this.investigation = 1;
		 this.strength = -1;
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
	   
	 
	
}//class Adventurer
