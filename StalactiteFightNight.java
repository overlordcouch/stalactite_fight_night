import java.util.*;
import java.lang.Math;
import java.io.*;

/**
 * StalactiteFightNight is the driving class for this game.  
 * 
 * @author M.AnsellRose
 * @version 1.5
 */
public class StalactiteFightNight{
	
	/**
	 * Public lists that other game classes can access for random content
	 * generation.
	 * 
	 * @since 1.2
	 */
	public static List<String> monsterType, monsterDesc, weaponType, weaponDesc,
								armorType, armorDesc, potionType, potionDesc, caveDesc;
	
	/**
	 * Random object used throughout the game for generation and event
	 * choices.
	 * 
	 * @since 1.2
	 */
	public static Random rand = new Random();
	
	/**
	 * Scanner on console input that will be used by various classes for
	 * gameplay
	 * 
	 * @since 1.5
	 */
	public static Scanner console = new Scanner(System.in);
	
	/**
	 * The object that holds the current player object.
	 * 
	 * @since 1.5
	 */
	public static Adventurer player;
	
	
	/**
	 * The cavenode representing where the player currently is.
	 * 
	 * @since 1.5
	 */
	public static CaveNode currentCave;
	
	/**
	 * Holds the path that the adventurer has traveled.  Allows
	 * backtracking and retreating along tree.
	 * 
	 * @since 1.5
	 */
	public static Stack<CaveNode> path = new Stack<CaveNode>();
	
	
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
		
		startScreen();
		
		/*Begin with prompting user and creating character*/
		introPrompt();
		
		caveTest();

		
		
	}
	
	/**
	 * Performs startup interactions.  Prints splash screen and interlinks
	 * with instruction method to loop as much as necessary.  Eventually
	 * leads to beginning of gameplay.
	 * 
	 * @since 1.5
	 */
	private static void startScreen(){
		

		clearWindow();
		
		splashPrint();
		
		//pull in user input
		String input = console.next().toLowerCase();
		
		/*Wait until user input is either i or s*/
		while(!input.equals("i") && !input.equals("s")){
			
			input = console.next().toLowerCase();
			
		}
		
		if(input.equals("s")){
			return;
		}
		
		if(input.equals("i")){
			console.nextLine();
			instructPrint();
		}	
		
	}//startScreen
	
	/**
	 * Prints the instructions to the console.  When complete, it will
	 * return the user to the start screen.
	 * 
	 * @since 1.5
	 */
	private static void instructPrint(){
		//Clear the display
		clearWindow();
		
		//Print the instructions
		try{
			Scanner reader=new Scanner( new File("dev_documents/instructions.txt"));
			
			while(reader.hasNextLine()){
				System.out.println(reader.nextLine());
			}
			
		}catch(Exception FileNotFoundException){
			
			System.out.println("Instruction file not found.");
			System.out.println("Press any key to return to the start.");
		}
		
		//Wait for the user to input literally anything
			
			while(!console.hasNextLine()){
				
			}
			console.nextLine();
			
			
			startScreen();
	}//instructionPrint
	
	/**
	 * Prints intro flavor text and prompts user through character creation.
	 * 
	 * @since 1.5
	 */
	private static void introPrompt(){
		
		//Clear the screen and the buffer
		clearWindow();
		if(console.hasNextLine()){
			console.nextLine();
		}
		
		try{
		
			//Print the intro text and prompt
			Scanner reader = new Scanner(new File("dev_documents/intro_prompt.txt"));
			while(reader.hasNextLine()){
				System.out.println(reader.nextLine());
			}
			
		}catch(Exception FileNotFoundException){
			System.out.println("Failed to find intro text.  Enter your name.");
		}
		
		
		
		player = new Adventurer(console.nextLine());
		
		System.out.println();
		
		System.out.println("\"Nice to meet you, " + player + ".\"");
		System.out.println("\"My calling is to sit outside this cave and meet those who dare to enter,");
		System.out.println("so when they inevitably die at least one person will remember them.\"\n");
		
		System.out.println("\"I'm not sure how you plan to succed wearing a "+ player.getEquippedArmor()
							+" with nothing but your "+ player.getEquippedWeapon() +", but who am I to judge?\"");
		System.out.println("\n\n\"Good luck, I guess!\"");
		
		
		System.out.println("\n\n\n\n Press any key to enter the caves!  No turning back now.");
		
		console.nextLine();
		
		
		
		return;
		
		
	}
	
	/**
	 * Starting point of the adventure.  Generates first cave, introduces
	 * with flavor text.
	 * 
	 * @since 1.5
	 */
	private static void enterCaves(){
		
		System.out.println();

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
		caveDesc = new ArrayList<String>();
		
		/*Map each file name to the List that will hold it, that way we can procedurally
		 * read in the files with streamlined code.  This setup allows for
		 * new lists of descriptors to be added to the game with a single line
		 * of code.*/
		 
		Map<String, List<String>> filesToLists = new HashMap<String,List<String>>();
		filesToLists.put("dev_documents/monster_types.txt",monsterType);
		filesToLists.put("dev_documents/weapon_types.txt",weaponType);
		filesToLists.put("dev_documents/armor_types.txt",armorType);
		filesToLists.put("dev_documents/potion_types.txt",potionType);
		
		filesToLists.put("dev_documents/monster_desc.txt",monsterDesc);
		filesToLists.put("dev_documents/weapon_desc.txt",weaponDesc);
		filesToLists.put("dev_documents/armor_desc.txt",armorDesc);
		filesToLists.put("dev_documents/potion_desc.txt",potionDesc);
		filesToLists.put("dev_documents/cave_desc.txt",caveDesc);
		
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
	 * Prints the vital statistics of the player, normally at the top of
	 * the game screen. Prints name, health bar, health, equipped armor,
	 * and equipped weapon.
	 * 
	 * @since 1.5
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
	
	/**
	 * Tests cavenode functionalities and probabilities.
	 * 
	 * @since 1.4
	 */
	private static void caveTest(){
		
		/*Tests CaveNode functionality and monster/loot probabilities
		 * by generating and outputs 100 caverns*/
		
		CaveNode temp;
		for(int index = 0; index <100; index++){
			
			temp = new CaveNode(index, caveDesc.get(rand.nextInt(caveDesc.size())), caveDesc.get(rand.nextInt(caveDesc.size())), null);
			
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
