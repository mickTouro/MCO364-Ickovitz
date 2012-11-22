package ickovitz.dictionary;

import java.util.Random;

public class BinarySearchThread extends Thread{

	DictionaryComparison dictionary = new DictionaryComparison();
	String[] randomWords = new String[100000];
	String[] randomNonsense = new String[1000000];
	
	
	public void run(){
		
		fillRandomWordArray();
		
		int index = 0;
		int binarySearchStartTime = (int) System.currentTimeMillis();
	
		while (index < 20) {
			for (String s : randomWords) {
				dictionary.containsBinarySearch(s);
			}
			index++;
		}
		int binarySearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Binary Search 2,000,000 Random Words: "
				+ (binarySearchEndTime - binarySearchStartTime) + " millis.");
		
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
