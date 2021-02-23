import java.util.*;
import java.lang.Math;
import java.io.*;

/**
 * StalactiteFightNight is the driving class for this game.  
 * 
 * @author M.AnsellRose
 * @version 1.2
 */
public class StalactiteFightNight{
	
	/**
	 * Public lists that other game classes can access for random content
	 * generation.
	 * 
	 * @since 1.2
	 */
	public static List<String> monsterType, monsterDesc;
	
	
	public static void main(String[] args){
		
		/*First, we populate the lists that are used for content generation*/
		try{
			
			listPopulate();
			
		}catch(Exception FileNotFoundException){
			System.out.println("The file paths to content lists are not all valid.");
		}
		
		
	}
	
	/**
	 * Runs at the beginning of the program to populate the public lists
	 * that other game classes use to generate random content.
	 * 
	 * @throws FileNotFoundException if filepath to descriptors is not valid.
	 * @since 1.2
	 */
	private static void listPopulate()throws FileNotFoundException{
		
		/*Open the descripto files*/
		File monDescFile = new File("dev_documents/monster_desc.txt");
		File monTypeFile = new File("dev_documents/monster_types.txt");
		
		/*Scan the files, reading them into a HashSet to scramble them.
		 * Then, move them into an ArrayList for fast access*/
		Scanner monDescReader = new Scanner(monDescFile);
		
		Set<String> monDescSet = new HashSet<String>();
		
		while(monDescReader.hasNextLine()){
			monDescSet.add(monDescReader.nextLine());
		}
		monsterDesc = new ArrayList<String>(monDescSet);
		
		
		Scanner monTypeReader = new Scanner (monTypeFile);
		
		Set<String> monTypeSet = new HashSet<String>();
		
		while(monTypeReader.hasNextLine()){
			monTypeSet.add(monTypeReader.nextLine());
		}
		
		monsterType = new ArrayList<String>(monTypeSet);
		
	}//listpopulate
	
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
