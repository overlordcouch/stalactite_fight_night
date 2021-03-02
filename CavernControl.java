import java.util.*;
import java.io.*;
/**
 * The CavernControl class represents the state of the player being
 * safely in a cavern.  All methods relating to actions taken in
 * a cavern (while not in combat) reside within this class for
 * consistency.
 * 
 * @author M.Ansell
 * @version 1.1
 */
public class CavernControl{
	
	/**
	 * The cavenode representing where the player currently is.
	 * 
	 * @since 1.0
	 */
	public static CaveNode currentCave;
	
	/**
	 * Local pointer to the Random object held by the driving class.
	 * 
	 * @since 1.0
	 */
	private static Random randGen = StalactiteFightNight.rand;
	
	/**
	 * Local pointer to console input from driver class
	 * 
	 * @since 1.0
	 */
	private static Scanner console = StalactiteFightNight.console;
	
	/**
	 * Local pointer to the list of cave descriptors generated in main.
	 * 
	 * @since 1.0
	 */
	private static List<String> caveDesc = StalactiteFightNight.caveDesc;
	
	/**
	 * Local pointer to the list of entrance actions in main.
	 * 
	 * @since 1.0
	 */
	private static List<String> entranceDesc = StalactiteFightNight.enterDesc;
	
	/**
	 * Local pointer to the list of nap actions in main.
	 * 
	 * @since 1.1
	 */
	private static List<String> napDesc = StalactiteFightNight.napDesc;
	
	/**
	 * Local pointer to the player object created by main.
	 * 
	 * @since 1.0
	 */
	private static Adventurer player = StalactiteFightNight.player;
	
	/**
	 * Local pointer to stack that holds the path traversed.
	 * 
	 * @since 1.0
	 */
	private static Stack<CaveNode> path = StalactiteFightNight.path;
	
	
	/**
	 * Represents the basic state of being in a cavern while not
	 * in combat.  Controls gameplay for related tasks, or passes control
	 * to a different state class as appropriate.
	 * 
	 * @since 1.0
	 */
	public static void cavernMain(){
		//Update player reference
		player = StalactiteFightNight.player;
		
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		
		System.out.printf("You are standing in %s\n", currentCave);
		
		if(currentCave.getPrev() == null && currentCave.exitedFirst ==true){
			System.out.println("You have returned to the entrance, only to find that it has caved in.");
			System.out.println("Looks like the only way out is through the caves!");
		}
		
		if(currentCave.hasLoot()){
			System.out.println("You notice a chest, nearly hidden among the rocks.");
		}
		System.out.println();
		currentCave.printPaths();
		Set<String> validInputs = printOptions();
		
		System.out.println("\n\t\t\t\tHow do you want to proceed?");
		
		String input = console.next().toLowerCase();
		
		
		
		while(!validInputs.contains(input)){
			input = console.next().toLowerCase();
		}

		
		/*If its a direction, newCave in the new direction and repeat.
		 * 
		 * If search then search, if nap then nap, */
		 switch(input){
			case "r":  if(currentCave.getRight() != null){
							currentCave = currentCave.getRight();
							cavernMain();
				
						}
						newCavern("right");
						break;
						
			case "l": if(currentCave.getLeft() != null){
							currentCave = currentCave.getLeft();
							cavernMain();
				
						}
						newCavern("left");
						break;
			case "f": if(currentCave.getCenter() != null){
							currentCave = currentCave.getCenter();
							cavernMain();
				
						}
						newCavern("center");
						break;
			case "p": currentCave = currentCave.getPrev();
						cavernMain();
						break;
						
			case "n": nappyTime();
						break;
		}
		
		
		
		
		
		
	}//cavernMain
	
	/**
	 * Generates a new cavern and links it into the tree structure.  Updates
	 * path stack and transfers control to cavernMain.
	 * 
	 * @param direction The direction to carry on in.
	 * @since 1.0
	 */
	public static void newCavern(String direction){
		player = StalactiteFightNight.player;
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		/*Generate the descriptors for this shiny new cavern*/
		String desc1 = caveDesc.get(randGen.nextInt(caveDesc.size()));
		String desc2 = caveDesc.get(randGen.nextInt(caveDesc.size()));
		
		/*Generate our new cave and connect it.
		 * First, we check if this is the first cave in the instance.*/
		if(currentCave == null){			
			
			currentCave = new CaveNode(player.getLevel(), desc1, desc2, null);
			
			/*Set the flag for the first cavern only.*/
			currentCave.exitedFirst = false;
			
		}else{
			/*Create new cave and make its parent the current cave*/
			CaveNode temp = new CaveNode(player.getLevel(), desc1, desc2, currentCave);
			
			currentCave.exitedFirst = true;
			
			/*Create the link forward from the current path by setting the directional
			 * reference.*/
			currentCave.setPath(direction, temp);
			
			/*Update the state of the game by moveing our 'current' reference
			 * forward to the new cavern*/
			 currentCave = temp;
			 
			 path.push(temp);
			
		}
		
		/*Print out the player's entrance.*/
		String entranceFlavor = entranceDesc.get(randGen.nextInt(entranceDesc.size()));
		System.out.printf("%s %s  ", entranceFlavor, currentCave);
		
		if(currentCave.hasMonster()){
			System.out.printf(" \nThe first thing you notice is the %s.", currentCave.getMonster());
			
			System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t  Press enter to continue.");
			
			while(!console.hasNextLine()){}
			
			/*When a monster is encountered, you are immediately in combat.*/
			player.setCombat(true);
			CombatControl.combatMain();
			
			
		}else if(currentCave.hasLoot()){
			System.out.printf("\nYou notice a chest, nearly hidden in the shadows.  ");
		}
		System.out.println();
		currentCave.printPaths();
		
		System.out.println("\n\t\t\t\t\tPress 'enter' to proceed.");
		
		
		
		while(!console.hasNextLine()){}
		
		cavernMain();
		
		
	}//newCavern
	
	/**
	 * Generates and prints the appropriate actions that the user can
	 * take based on the state of the current cavern.  Returns a Set
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
		
		System.out.println(justification +"N:Take a nap");
		validOptions.add("n");
		
		if(currentCave.hasLoot()){
			System.out.println(justification +"O: Open chest");
			validOptions.add("o");
		}
		if(!currentCave.hasLoot() && !currentCave.beenSearched()){
			System.out.println(justification +"S:Search for hidden loot");
			validOptions.add("s");
		}
		if(currentCave.getPrev() != null){
			System.out.println(justification +"P:Previous cavern");
			validOptions.add("p");
		}
		if(currentCave.hasLeft()){
			System.out.println(justification +"L: Go left");
			validOptions.add("l");
		}
		if(currentCave.hasRight()){
			System.out.println(justification +"R: Go right");
			validOptions.add("r");
		}
		if(currentCave.hasCenter()){
			System.out.println(justification +"F: Go directly ahead");
			validOptions.add("f");
		}
		
		return validOptions;
	}
	
	
	
	/**
	 * Performs nap function.  Restores some player health at the risk
	 * of spawning a monster while sleeping.
	 * 
	 * @since 1.1
	 */
	private static void nappyTime(){
		
		/*Ensure that player reference is up to date.  Clear buffer
		 * and screen*/
		player = StalactiteFightNight.player;
		Helper.clearWindow();
		Helper.clearInputBuffer();
		
				
		
		/*Determine how much healing occurred, and apply it to the player.
		 * Initial: 10% of max health + 1d(max-current), not to exceed
		 * max health.*/
		 int healthMissing = player.getMaxHealth() - player.getHealth();
		 int healthAdd =(int) Math.round((player.getMaxHealth() / 10.0) + randGen.nextInt(healthMissing+1));
		 
		 /*Heal the player for the calculated amount.  If this exceeds their max, bring it down to their max*/
		 player.heal(healthAdd);
		 if(player.getHealth() > player.getMaxHealth()){
			 player.setHealth(player.getMaxHealth());
		 }
		 
		 
		 
		 /*Determine if monster shows up (1/7)*/ 
		 boolean monsterMade;
		 if(randGen.nextInt(7) == 4){
			 currentCave.monsterMaker(player.getLevel());
			 monsterMade = true;
			 player.setCombat(true);
		 }else{
			 monsterMade = false;
		 }
		 
		 /*Tell the player what is up.*/
		 
		System.out.println("\t\t*****************************\n\n");
		Helper.printPlayerHeader();
		System.out.println("\n\n");
		System.out.println(napDesc.get(randGen.nextInt(napDesc.size())));
		System.out.println("\n\n You took a nap! You healed "+healthAdd +" points of damage.");
		
		/*If a monster generated, take the player to combat.*/
		if(monsterMade){
			System.out.println("\nUnfortunately, you were woken up by the "+ currentCave.getMonster() + " that wandered in.");
			System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t  Press enter to continue.");
			
			while(!console.hasNextLine()){}
			
			CombatControl.combatMain();
			
		}else{
			System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t  Press enter to continue.");
			while(!console.hasNextLine()){}
			cavernMain();
			
		}
		
		
		
	}//nappyTime
	
	
	
}//caverncontrol
