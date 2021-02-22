import java.util.*;
/**
 * CaveNode class is the building block of the cave adventure.  Each
 * room will consist of a CaveNode that contains the pertinent information
 * about the room's contents and description.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class CaveNode{
	
	/**
	* The words that best describe this specific cavern
	* 
	* @since 1.0
	*/
	private String desc1, desc2;

	/**
	* Whether or not this cave has a monster in it
	* 
	* @since 1.0
	*/
	private boolean hasMonster;

	/**
	* The monster, should there be one.
	* 
	* @since 1.0
	*/
	private Monster caveMon;

	/**
	* Has the cavern been searched?
	* 
	* @since 1.0
	*/
	private boolean searched;

	/**
	* Is there loot to be had in here?
	* 
	* @since 1.0
	*/
	private boolean hasLoot;
	

	/**
	* The loot, should there be any.
	* 
	* @since 1.0
	*/

	private Item loot;

	/**
	* The level of the cavern.  Matches player level at time of generation,
	* used to generate monsters and loot of the appropriate level.
	* 
	* Level will update to the adventurer's level when serched, in case the
	* player comes back later.
	* 
	* @since 1.0
	*/

	private int level;


/*Constructor will need to accept a Random object for contents generation.
 * No need to make a new one here, as that would build up as the game progresses.
 * 
 * The constructor will need information on the player level to generate
 * appropriate contents.  I think it is best to have a CaveGen function that accesses
 * the adventure class and passes the information here.
 * 
 * The constructor will need 2 string descriptors.*/
 
	/**
	 * Builds a CaveNode and populates it the appropriate monsters and 
	 * items, at the appropriate level.
	 * 
	 * @param rand A Random object used for populating the cavern.
	 * @param level The player's level at the time of entering the cavern.
	 * @param adj1 The first word describing this particular cavern.
	 * @param adj2 The second word describing this particular cavern.
	 * 
	 * @since 1.0
	 */
	public CaveNode(Random rand, int level, String adj1, String adj2){
		
		this.level = level;
		this.desc1 = adj1;
		this.desc2 = adj2;
		
		/*TODO:
		 * Figure out probabilities for monsters, loot, etc.*/
		
	}
	   
	
}//CaveNode class
