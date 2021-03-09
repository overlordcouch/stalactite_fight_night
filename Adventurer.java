import java.util.*;
import java.lang.Math;


/**
 * Class that contains information, statistics, and inventory for the player character.
 * 
 * @author M.Ansell
 * @version 1.8
 */
public class Adventurer{
	
	/**
	 * Local pointer to the Random object in the driver
	 * 
	 * @since 1.7
	 */
	 private Random randGen = StalactiteFightNight.rand;
	 
	
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
		 
		 this.investigation = 0;
		 this.strength = 10;
		 
		 this.currentWeapon = DEFAULT_WEAPON;
		 this.currentArmor = DEFAULT_ARMOR;
		 
		 this.updateAC();
		 
		 this.inCombat = false;
		 this.myTurn = false;
		 this.inventory = new ArrayList<Item>();
		
	 }
	 
	 /**
	  * Checks if the player needs to level up, and performs the level up operation
	  * if appropriate.  Returns a boolean of if the player leveled up to 
	  * allow generation of appropriate text output.
	  * 
	  * @return If the player levels up.
	  * @since 1.7
	  */
	 public boolean levelCheck(){
		 boolean levelUpNow = this.xp >= this.nextLevelXP;
		 
		 if(levelUpNow){
			 this.levelUp();
		 }
		 
		 return levelUpNow;
	 }
	 
	 /**
	  * Returns the current XP of the player.  Added for combat troubleshooting.
	  * 
	  * @return The player's current XP.
	  * @since 1.7
	  */
	 public int getCurrentXP(){
		 return this.xp;
	 }
	 
	 /**
	  * Adds XP to the the player.
	  * 
	  * @param XP The xp to add to the player's total.
	  * @since 1.7
	  */
	 public void addXP(int XP){
		 this.xp += XP;
		 return;
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
	 * @param d20Roll The result of a d20 roll.
	 * @return The amount of damage to deal to the monster.
	 * @since 1.7  
	 */
	public int giveDamage(int d20Roll){
		
		boolean isCrit = d20Roll == 20;
		
		/*Return damage based on whether it's a critical hit or not.*/
		if(isCrit){
			
			return this.strength + (randGen.nextInt(this.strength)+1) + currentWeapon.getDamageMod();
			
		}else{
			
			return randGen.nextInt(this.strength)+1 + currentWeapon.getDamageMod();
			
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
		return this.nextLevelXP + (((int)Math.round(this.strength/2.0))+this.level - 1)*5;
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
		
		return;
	}
	
	private void updateAC(){
		this.ac = 9 + this.level + currentArmor.getArmor();
	}
	
	/**
	 * Returns the player's current AC.
	 * 
	 * @return The player's armor class.
	 * @since 1.7
	 */
	public int getAC(){
		return this.ac;
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
	 
	 /**
	  * Sets the combat state flag of the player when in the presence of
	  * a monster.
	  * 
	  * @param  combatStatus Whether or not the player is in combat.
	  * @since 1.7
	  */
	 public void setCombat(boolean combatStatus){
		 this.inCombat = combatStatus;
	 }
	 
	 /**
	  * Sets the player's health the given value.
	  * 
	  * @param newHealth The value to set the player's health to.
	  * @since 1.7
	  */
	 public void setHealth(int newHealth){
		 this.health = newHealth;
	 }
	 
	 /**
	  * Returns the player's current investigation score.  Used when 
	  * searching for loot.
	  * 
	  * @return The player's investigation score.
	  * @since 1.8
	  */
	 public int getInvestigation(){
		 return this.investigation;
	 }
	 
	 /**
	  * Method to get better at investigating.  Each time the player searches,
	  * there is a 50% chance that they get better at searching.
	  * 
	  * @return Whether or not the player got better at investigating.
	  * @since 1.8
	  */
	 public boolean improveInvestigation(){
		 
		 if(this.investigation <25){
			 /*1/3 chance of improvement Each time you do an investigation, 
			  * there is a 50% chance you get better at investigating.*/
			 boolean gotBetter = (randGen.nextInt(3) == 1);
			 if(gotBetter){
				 this.investigation++;
			 }
			 
			 return gotBetter;
		 }else{
			 return false;
		 }
	 }
	 
	 /**
	  * Accessor to get a pointer to the player's inventory.
	  * 
	  * @return A pointer to the player's inventory.
	  * @since 1.8
	  */
	 public List<Item> getInventory(){
		 return this.inventory;
	 }
	 
	 /**
	  * Returns whether the player is in combat or not.  Mainly used in
	  * inventory operations.
	  * 
	  * @return Whether the player is in combat.
	  * @since 1.8
	  */
	 public boolean inCombat(){
		 return this.inCombat;
	 }
	 
	 /**
	  * Equips new armor to the player.
	  * 
	  * @param freshDigs The new piece of armor to equip.
	  * @since 1.8
	  */
	 public void setArmor(Armor freshDigs){
		 this.currentArmor = freshDigs;
		 this.updateAC();
	 }
	 
	 /**
	  * Equips a new weapon to the player.
	  * 
	  * @param freshTools The weapon to be equipped.
	  * @since 1.8
	  */
	 public void setWeapon(Weapon freshTools){
		 this.currentWeapon = freshTools;
	 }
	   
	 
	
}//class Adventurer
