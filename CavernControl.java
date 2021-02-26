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
	private Random randGen = StalactiteFightNight.rand;
	
	/**
	 * Local pointer to console input from driver class
	 * 
	 * @since 1.0
	 */
	private Scanner console = StalactiteFightNight.console;
	
	
	/**
	 * Represents the basic state of being in a cavern while not
	 * in combat.  Controls gameplay for related tasks, or passes control
	 * to a different state class as appropriate.
	 * 
	 * @since 1.0
	 */
	public static void cavernMain(){
		
		clearWindow();
		
		
	}
	
	
	
	
	
	
	/**
	 * Clears the console window.  Used regularly in gameplay.
	 * 
	 * @since 1.0
	 */
	public static void clearWindow(){
		
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}catch(IOException | InterruptedException ex){
			System.out.println("Couldn't clear terminal!  Oopsie");
		}	

	}
	
}//caverncontrol
