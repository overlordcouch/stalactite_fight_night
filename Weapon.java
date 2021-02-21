
/**
 * Weapon class is a subclass of Item.  Implements methods and fields
 * specific to Weapon behaviors.
 * 
 * @author M. Ansell
 * @version 1.2
 */
public class Weapon extends Item{
	
	/**
	 * How damaging is this weapon
	 * 
	 * @since 1.0
	 */
	 private int damageModifier;
	 
	 /**
	  * How good this is at hitting things.
	  * 
	  * @since 1.2
	  */
	 private int hitModifier;
	 
	 /**
	  * How many times this can hit without breaking.
	  * 
	  * @since 1.2
	  */
	 private int durability;
	 
	 
	 
	/*TODO:
	 * Figure out how damage will be related to level.
	 * Implement that in a constructor.
	 * 
	 * */ 
	 
	 /**
	  * Weapon constructor.  Works like any item, with the addition of
	  * populating the damage field.  Updated in 1.2 to reflect appropriate
	  * relationships.
	  * 
	  * @param level The level of the weapon
	  * @param type The type of weapon
	  * @param desc The descriptor word of the weapon
	  * @since 1.1
	  */
	 public Weapon(int level, String type, String desc){
		 super(level, type, desc);
		 this.damageModifier = this.level;
		 this.hitModifier = this.level;
		 
	 }
	 
	 /**
	  * Returns the weapon hit modifier used when seeing if an attack
	  * with this weapon hits
	  * 
	  * @return The weapon's hit modifier
	  * @since 1.2
	  */
	 public int getHitMod(){
		 return this.hitModifier;
	 }
	 
	 /**
	  * Returns the damage modifier for this weapon.  Used when calculating
	  * damage done by a hit with this weapon.
	  * 
	  * @return The damage modifier for this weapon.
	  * @since 1.2
	  */
	 public int getDamageMod(){
		 return this.damageModifier;
	 }
	
	
	
	
}//Weapon class
