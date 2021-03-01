import java.util.*;
import java.util.regex.*;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CardMaker {
	private final static String filenameSuffix = ".txt";
	private final static int numOfFiles = 250;
	private final static int MarkovLevel = 1;


	public static void main(String[] args) throws Exception {
		//This is done to universalize this program with no need
		//for manual setup on every device
		Path basePath = Paths.get("MarkovPhrase.java");
		String filenameBase = basePath.toAbsolutePath().getParent().toString() + "/src/assets/card";
		ProbabilityMap wordStorage = new ProbabilityMap();
		ArrayList<String> startPhrases = new ArrayList<String>();
		
		for(int i = 1; i <= numOfFiles; i++) {
			File cardText = new File(filenameBase + i + filenameSuffix);
			Scanner scan = new Scanner(cardText);
			
			String fullText = "";
			
			while(scan.hasNextLine()) {
				fullText += scan.nextLine();
			}
			scan.close();
			
			String cleanedText = cleanText(fullText);
			
			int MarkovLength = 0;
			String[] tokens = cleanedText.split("\\s");
			
			String startPhrase = "";
			while(MarkovLength < MarkovLevel) {
				startPhrase += tokens[MarkovLength] + " ";
				MarkovLength++;
			}
			
			String lowerStartPhrase = startPhrase.toLowerCase();
			startPhrases.add(lowerStartPhrase);
			MarkovLength = 0;
			
			//At this point, each line is being broken up into
			//phrase lengths and added into the various storages
			for(int j = 0; j < tokens.length - MarkovLevel; j++) {
				String leadText = "";
				while(MarkovLength < MarkovLevel) {
					leadText += tokens[j + MarkovLength].toLowerCase() + " ";
					MarkovLength++;
				}
				String followWord = tokens[j + MarkovLength].toLowerCase();
				MarkovLength = 0;
				wordStorage.addPair(leadText, followWord);
			}
		}
		Random rand = new Random();
		
		int startNumber = rand.nextInt(startPhrases.size());
		
		String flavorText = startPhrases.get(startNumber);
		
		int count = 0;
		
		ArrayList<String> addedPhrases = new ArrayList<String>();
		addedPhrases.add(flavorText);
		
		//10 is an arbitrary number, as I didn't want to risk generating too much text depending on lengths of various words
		while(count < 10) {
			String addition = wordStorage.nextWord(addedPhrases.get(count));
			if(addition == null) {	break;	}
			flavorText += addition + " ";
			addedPhrases.add(nextSequence(addedPhrases.get(count), addition));
			count++;
			
		}
		
		System.out.println(flavorText);
		String imagePath = basePath.toAbsolutePath().getParent().toString() + "/src/assets/Markov_Cannon.jpg";
		BufferedImage card = ImageIO.read(new File(imagePath));
		
		Font font = new Font("Arial", Font.BOLD, 10);
		
		Graphics g = card.getGraphics();
		g.setFont(font);
		g.setColor(Color.BLACK);
		
		FontMetrics metrics = g.getFontMetrics();
		
		int positionX = ((card.getWidth() - metrics.stringWidth(flavorText)) / 2);
		int positionY = (card.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() + 150;


		g.drawString(flavorText, positionX, positionY);
		g.dispose();
		
		File cardOutput = new File(basePath.toAbsolutePath().getParent().toString() + "/src/final product.jpg");
		ImageIO.write(card, "jpg", cardOutput);
	}
	
	/*
	 * nextSequence:
	 * 
	 * params:
	 * 	oldSequence: a string containing the x-length word sequence of text that was last used 
	 * 
	 * 	newWord: the newly generated word
	 * 
	 * output:
	 * 	addition: a string with the first word of oldSequence removed and newWord added to the end
	 * 
	 * This method is used to generate the new sequence that is created by adding a new word to the text
	 */
	public static String nextSequence(String oldSequence, String newWord) {
		String addition = "";
		String[] oldWords = oldSequence.split(" ");
		
		if(oldWords.length > 1) {
			for(int i = 1; i < oldWords.length; i++) {
				addition += oldWords[i] + " ";
			}
		}
		
		addition += newWord + " ";
		
		return addition;
	}
	
	/*
	 * cleanText:
	 * 
	 * params:
	 * 	input: a string containing raw text with all punctuation marks in original
	 * 
	 * output:
	 * 	outText: a String that has had all assigned punctuation markings removed
	 * 
	 * This method was designed to cleanly remove the designated punctuation markings (the ones most commonly in mtg flavor text
	 * in order to reduce the amount of unique words that were generated from the input
	 */
	public static String cleanText(String input) {
		String outText = input;
		String storage = outText.replace("”", "");
		outText = storage.replace("\"", "");
		storage = outText.replace(",", "");
		outText = storage.replace(".", "");
		storage = outText.replace("“", "");
		outText = storage.replace("-", " ");
		storage = outText.replace("—", " ");
		outText = storage;
		
		if(!Pattern.matches("([a-zA-Z])", outText.substring(0,1))) {
			storage = outText.substring(1);
			outText = storage;
		}
		return outText;
	}
}