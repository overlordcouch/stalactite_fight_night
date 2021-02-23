import java.util.*;
/**
 * CaveNode class is the building block of the cave adventure.  Each
 * room will consist of a CaveNode that contains the pertinent information
 * about the room's contents and description.
 * 
 * @author M.Ansell
 * @version 1.2
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

	/**
	 * The chance percentage that a monster will spawn in this cavern when
	 * it generates.
	 * 
	 * @since 1.2
	 */
	private static int MONSTER_CHANCE = 62;
	
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
		this.monsterGen(rand, level);
		
		/*TODO:
		 * Figure out probabilities for monsters, loot, etc.*/
		
	}
	
	/**
	 * Accessor to check if this cavern has loots.
	 * 
	 * @return If the cavern has loot or not.
	 * @since 1.1
	 */
	public boolean hasLoot(){
		return this.hasLoot;
	}
	
	/**
	 * Accessor to check if this cavern has been searched.
	 * 
	 * @return If this cavern has been searched yet.
	 * @since 1.1
	 */
	public boolean beenSearched(){
		return this.searched;
	}
	
	/**
	 * Accessor to check if this cavern has a monster.
	 * 
	 * @return If there is a monster here.
	 * @since 1.1
	 */
	public boolean hasMonster(){
		return this.hasMonster;
	}
	
	/**
	 * Gives the caller a pointer to the Monster object in this cave.
	 * 
	 * @return The monster in this cave.
	 * @throws IllegalStateException if there is no monster in this cave.
	 * @since 1.1
	 */
	public Monster getMonster()throws IllegalStateException{
		
		if(!this.hasMonster){
			throw new IllegalStateException("No monster here");
		}
		return this.caveMon;
	}
	
	
	/**
	 * Eliminates the monster from this cavern when the monster is killed.
	 * 
	 * @throws IllegalStateException if there is no monster in here.
	 * @since 1.1
	 */
	public void killMonster() throws IllegalStateException{
		
		if(!this.hasMonster){
			throw new IllegalStateException("No monster here");
		}
		
		this.caveMon = null;
		this.hasMonster = false;
	}
	
	
	/**
	 * Decides if a monster should populate with the node and generates 
	 * that monster if necessary.
	 * 
	 * @param rand Random object for characteristic generation.
	 * @param level The player level around which the monster will be generated.
	 * @since 1.2
	 */
	private void monsterGen(Random rand, int level){
		
		/*Don't generate monster if chance is exceeded*/
		if(rand.nextInt(100) >= this.MONSTER_CHANCE){
			
			return;
			
		}else{
			//Monster level will be player level +-1
			int monsterLevel = rand.nextInt((level+1) - (level -1)) + (level - 1);
			
			//Generate a monster with random type, description, and the level
			//calculated above.
			
			/*Pull a monster type from the master list by randomly picking an int value less than the size
			 * of the list and pulling the entry from that index.*/
			String type = StalactiteFightNight.monsterType.get(rand.nextInt(StalactiteFightNight.monsterType.size()));
			
			/*Pull a monster description from the master list by randomly picking an int value less than the size
			 * of the list and pulling the entry from that index.*/
			String desc = StalactiteFightNight.monsterDesc.get(rand.nextInt(StalactiteFightNight.monsterDesc.size()));
			
			/*Generate and hold that monster*/
			this.caveMon = new Monster(monsterLevel, type, desc);
			this.hasMonster = true;
			
			return;
		}
		
	}
	   
	
}//CaveNode class
