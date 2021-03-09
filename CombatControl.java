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
	  * Local pointer to list of hitting words in driver.
	  * 
	  * @since 1.1
	  */
	private static List<String> hitWords = StalactiteFightNight.hitWords;
	
	
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
			
			case "a":	playerAttack();
						break;
			case "r": 	monsterTurn("retreat");
						break;
			case "i": 	InventoryControl.inventoryMain();
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
		
		String monsterDescription = monster.toString();
		
		/*Basic setup functionality.  Clear window and buffer, print player
		 * state to console*/
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		System.out.println("\n\n\n");
		
		/*See if the attack hits.*/
		int diceRoll = randGen.nextInt(20)+1;
		boolean didHit = player.attack(diceRoll) >= monster.getAC();
		
		/*If it hit, calculate and apply damage*/
		int damage = -1;
		if(didHit){
			
			damage = player.giveDamage(diceRoll);
			
			monster.takeDamage(damage);			
		}
		
		/*Check if monster died.*/
		boolean monsterDedNow = monster.getHealth() <= 0;
		
		/*Holder for if player levels up*/
		boolean levelUpNow = false;
		
		/*Transfer XP, remove monster from cavern, perform level up operation
		 * as necessary, if the monster is dead now.*/
		 if(monsterDedNow){
			 
			 /*Transfer monster XP to player and remove from cavern*/
			 player.addXP(CavernControl.currentCave.killMonster());
			 
			 /*Check if player leveled up, and do the leveling if necessary*/
			 levelUpNow = player.levelCheck();
			 
			 /*Put the player out of combat*/
			 player.setCombat(false);
			 
		 }
		
		
		if(didHit){
			//Random word for hit
			String hitDesc = hitWords.get(randGen.nextInt(hitWords.size()));
			
			System.out.println("You "+ hitDesc +" the "+ monsterDescription +" for "+ damage+" points of damage!");
			
			/*If they kill the monster, print message*/
			if(monsterDedNow){
				System.out.println("\n"+monsterDeath.get(randGen.nextInt(monsterDeath.size())));
				System.out.println("\nYou have defeated the "+monsterDescription +"!");
			}
			
			/*Let them know if they have leveled up.*/
			if(levelUpNow){
				
				System.out.println("\nYou  have leveled up to level "+ player.getLevel()+"!");
				
			}
			
			
		}else{//if they didn't hit the monster
			
			System.out.println("\nYour strike misses!");
		}
		
		Helper.waitForInput();
		
		if(!monsterDedNow){
			monsterTurn(null);
		}else{
			CavernControl.cavernMain();
		}
		
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
	 * @param branch What happens after monster turn (null->combat cycle, "retreat"->exit combat).
	 * @since 1.0
	 */
	public static void monsterTurn(String branch){
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
		boolean didHit = monster.attack(diceRoll) >= player.getAC();
		
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
		
		/*Retreat specific text option.*/
		if(branch != null && branch.equals("retreat")){
			System.out.println("As you turn to run, the "+ monster+" tries to attack you!\n");
		}
		
		/*Print message about whether or not the monster hit.*/
		if(didHit){
			System.out.println("The "+ monster + " hits you for " + damage +" points of damage.\n");
		}else{
			System.out.println("");
			System.out.println(monsterMiss.get(randGen.nextInt(monsterMiss.size())));
		}
		
		/*Check for death*/
		if(player.getHealth() == 0){
			
			System.out.println("\n");
			System.out.println(deathDesc.get(randGen.nextInt(deathDesc.size())));
			
			Helper.waitForInput();
			death();
		/*Check if player was trying to retreat, if so do the retreat.*/	
		}else if(branch != null && branch.equals("retreat")){
			
			Helper.waitForInput();
			retreat();
			
		/*Final case: normal combat, return to player choices.*/	
		}else{
			Helper.waitForInput();
			combatMain();
		}
		
	}
	
	/**
	 * Retreat action.  Takes the player out of combat and moves them back to the
	 * previous cave.
	 * 
	 * @since 1.1
	 */
	private static void retreat(){
		/*Take the player out of combat.*/
		player.setCombat(false);
		
		/*Move player back to the previous cavern, and enter normal cavern state*/
		CavernControl.currentCave = CavernControl.currentCave.getPrev();
		CavernControl.cavernMain();
	}
	
	/*Death actions.
	 * 
	 * @since 1.1*/
	private static void death(){
		
		/*Set health to 1/4*/
		player.setHealth(player.getMaxHealth()/10);
		
		/*REmove some random stuff from inventory*/
		
		/*Take them out of combat and put them in the previous cave.*/
		if(CavernControl.currentCave.getPrev() != null){
			Helper.clearWindow();
			Helper.clearInputBuffer();
			System.out.println("You wake, sore and battered, in the cavern you just tried to leave.");
			Helper.waitForInput();
			retreat();
		}else{
			System.out.println("You can't retreat any further.  The cavern has bested you.");
			Helper.waitForInput();
			System.exit(0);
			
		}
		
		
		
	}
	
	
}//CombatControl class
