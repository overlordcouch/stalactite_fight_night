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
	 * Local pointer to the player object created by main.
	 * 
	 * @since 1.0
	 */
	private static Adventurer player = StalactiteFightNight.player;
	
	
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
		
		Helper.clearWindow();
		Helper.clearInputBuffer();
		Helper.printPlayerHeader();
		
		/*Generate the descriptors for this shiny new cavern*/
		String desc1 = caveDesc.get(randGen.nextInt(caveDesc.size()));
		String desc2 = caveDesc.get(randGen.nextInt(caveDesc.size()));
		
		/*First, we check if this is the first cave in the instance.*/
		if(StalactiteFightNight.currentCave == null){			
			
			StalactiteFightNight.currentCave = new CaveNode(player.getLevel(), desc1, desc2, null);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}//caverncontrol
