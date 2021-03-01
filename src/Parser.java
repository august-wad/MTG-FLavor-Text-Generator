import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Parser {
	private final static String filenameBase = "C:\\Users\\gusby\\eclipse-workspace\\MTG Flavor Text Generator\\src\\CardText\\card";
	private final static String filenameSuffix = ".txt";
	private final static int numOfFiles = 200;
	
	public static void main(String[] args) throws Exception {
		for(int i = 1; i <= numOfFiles; i++) {
			File flavorText = new File(filenameBase + i + filenameSuffix);
			Scanner scan = new Scanner(flavorText);
			
			String fullText = "";
			
			while(scan.hasNextLine()) {
				fullText += scan.nextLine();
			}
			scan.close();
			
			String newText = fullText.replace("”", " ");
			fullText = newText.replace("\"", "");
			if(!Pattern.matches("([a-zA-Z])", fullText.substring(0,1))) {
				newText = fullText.substring(1);
				fullText = newText;
			}
			String[] tokens = newText.split("\\s");

			for(int j = 0; j < tokens.length - 2; j++) {
				
			}
		}
	}
}
