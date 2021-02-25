import java.util.*;
/**
 * CaveNode class is the building block of the cave adventure.  Each
 * room will consist of a CaveNode that contains the pertinent information
 * about the room's contents and description.
 * 
 * @author M.Ansell
 * @version 1.4
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
	 * The type of loot that is generated if it is generated.  Allows for
	 * casting for access to appropriate subclass methods.
	 * 
	 * @since 1.3
	 */
	private String lootType;

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
	private static int MONSTER_CHANCE = 63;
	
	/**
	 * The chance percentage that there will be loot in this cavern that
	 * is visible at spawn.
	 * 
	 * @since 1.2
	 */
	private static int LOOT_CHANCE = 33;
	
	/**
	 * Boolean values used in the initial cave generation to indicate
	 * valid paths forward.
	 * 
	 * @since 1.4
	 */
	private boolean hasLeft, hasCenter, hasRight;
	
	/**
	 * Pointers for all of the potential paths out of the cave.
	 * 
	 * @since 1.4
	 */
	private CaveNode leftCave, rightCave, centerCave, previous;
	
 
	/**
	 * Builds a CaveNode and populates it with the appropriate monsters and 
	 * items, at the appropriate level.  Determines which paths forward
	 * exist.  
	 * 
	 * @param level The player's level at the time of entering the cavern.
	 * @param adj1 The first word describing this particular cavern.
	 * @param adj2 The second word describing this particular cavern.
	 * @param previous The previous cave in the tree.
	 * 
	 * @since 1.5
	 */
	public CaveNode(int level, String adj1, String adj2, CaveNode previous){
		
		this.level = level;
		this.desc1 = adj1;
		this.desc2 = adj2;
		this.monsterGen(level);
		this.lootGen(level);
		this.pathGen();
		
		/*At generation, the paths exist, but the caves don't,
		 * otherwise we'd end up with a never ending recursive 
		 * construction call*/
		 this.leftCave = null;
		 this.rightCave = null;
		 this.centerCave = null;
		 this.previous = previous;
		
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
	private void monsterGen(int level){
		
		/*Don't generate monster if chance is exceeded*/
		if(StalactiteFightNight.rand.nextInt(100) >= this.MONSTER_CHANCE){
			
			return;
			
		}else{
			//Monster level will be player level +-1
			int monsterLevel = StalactiteFightNight.rand.nextInt((level+1) - (level -1)) + (level - 1);
			
			//Generate a monster with random type, description, and the level
			//calculated above.
			
			/*Pull a monster type from the master list by randomly picking an int value less than the size
			 * of the list and pulling the entry from that index.*/
			String type = StalactiteFightNight.monsterType.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.monsterType.size()));
			
			/*Pull a monster description from the master list by randomly picking an int value less than the size
			 * of the list and pulling the entry from that index.*/
			String desc = StalactiteFightNight.monsterDesc.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.monsterDesc.size()));
			
			/*Generate and hold that monster*/
			this.caveMon = new Monster(monsterLevel, type, desc);
			this.hasMonster = true;
			
			return;
		}
		
	}//monsterGen
	
	/**
	 * Decides is there is loot upon intial generation, and
	 * generates that loot if necessary.
	 * 
	 * @param rand A Random object used to generate the items and description.
	 * @param level The level of the player at the time of calling
	 * @since 1.2
	 */
	private void lootGen(int level){
		
		/*Do nothing if loot chance is exceeded*/
		if(StalactiteFightNight.rand.nextInt() >= this.LOOT_CHANCE){
			return;
		}else{
			this.hasLoot = true;
			/*First, choose what kind of loot to generate*/
			int choice = StalactiteFightNight.rand.nextInt(6);
			
			//And then generate it
			switch(choice){
				case 0:
				case 1:
				case 2: this.lootType = "weapon";
						int weapLevel = StalactiteFightNight.rand.nextInt((level+2) - (level - 1)) + (level -1);
						if(weapLevel == 0){weapLevel = 1;}
						String weapType = StalactiteFightNight.weaponType.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.weaponType.size()));
						String weapDesc = StalactiteFightNight.weaponDesc.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.weaponDesc.size()));
						this.loot = new Weapon(weapLevel, weapType, weapDesc);
					break;
				case 3:
				case 4: this.lootType = "armor";
						int mod = level/10;
						int armLevel = StalactiteFightNight.rand.nextInt(2 - 1) + 1 + StalactiteFightNight.rand.nextInt(level/7 +1);
						String armType = StalactiteFightNight.armorType.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.armorType.size()));
						String armDesc = StalactiteFightNight.armorDesc.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.armorDesc.size()));
						this.loot = new Armor(armLevel, armType, armDesc);
					break;
				case 5: this.lootType = "potion";
						int potLevel = StalactiteFightNight.rand.nextInt((level+1) - (level)) + (level);
						String potType = StalactiteFightNight.potionType.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.potionType.size()));
						String potDesc = StalactiteFightNight.potionDesc.get(StalactiteFightNight.rand.nextInt(StalactiteFightNight.potionDesc.size()));
						this.loot = new Armor(potLevel, potType, potDesc);
					break;
			}//switch/case
			
			return;
		}//if/else
	}//lootGen
	
	/**
	 * Decides which ways you can get out of this cave.
	 * 
	 * @since 1.4
	 */
	private void pathGen(){
		
		/*7 possible combinations of the 3 ways out*/
		int choice  = StalactiteFightNight.rand.nextInt(7);
		
		if(choice%2 == 0){
			this.hasLeft = true;
		}
		
		if(choice >0 && choice <= 4){
			this.hasCenter = true;
		}
		
		if(choice > 2){
			this.hasRight = true;
		}
	}
	
	/**
	 * Returns the current loot type, if there is loot.
	 * 
	 * @return The loot item in this cavern.
	 * @throws IllegalStateException if there is no loot in this cavern.
	 * @since 1.3
	 */
	public Item getLoot()throws IllegalStateException{
		if(!this.hasLoot){
			throw new IllegalStateException("No loot to be got.");
		}
		return this.loot;
	}	  
	
	/**
	 * Returns the type of loot that this cavern has, if there is loot.
	 * 
	 * @return A string indicating the loot type.
	 * @throws IllegalStateException if there is no loot in this cavern.
	 * @since 1.3
	 */
	public String getLootType()throws IllegalStateException{
		
		if(!this.hasLoot){
			throw new IllegalStateException("No loot type because no loot.");
		}
		
		return this.lootType;
		
	} 
	
	/**
	 * General toString method.  Gives string in the form "a adj, adj cavern"
	 * 
	 * @return A string describing the cavern.
	 * @since 1.4
	 */
	public String toString(){
		return "a " + this.desc1 + ", " + this.desc2 + " cavern.";
	}
	
	/**
	 * Prints the configuration of the path forward from this cave to the
	 * console.
	 * 
	 * @since 1.4
	 */
	public void printPaths(){
		
		if(this.hasLeft){
			System.out.println("You see a tunnel leading off to the left.");
		}
		if(this.hasRight){
			System.out.println("You see a tunnel leading off to the right.");
		}
		if(this.hasCenter){
			System.out.println("You see a tunnel leading off directly across from you.");
		}
	}
}//CaveNode class
