package ickovitz.dictionary;

import java.util.Random;

public class SequentialSearchThread extends Thread{

	DictionaryComparison dictionary = new DictionaryComparison();
	String[] randomWords = new String[100000];
	String[] randomNonsense = new String[1000000];
	
	
	public void run(){
		
		fillRandomWordArray();
		
		int sequentialSearchStartTime = (int) System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			dictionary.containsSequentialSearch(randomWords[i]);
		}
		int sequentialSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Sequential Search 10,000 Random Words: "
				+ (sequentialSearchEndTime - sequentialSearchStartTime) + " millis.");
		
	}
	
	public void fillRandomWordArray() {
		Random random = new Random();
		String[] words = dictionary.getWordArray();
		int index = 0;
		while (index < 100000) {
			int number = random.nextInt(113808);

			randomWords[index] = words[number];
			index++;
		}
	}
	
}
