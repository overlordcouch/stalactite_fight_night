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
	 * Local pointer to the Scanner on the terminal input.
	 * 
	 * @since 1.0
	 */
	private static Scanner console = StalactiteFightNight.console;
	
	
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
		
		/*Create a Set of valid inputs from the user.*/
		Set<String> validInputs = printInventory();
		validInputs.add("q");
		
		/*Prompt user for input, and wait for a valid input.*/
		System.out.println("\n\n\t\t\t\tChoose a number to interact with, or 'Q' to exit inventory.");
		String input = console.next().toLowerCase();
		
		while(!validInputs.contains(input)){
			input = console.next().toLowerCase();
		}
		
		/*Check to see if they quit the inventory, and return them to where they came from.*/
		if(input.equals("q")){
			
			if(player.inCombat()){
				CombatControl.combatMain();
			}else{
				CavernControl.cavernMain();
			}
		}else{/*If they aren't quitting, go to appropriate interactions*/
			//itemInteraction(input);
		}
		
		
		
		
	}//inventoryMain
	
	/**
	 * Drives individual item interactions within the inventory state.
	 * 
	 * @since 1.0
	 */
	private static void itemInteraction(String which){
		
		/*Clear screen and print player information*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		/*Convert the player's choice into an integer, and convert to zero
		 * indexing for pulling from their inventory*/
		int choice = Integer.parseInt(which) - 1;
		
		/*Get a pointer to the object for branch descisions*/
		Item piece = inventory.get(choice);
		
		System.out.println("You are looking at a level "+ piece.getLevel() + " "+ piece);
		
		//printItemOptions(piece);
		
		/*Make use of chort-circuited comparison to prevent player changing armor 
		 * in the middle of combat*/
		if(piece instanceof Armor && player.inCombat()){
			
			
		}
		
	}
	
	/**
	 * Generates the allowable list of options for each type of item.
	 * 
	 * @since 1.0
	 */
	private static Set<String> printItemOptions(Item piece){
		
		Set<String> validOptions = new HashSet<String>();
		
		String justification = 	"\t\t\t\t\t\t\t\t";
		System.out.println("\n\n\n");
		
		/*Generate armor options.*/
		if(piece instanceof Armor){
			
			if(player.inCombat()){
				System.out.println(justification +"Can't change clothes in combat :(");
			}else{
				System.out.println(justification + "E: Equip Item");
				validOptions.add("e");
			}
			
			/********************HERE BE THE WORK************************/
		}
		
		return validOptions;
		
	}
	
	/**
	 * Prints a formatted version of the player's inventory to the terminal.
	 * 
	 * @return Set with valid inputs allowable from user.
	 * @since 1.0
	 */
	private static Set<String> printInventory(){
		/*Generate and print the header*/
		String header = "Inventory";
		for(int i = 0; i<40; i++){
			header = "*"+ header +"*";
		}
		System.out.println(header);
		
		System.out.println("\n#\tLevel\tItem");
		
		/*Create a Set to hold allowable choices for the player*/
		Set<String> validOptions = new HashSet<String>();
		
		/*Print numbered list of current inventory and add valid numbers to player choices for later.*/
		for(int index = 0; index < inventory.size(); index++){
			System.out.println((index+1) + "\t"+inventory.get(index).getLevel() + "\t"+ inventory.get(index));
			
			validOptions.add(String.valueOf(index+1));
		}
		
		
		
		return validOptions;
		
	}
	
	
}//InventoryControl Class
