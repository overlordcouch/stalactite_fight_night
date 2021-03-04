
import java.io.*;
import java.util.*;
/**
 * Helper class contains utility methods that are used in common gameplay
 * across several states.  Gathered in one place for cohesion.
 * 
 * @author M.Ansell
 * @version 1.1
 */
public class Helper{
	
	/**
	 * Local pointer to the player object in the driver.
	 * 
	 * @since 1.0
	 */
	private static Adventurer player = StalactiteFightNight.player;
	
	
	private static Scanner console = StalactiteFightNight.console;
	
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
	
	/**
	 * Clears the console input of any extra characters to ensure
	 * known state prior to inputs.
	 * 
	 * @since 1.1
	 */
	public static void clearInputBuffer(){
		if(console.hasNextLine()){
			console.nextLine();
		}
		
		
		
		return;	
	}
	
	
	/**
	 * Prints the vital statistics of the player, normally at the top of
	 * the game screen. Prints name, health bar, health, equipped armor,
	 * and equipped weapon.
	 * 
	 * @since 1.0
	 */
	public static void printPlayerHeader(){
		player = StalactiteFightNight.player;
		String healthBar ="[";
		int healthPercentage =(int) Math.round((player.getHealth()/(double)player.getMaxHealth())*10.0) ;	
		
		for (int index = 0; index < healthPercentage; index++){
			healthBar += "|";
		}
		for(int index = 0; index < (10 - healthPercentage); index++){
			healthBar += " ";
		}
		healthBar += "]";
		
		String justification = 	"\t\t\t\t\t\t\t\t";											
		System.out.println(justification+ player + " ("+player.getLevel()+")");
		System.out.println(justification+ healthBar);
		System.out.println(justification+ "Health: "+ player.getHealth() +"/"+player.getMaxHealth());
		System.out.println(justification+ "Wearing "+ player.getEquippedArmor());
		System.out.println(justification+ "Wielding " + player.getEquippedWeapon());
		System.out.println("\n\n\n\n");
		
		return;
	}
	
	/**
	 * Prompts the player to hit enter to continue, and then waits for
	 * them to do it.
	 * 
	 * @since 1.1
	 */
	public static void waitForInput(){
		System.out.println("\n\n\t\t\t\t\t\t  Press enter to continue.");
		while(!console.hasNextLine()){}
		
		return;
	}
	
}//helper
