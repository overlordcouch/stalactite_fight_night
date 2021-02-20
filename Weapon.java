
/**
 * Weapon class is a subclass of Item.  Implements methods and fields
 * specific to Weapon behaviors.
 * 
 * @author M. Ansell
 * @version 1.1
 */
public class Weapon extends Item{
	
	/**
	 * How damaging is this weapon
	 * 
	 * @since 1.0
	 */
	 private int damage;
	 
	 
	 
	/*TODO:
	 * Figure out how damage will be related to level.
	 * Implement that in a constructor.
	 * 
	 * */ 
	 
	 /**
	  * Weapon constructor.  Works like any item, with the addition of
	  * populating the damage field.
	  * 
	  * @param level The level of the weapon
	  * @param type The type of weapon
	  * @param desc The descriptor word of the weapon
	  * @since 1.1
	  */
	 public Weapon(int level, String type, String desc){
		 super(level, type, desc);
		 damage = -1;
	 }
	 
	
	
	
	
}//Weapon class
