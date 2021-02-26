
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
	
}//helper
