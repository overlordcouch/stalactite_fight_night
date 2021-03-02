import java.util.*;
/**
 * CombatControl class contains the majority of functions related to the
 * combat state of the game.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class CombatControl{
	
	/**
	 * Local pointer to the scanner on terminal input.
	 * 
	 * @since 1.0
	 */
	private static Scanner console = StalactiteFightNight.console;
	
	/**
	 * Local pointer to the player object created by main.
	 * 
	 * @since 1.0
	 */
	private static Adventurer player = StalactiteFightNight.player;
	
	/**
	 * Local pointer to the current player location
	 * 
	 * @since 1.0
	 */
	private static CaveNode currentCave;
	
	/**
	 * The main driver for the combat state.  Orchestrates battle sequence
	 * and interactions with different game states.
	 * 
	 * @since 1.0
	 */
	public static void combatMain(){
		
		/*Ensure that the player state is up to date*/
		player = StalactiteFightNight.player;
		
		/*Ensure that the pointer to the current cave is up to date */
		currentCave = CavernControl.currentCave;
		
		/*Basic setup functionality.  Clear window and buffer, print player
		 * state to console*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		System.out.println("\n\nYou are fighting the "+currentCave.getMonster()+"!  \n\nWhat do you want to do?\n");
		
		Set<String> validInputs = printOptions();
		
		System.out.println("\n\t\t\t\tHow do you want to proceed?");
		
		String input = console.next().toLowerCase();
		
		while(!validInputs.contains(input)){
			input = console.next().toLowerCase();
		}
		
		
		
		
	}//combatMain
	
	/**
	 * Generates and prints the appropriate actions that the user can
	 * take based on the current combat state.  Returns a Set
	 * that contains string representations of valid choices for the 
	 * player
	 * 
	 * @return Set of strings that are valid inputs from the player.
	 */
	private static Set<String> printOptions(){
		
		Set<String> validOptions = new HashSet<String>();
		
		String justification = 	"\t\t\t\t\t\t\t\t";
		System.out.println("\n\n\n");
		
		System.out.println(justification +"I: Inventory");
		validOptions.add("i");
		
		
		if(CavernControl.currentCave.getPrev() != null){
			System.out.println(justification +"R: Retreat");
			validOptions.add("r");
		}
		
		System.out.println(justification + "A: Attack");
		validOptions.add("a");
		
		
		return validOptions;
		
	}
	
	
}//CombatControl class
