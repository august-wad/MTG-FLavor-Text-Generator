import java.util.*;
import java.util.Map.Entry;


public class MarkovPhrase {
	LinkedHashMap<String, Integer> probabilityMap;
	
	public MarkovPhrase(String nextWord) {
		probabilityMap = new LinkedHashMap<String, Integer>();
		increment(nextWord);
	}
	
	public void increment(String nextWord) {
		if(probabilityMap.containsKey(nextWord)) {
			int seenCount = probabilityMap.get(nextWord);
			probabilityMap.put(nextWord, seenCount + 1);
		}
		else {	probabilityMap.put(nextWord, 1);	}
	}
	
	public boolean containsWord(String word) {
		if(probabilityMap.containsKey(word)) {	return true;	}
		else {	return false;	}
	}
	
	public int getSeenCount(String phrase) {
		if(!probabilityMap.containsKey(phrase)) {	return -1;	}
		else {	return probabilityMap.get(phrase);	}
	}
	
	public LinkedHashMap<String, Integer> getProbabilityMap(){	return probabilityMap;	}
	
	public String nextWord() {
		Random rand = new Random();
		String nextWord = null;
		Set<Entry<String, Integer>> entries = probabilityMap.entrySet();
		Iterator<Entry<String, Integer>> itr = entries.iterator();
		ArrayList<String> nextWords = new ArrayList<String>();
		
		while(itr.hasNext()) {
			Entry<String, Integer> placeholder = itr.next();
			for(int i = 0; i < placeholder.getValue(); i++) {	nextWords.add(placeholder.getKey());	}
		}
		
		int returnLetter = rand.nextInt(nextWords.size());
		nextWord = nextWords.get(returnLetter);
		return nextWord;
	}
}
