import java.util.*;
import java.io.*;

public class TestMain{
	
	
	public static void main(String[] args){
		
		try{
			File file = new File("dev_documents/splash.txt");
			
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()){
				System.out.println(reader.nextLine());
			}
		}catch(Exception FileNotFoundException){
			/*If the chosen file name is not valid, this message will print
			 * to the terminal.*/
			System.out.println("SPlash file not found in current directory.\n");
		}
		
		System.out.println("\t\t\t\t\t\t\t\t\t\t  Press 's' to start a new game");
		System.out.println("\t\t\t\t\t\t\t\t\t\t                 or            ");
		System.out.println("\t\t\t\t\t\t\t\t\t\t  Press 'i' for some instructions");
		
		
		
		return;
	}
}
