import java.util.*;
/**
 * CombatControl class contains the majority of functions related to the
 * combat state of the game.
 * 
 * @author M.Ansell
 * @version 1.1
 */
public class CombatControl{
	
	private static Random randGen = StalactiteFightNight.rand;
	
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
	 * Local pointer to list of death action descriptions.
	 * 
	 * @since 1.0
	 */
	private static List<String> deathDesc = StalactiteFightNight.deathDesc;
	
	/**
	 * Local pointer to monster miss descriptions.
	 * 
	 * @since 1.0
	 */
	private static List<String> monsterMiss = StalactiteFightNight.monsterMiss;
	
	/**
	 * Local pointer to monster death descriptions.
	 * 
	 * @since 1.1
	 */
	 private static List<String> monsterDeath = StalactiteFightNight.monsterDeath;
	
	
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
		
		switch(input){
			
			case "a":	//player attack
						break;
			case "r": 	//retreat
						break;
			case "i": 	//go to inventory
						break;
		}
		
		
		
	}//combatMain
	
	/**
	 * Orchestrates the mechanics of the player attacking a monster.
	 * 
	 * @since 1.1
	 */
	private static void playerAttack(){
		//Ensure player reference is up to date
		player = StalactiteFightNight.player;
		
		/*Get a pointer to the monster being fought*/
		Monster monster = currentCave.getMonster();
		
		/*Basic setup functionality.  Clear window and buffer, print player
		 * state to console*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		/*See if the attack hits.*/
		int diceRoll = randGen.nextInt(20)+1;
		boolean didHit = player.attack(diceRoll) >= monster.getAC();
		
		/*If it hit, calculate and apply damage*/
		int damage = -1;
		if(didHit){
			
			damage = player.giveDamage(diceRoll);
			
			monster.takeDamage(damage);			
		}
		
		/*Check if monster died.  If so, give player XP, check level up.
		 * Remove monster from cavenode.
		 * 
		 * if not, display whether or not hit happenned.  give player 
		 * text.  prompt for continue. */
		
	}
	
	/**
	 * Generates and prints the appropriate actions that the user can
	 * take based on the current combat state.  Returns a Set
	 * that contains string representations of valid choices for the 
	 * player
	 * 
	 * @return Set of strings that are valid inputs from the player.
	 * @since 1.0
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
	
	/**
	 * Performs the actions of the monster's turn.  Typically just an 
	 * attack.  Method returns to sender to allow different paths out
	 * of monsterTurn.  Method automatically passes to death method if
	 * the player dies.
	 * 
	 * @since 1.0
	 */
	private static void monsterTurn(){
		/*Ensure that the player state is up to date*/
		player = StalactiteFightNight.player;
		
		/*Ensure that the pointer to the current cave is up to date */
		currentCave = CavernControl.currentCave;
		
		Monster monster = currentCave.getMonster();
		
		/*Basic setup functionality.  Clear window and buffer, print player
		 * state to console*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		
		
		/*Determine if the monster hits the player.*/
		int diceRoll = randGen.nextInt(20)+1;
		boolean didHit = monster.attack(diceRoll) > player.getAC();
		
		/*If the monster hits, calculate damage*/
		int damage = -1;
		if(didHit){
			damage = monster.giveDamage(diceRoll);
			player.takeDamage(damage);
			
			if(player.getHealth() < 0){
				player.setHealth(0);
			}
		}
		
				
		Helper.printPlayerHeader();
		
		/*Print message about whether or not the monster hit.*/
		if(didHit){
			System.out.println("\n\n\nThe "+ currentCave.getMonster() + "hits you for " + damage +" points of damage.");
		}else{
			System.out.println("\n\n\n");
			System.out.println(monsterMiss.get(randGen.nextInt(monsterMiss.size())));
		}
		
		/*Check for death*/
		if(player.getHealth() == 0){
			
			System.out.println("\n");
			System.out.println(deathDesc.get(randGen.nextInt(deathDesc.size())));
			
			Helper.waitForInput();
			death();
		}else{
			Helper.waitForInput();
			return;
		}
		
	}
	
	private static void death(){
		
	}
	
	
}//CombatControl class
