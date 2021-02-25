/**
 * Armor object class is a subclass of item that implements methods and
 * fields specific to Armor items.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class Armor extends Item{
	
	/*TODO:
	  * FIgure out how armor should be related to level.
	  * Figure out how base durability should be related to level.
	  * Implement ^^ those functions in constructor. */
	
	/**
	 * How good is this piece of armor at stopping hits.
	 * 
	 * @since 1.0
	 */
	private int armor;
	
	/**
	 * How much more beating can this thing take
	 * 
	 * @since 1.0
	 */
	private int durability;
	
	/**
	  * Armor constructor.  Works like any item, with the addition of
	  * populating the armor and durability fields.
	  * 
	  * @param level The level of the weapon
	  * @param type The type of weapon
	  * @param desc The descriptor word of the weapon
	  * @since 1.0
	  */
	 public Armor(int level, String type, String desc){
		 super(level, type, desc);
		 this.armor = -1;
		 this.durability = -1;
		 
	 }
	 
	 /**
	  * Gets the armor value for this piece of armor.
	  * 
	  * @return What this armor adds to player's armor class.
	  * @since 1.0
	  */
	public int getArmor(){
		return this.armor;
	}
	
	/**
	 * Gets the current level of durability for this piece of
	 * armor.
	 * 
	 * @return The remaining durability of this armor.
	 * @since 1.0
	 */
	public int getDurability(){
		return this.durability;
	}
	
}//Armor object class
