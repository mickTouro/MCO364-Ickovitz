package ickovitz.dictionary;

import java.util.Random;

public class HashMapSearchThread extends Thread{

	DictionaryComparison dictionary = new DictionaryComparison();
	String[] randomWords = new String[100000];
	String[] randomNonsense = new String[1000000];
	
	
	public void run(){
		
		fillRandomWordArray();
		
		int index = 0;
		int hashMapSearchStartTime = (int) System.currentTimeMillis();
		while (index < 40) {
			for (String s : randomWords) {
				dictionary.containsHashMapSearch(s);
			}
			index++;
		}
		int hashMapSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Hash Map Search 4,000,000 Random Words: "
				+ (hashMapSearchEndTime - hashMapSearchStartTime) + " millis.");
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
