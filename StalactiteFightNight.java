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
								armorType, armorDesc, potionType, potionDesc, caveDesc,
								enterDesc, napDesc, deathDesc, monsterMiss, monsterDeath,
								hitWords;
	
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
		
		//Set the current cave to null in preparation for tree generation.
		CavernControl.currentCave = null;
		
		//Put up the start screen and begin gameplay
		startScreen();
		
		/*Begin with prompting user and creating character*/
		introPrompt();
		
		CavernControl.newCavern(null);
		
		//~ Item test = new Item(1, "cool", "cool");
		//~ System.out.println(test instanceof Armor);

		
		
	}
	
	/**
	 * Performs startup interactions.  Prints splash screen and interlinks
	 * with instruction method to loop as much as necessary.  Eventually
	 * leads to beginning of gameplay.
	 * 
	 * @since 1.5
	 */
	private static void startScreen(){
		

		Helper.clearWindow();
		
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
		Helper.clearWindow();
		
		//Print the instructions
		try{
			Scanner reader=new Scanner( new File("world_gen/instructions.txt"));
			
			while(reader.hasNextLine()){
				System.out.println(reader.nextLine());
			}
			
		}catch(Exception FileNotFoundException){
			
			System.out.println("Instruction file not found.");
			System.out.println("Press 'enter' to return to the start.");
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
		Helper.clearWindow();
		Helper.clearInputBuffer();
		
		try{
		
			//Print the intro text and prompt
			Scanner reader = new Scanner(new File("world_gen/intro_prompt.txt"));
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
		
		System.out.println("\"I'm not sure how you plan to succeed wearing a "+ player.getEquippedArmor()
							+" with nothing but your "+ player.getEquippedWeapon() +", but who am I to judge?\"");
		System.out.println("\n\n\"Good luck, I guess!\"");
		
		
		System.out.println("\n\n\n\n Press 'enter' to enter the caves!  No turning back now.");
		
		while(!console.hasNextLine()){}
		
		
		
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
		enterDesc = new ArrayList<String>();
		napDesc = new ArrayList<String>();
		deathDesc = new ArrayList<String>();
		monsterMiss = new ArrayList<String>();
		monsterDeath = new ArrayList<String>();
		hitWords = new ArrayList<String>();
		
		/*Map each file name to the List that will hold it, that way we can procedurally
		 * read in the files with streamlined code.  This setup allows for
		 * new lists of descriptors to be added to the game with a single line
		 * of code.*/
		 
		Map<String, List<String>> filesToLists = new HashMap<String,List<String>>();
		filesToLists.put("world_gen/monster_types.txt",monsterType);
		filesToLists.put("world_gen/weapon_types.txt",weaponType);
		filesToLists.put("world_gen/armor_types.txt",armorType);
		filesToLists.put("world_gen/potion_types.txt",potionType);
		
		filesToLists.put("world_gen/monster_desc.txt",monsterDesc);
		filesToLists.put("world_gen/weapon_desc.txt",weaponDesc);
		filesToLists.put("world_gen/armor_desc.txt",armorDesc);
		filesToLists.put("world_gen/potion_desc.txt",potionDesc);
		filesToLists.put("world_gen/cave_desc.txt",caveDesc);
		filesToLists.put("world_gen/enter_desc.txt",enterDesc);
		filesToLists.put("world_gen/nap_desc.txt",napDesc);
		filesToLists.put("world_gen/death_desc.txt",deathDesc);
		filesToLists.put("world_gen/monsterMiss.txt",monsterMiss);
		filesToLists.put("world_gen/monster_death.txt",monsterDeath);
		filesToLists.put("world_gen/hit_words.txt",hitWords);
		
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
			File file = new File("world_gen/splash.txt");
			
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
