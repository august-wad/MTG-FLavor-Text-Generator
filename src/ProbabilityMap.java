import java.util.*;

public class ProbabilityMap {
	Map<String, MarkovPhrase> probabilityMap;
	
	public ProbabilityMap() {
		probabilityMap = new LinkedHashMap<String, MarkovPhrase>();
	}
	
	public void addPair(String leadText, String followWord) {
		if(probabilityMap.containsKey(leadText)) {
			probabilityMap.get(leadText).increment(followWord);
		}
		else {
			probabilityMap.put(leadText, new MarkovPhrase(followWord));
		}
	}
	
	public String nextWord(String leadPhrase) {
		if(probabilityMap.get(leadPhrase) == null) {
			return null;
		}
		return probabilityMap.get(leadPhrase).nextWord();
	}
}
