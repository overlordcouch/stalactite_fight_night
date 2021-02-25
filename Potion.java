/**
 * The potion class is a general catch-all for healing items.  Adds
 * an additional field for healing.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class Potion extends Item{
	
	/**
	 * The amount of healing the potion will do.  Is a function
	 * of the player's max health at time of aqcuisition.
	 * 
	 * @since 1.0
	 */
	private int healing;
	
	/**
	 * The player's level at time of acquisition, and the level of the 
	 * potion.  Determines amount of healing it will do.
	 * 
	 * @since 1.0
	 */
	private int level;
	
	/**
	 * Potion constructor relies mostly on Item constructor, but includes 
	 * functionality to generate the amount of healing based on player level
	 * at time of acquisition.
	 * 
	 * @param  level The player's level at time of acquisition.
	 * @param type The type of healing (bandage/potion/band-aid.
	 * @param desc A description word for the item
	 * @since 1.0
	 */
	public Potion(int level, String type, String desc){
		super(level, type, desc);
		
		/*Heals for 30% of players max health + a random % up to their level*/
		this.healing = (((int)Math.round((9 + level)/2.0) + level)*4)*3 / 10 + StalactiteFightNight.rand.nextInt(level);
	}
	
	/**
	 * Uses this healing item.  The amount of healing provided gets returned
	 * to the caller.  The description of the item changes to 'used up', and the
	 * healing field is set to 0 to ensure no reuse.  Recommend removing this
	 * item from inventory as part of the use.
	 * 
	 * @return The amount of healing provided.
	 * @since 1.0
	 */
	public int usePotion(){
		int temp = healing;
		this.description = "completely used up";
		this.healing = 0;
		
		return temp;
	}
	
	
	
}//potion
