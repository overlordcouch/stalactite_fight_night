import java.util.*;

/**
 * InventoryControl class is the main state driver for player inventory
 * interaction.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class InventoryControl{
	
	/**
	 * Local pointer to the player's inventory.
	 * 
	 * @since 1.0
	 */
	private static List<Item> inventory = StalactiteFightNight.player.getInventory();
	
	/**
	 * Local pointer to the player object.
	 * 
	 * @since 1.0
	 */
	private static Adventurer player = StalactiteFightNight.player;
	
	
	/**
	 * The foundational state function for inventory interactions by the 
	 * player.
	 * 
	 * @since 1.0
	 */
	public static void inventoryMain(){
		
		/*Clear screen and print player information*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		printInventory();
		
	}//inventoryMain
	
	/**
	 * Prints a formatted version of the player's inventory to the terminal.
	 * 
	 * @since 1.0
	 */
	private static void printInventory(){
		
		String header = "Inventory";
		for(int i = 0; i<40; i++){
			header = "*"+ header +"*";
		}
		System.out.println(header);
		
		System.out.println("\n#\tLevel\tItem");
		
		
		
	}
	
	
}//InventoryControl Class
