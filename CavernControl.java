import java.util.*;
import java.io.*;
/**
 * The CavernControl class represents the state of the player being
 * safely in a cavern.  All methods relating to actions taken in
 * a cavern (while not in combat) reside within this class for
 * consistency.
 * 
 * @author M.Ansell
 * @version 1.0
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
		
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		
		
		
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
			
		}else{
			/*Create new cave and make its parent the current cave*/
			CaveNode temp = new CaveNode(player.getLevel(), desc1, desc2, currentCave);
			
			/*Create the link forward from the current path by setting the directional
			 * reference.*/
			currentCave.setPath(direction, temp);
			
			/*Update the state of the game by moveing our 'current' reference
			 * forward to the new cavern*/
			 currentCave = temp;
			 
			 temp = null;
			
		}
		
		String entranceFlavor = entranceDesc.get(randGen.nextInt(entranceDesc.size()));
		System.out.printf("%s %s  ", entranceFlavor, currentCave);
		
		if(currentCave.hasMonster()){
			System.out.printf(" \nThe first thing you notice is the %s.", currentCave.getMonster());
		}else if(currentCave.hasLoot()){
			System.out.printf("You notice a chest, nearly hidden in the shadows.  ");
		}
		System.out.println();
		currentCave.printPaths();
		
		System.out.println("\n\t\t\t\t\tPress any key to proceed.");
		
		while(!console.hasNextLine()){}
		
		cavernMain();
		
		
	}
	
	
	
	
	
	
	
	
}//caverncontrol
