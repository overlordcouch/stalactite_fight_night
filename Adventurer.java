import java.util.*;

/**
 * Class that contains information, statistics, and inventory for the player character.
 */
public class Adventurer{
	
	/**
	 * Player's name
	 */
	private String name;
	
	/**
	 * Player's level
	 */
	 private int level;
	 
	 /**
	  * Player experience points
	  */
	 private int xp;
	 
	 /**
	  * Player health and maximum help.  Changes with combat and level
	  */
	 private int health, maxHealth;
	 
	 /**
	  * Player's investigatory ability.
	  */
	  
	  private int investigation;
	  
	  /**
	   * How hard the player hits things
	   */
	   private int strength;
	   
	   /**
	    * Armor class.  How protected is the player from getting hit.
	    */
	   private int ac;
	   
	   /**
	    * Tracks whether the player is in combat
	    */
	   private boolean inCombat;
	   
	   /**
	    * Tracks if it is the player's turn in combat
	    */
	    private boolean myTurn;
	   
	 
	
}//class Adventurer
