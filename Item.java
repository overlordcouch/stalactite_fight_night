
/**
 * Item superclass contains state and methods common to all items
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class Item {
	
	/**
	 * The level of the item.
	 * 
	 * @since 1.0
	 */
	 private int level;
	 
	 /**
	  * All items will have a general type description.  For example, a
	  * Weapon will be of type sword, staff, etc.
	  * 
	  * @since 1.0
	  */
	 private String type;
	 
	 
	 /**
	  * All items will have descriptive words associated with them.
	  * 
	  * @since 1.0
	  */
	 private String description;
	 
	 
/*General accessors for items*/

		/**
		 * Provides the level of the item in question.
		 * 
		 * @return The level of the item.
		 * @since 1.0
		 */
		public int getLevel(){
			return this.level;
		}//get level
		
		/**
		 * Outputs a text description of the item.  Used in inventory and
		 * user interaction.
		 * 
		 * @return String representation of the Item.
		 * @since 1.0
		 */
		public String toString(){
			return "Level " + level + " "+ description + " " + type;
		}
		
		
		
		
	
}//item super class
