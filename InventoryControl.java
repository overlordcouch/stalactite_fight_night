import java.util.*;

/**
 * InventoryControl class is the main state driver for player inventory
 * interaction.
 * 
 * @author M.Ansell
 * @version 1.1
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
			itemInteraction(input);
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
		
		
		/*Print valid options for the item based on item and context.
		 * Create a list of valid inputs from the user*/
		Set<String> validOptions = printItemOptions(piece);
		
		System.out.println("\n\t\t\t\tWhat would you like to do with this?");
		
		String input = console.next().toLowerCase();
		
		while(!validOptions.contains(input)){
			input = console.next().toLowerCase();
		}
		
		switch(input){
			
			//Discard
			case "d":	itemDiscard(choice);
						break;
						
			//Equip
			case "e":	itemEquip(choice);
						break;
						
			//Return to inventory
			case "r":	inventoryMain();
						break;
						
			//Use a consumable item
			case "u":	useConsumable(choice);
						break;
		}
		
		
	}
	
	/**
	 * Uses a consumable item.  At time of writing, this only includes potions,
	 * but may change in future iterations.
	 * 
	 * @param listPosition The List index of the item to use.
	 * @since 1.1
	 */
	private static void useConsumable(int listPosition){
		/*Clear screen and print player information*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		Item toUse = inventory.remove(listPosition);
		
		/*Examine the item to be consumed.  Take appropriate action
		 * based on what kind of item it is.*/
		if(toUse instanceof Potion){
			int healPts = ((Potion)toUse).usePotion();
			player.heal(healPts);
			
			System.out.println("You use the "+ toUse +" and heal for "+ healPts +" points of damage!");
		}
		
		/*If in combat, its the monster turn now, if not, go back to inventory.*/
		
		System.out.println("\n\n");
		Helper.waitForInput();
		
		if(player.inCombat()){
			CombatControl.monsterTurn(null);
		}else{
			inventoryMain();
		}
		
	}
	
	/**
	 * Swaps the chosen item in inventory with the appropriate piece on the
	 * player.
	 * 
	 * @param listPosition The index of the item to be equipped.
	 * @since 1.1
	 */
	private static void itemEquip(int listPosition){
		
		/*Clear screen and print player information*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		
		
		/*Pull the item to be equipped out of the inventory*/
		Item toEquip = inventory.remove(listPosition);
		
		/*Examine the item to be equipped to figure out where it needs
		 * to go.*/
		if(toEquip instanceof Armor){
			Armor temp = player.getEquippedArmor();
			
			/*Put currently equipped armor into the inventory*/
			if(!temp.toString().equals("filthy bathrobe")){
				inventory.add(temp);
			}
			
			/*Equip the new armor*/
			player.setArmor((Armor) toEquip);
			
			/*Tell the player about it.*/
			System.out.println("You swap your "+ temp + " for the far superior " + toEquip+"!");
			
			if(!temp.toString().equals("filthy bathrobe")){
				System.out.println("The "+temp+" was returned to your inventory!");
			}
			
		}else if (toEquip instanceof Weapon){
			
			Weapon temp = player.getEquippedWeapon();
			
			/*Put currently equipped armor into the inventory*/
			if(!temp.toString().equals("puny fists")){
				inventory.add(temp);
			}
			
			/*Equip the new armor*/
			player.setWeapon((Weapon) toEquip);
			
			/*Tell the player about it.*/
			System.out.println("You swap your "+ temp + " for the far superior " + toEquip+"!");
			
			if(!temp.toString().equals("filthy bathrobe")){
				System.out.println("The "+temp+" was returned to your inventory!");
			}
			
		}
		
		System.out.println("\n\n");
		Helper.waitForInput();
		
		/*If you are in combat, return it to combat on the monster's turn*/
		if(player.inCombat()){
			CombatControl.monsterTurn(null);
		}else{
			
			inventoryMain();
			
		}
		
	}
	
	/**
	 * Discards item from player inventory, letting the player know
	 * if was discarded.
	 * 
	 * @param listPosition The List index of the item that was chosen.
	 * @since 1.1
	 */ 
	private static void itemDiscard(int listPosition){
		
		/*Clear screen and print player information*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		/*Inform the player that it was discarded, and remove it from the inventory.*/
		System.out.println("You discarded the "+ inventory.get(listPosition) + "!");
		inventory.remove(listPosition);
		
		System.out.println("\n\n\n");
		Helper.waitForInput();
		
		inventoryMain();
		
		
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
			
		}else if(piece instanceof Weapon){
			
			System.out.println(justification + "E: Equip Item");
			validOptions.add("e");
			
		}else if (piece instanceof Potion){
		
			
			System.out.println(justification + "U: Use Item");
			validOptions.add("u");
			
		} 
		
		
		System.out.println(justification+ "D: Discard Item");
		validOptions.add("d");
		
		System.out.println(justification + "R: Return to Inventory");
		validOptions.add("r");
		
		
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
