import java.util.*;

/**
 * Class that contains information, statistics, and inventory for the player character.
 * 
 * @author M.Ansell
 * @version 1.1
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
	      
	      //~ /**
	       //~ * The current armor that the player is wearing.
	       //~ * 
	       //~ * @since 1.1
	       //~ */
	       //~ private 
	   
	 
	
}//class Adventurer
