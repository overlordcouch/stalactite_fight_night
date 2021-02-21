import java.util.*;
import java.lang.Math;
import java.io.*;

/**
 * StalactiteFightNight is the driving class for this game.  
 * 
 * @author M.AnsellRose
 * @version 1.1
 */
public class StalactiteFightNight{
	
	/**
	 * Prints the game's splash screen to the console, including initial
	 * prompts for user input.
	 * 
	 * @since 1.0
	 */
	public static void splashPrint(){
		
		//Print the splash screen for the game.  Try/catch in case the
		//.txt file can't be found, so the game can play anyway.
		try{
			File file = new File("dev_documents/splash.txt");
			
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()){
				System.out.println(reader.nextLine());
			}
			
			file = null;
			reader = null;
		}catch(Exception FileNotFoundException){
			/*If the chosen file name is not valid, this message will print
			 * to the terminal.*/
			System.out.println("Splash file not found in current directory.\n");
		}
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t  Press 's' to start a new game");
		System.out.println("\t\t\t\t\t\t\t\t\t\t                 or            ");
		System.out.println("\t\t\t\t\t\t\t\t\t\t  Press 'i' for some instructions");
		
		return;
	}//splashPrint
	
	/**
	 * Clears the console window.  Used regularly in gameplay.
	 * 
	 * @since 1.2
	 */
	public static void clearWindow(){
		
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}catch(IOException | InterruptedException ex){
			System.out.println("Couldn't clear terminal!  Oopsie");
		}
		

	}
	
	
}//end main game class
