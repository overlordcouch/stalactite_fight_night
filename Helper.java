
import java.io.*;
/**
 * Helper class contains utility methods that are used in common gameplay
 * across several states.  Gathered in one place for cohesion.
 * 
 * @author M.Ansell
 * @version 1.0
 */
public class Helper{
	
	/**
	 * Local pointer to the player object in the driver.
	 */
	private static Adventurer player = StalactiteFightNight.player;
	
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
	 * @since 1.0
	 */
	public static void clearInputBuffer(){
		if(StalactiteFightNight.console.hasNextLine()){
			StalactiteFightNight.console.nextLine();
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
		
		String healthBar ="[";
		int healthPercentage =(int) Math.round((player.getHealth()/(double)player.getMaxHealth())*10.0) ;	
		
		for (int index = 0; index < healthPercentage; index++){
			healthBar += "|";
		}
		for(int index = 0; index < (10 - healthPercentage); index++){
			healthBar += " ";
		}
		healthBar += "]";
		
		String justification = 	"\t\t\t\t\t\t\t\t\t\t\t\t\t";											
		System.out.println(justification+ player);
		System.out.println(justification+ healthBar);
		System.out.println(justification+ "Health: "+ player.getHealth() +"/"+player.getMaxHealth());
		System.out.println(justification+ "Wearing "+ player.getEquippedArmor());
		System.out.println(justification+ "Wielding " + player.getEquippedWeapon());
		
		return;
	}
	
}//helper
