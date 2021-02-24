import java.util.*;
import java.lang.Math;
import java.io.*;

/**
 * StalactiteFightNight is the driving class for this game.  
 * 
 * @author M.AnsellRose
 * @version 1.4
 */
public class StalactiteFightNight{
	
	/**
	 * Public lists that other game classes can access for random content
	 * generation.
	 * 
	 * @since 1.2
	 */
	public static List<String> monsterType, monsterDesc, weaponType, weaponDesc,
								armorType, armorDesc, potionType, potionDesc;
	
	/**
	 * Random object used throughout the game for generation and event
	 * choices.
	 * 
	 * @since 1.2
	 */
	public static Random rand = new Random();
	
	
	/**
	 * The driving method for the Stactite Fight Night game.
	 * 
	 * @param args - N/A
	 * @since 1.2
	 */
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
		/*Initialize all the Lists*/
		monsterType = new ArrayList<String>();
		monsterDesc = new ArrayList<String>();
		weaponType = new ArrayList<String>();
		weaponDesc = new ArrayList<String>();
		armorType = new ArrayList<String>();
		armorDesc = new ArrayList<String>();
		potionType = new ArrayList<String>();
		potionDesc = new ArrayList<String>();
		
		/*Map each file name to the List that will hold it, that way we can procedurally
		 * read in the files with streamlined code.*/
		 
		Map<String, List<String>> filesToLists = new HashMap<String,List<String>>();
		filesToLists.put("dev_documents/monster_types.txt",monsterType);
		filesToLists.put("dev_documents/weapon_types.txt",weaponType);
		filesToLists.put("dev_documents/armor_types.txt",armorType);
		filesToLists.put("dev_documents/potion_types.txt",potionType);
		
		filesToLists.put("dev_documents/monster_desc.txt",monsterDesc);
		filesToLists.put("dev_documents/weapon_desc.txt",weaponDesc);
		filesToLists.put("dev_documents/armor_desc.txt",armorDesc);
		filesToLists.put("dev_documents/potion_desc.txt",potionDesc);
		
		/*Retrieve a Set with all of the keys/ file names*/
		Set<String> fileNames = filesToLists.keySet();
		
		/*For each file name, open the file, put a scanner on it, and use its
		 * name as a key to access the list that it's contents need to go to.*/
		for(String fileN : fileNames){
			File words = new File(fileN);
			Scanner reader = new Scanner(words);
			
			
			while(reader.hasNextLine()){
				filesToLists.get(fileN).add(reader.nextLine());
				
			}
			
			words = null;
			reader = null;
			
		}//for each
				
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
	
	/**
	 * Tests cavenode functionalities and probabilities.
	 * 
	 * @since 1.4
	 */
	private void caveTest(){
		
		/*Tests CaveNode functionality and monster/loot probabilities
		 * by generating and outputs 100 caverns*/
		
		CaveNode temp;
		for(int index = 0; index <100; index++){
			
			temp = new CaveNode(index, "big", "black");
			
			System.out.println("You enter "+temp);
			temp.printPaths();
			if(!temp.hasMonster()){
				System.out.println("Cave has no monster");
				
			}else{
				System.out.println(temp.getMonster());
			}
			
			if(!temp.hasLoot()){
				System.out.println("No loot");
			}else{
				System.out.println(temp.getLoot());
			}
			temp =null;
			System.out.println();
		}
		
		return;
	}
	
	
}//end main game class
