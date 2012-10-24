package ickovitz.dictionary;

import java.util.Random;

import javax.print.attribute.standard.DateTimeAtCompleted;

import sun.util.calendar.BaseCalendar.Date;

public class TimesOfSearchMethods {

	private DictionaryComparison dictionary = new DictionaryComparison();
	private String[] randomWords = new String[100000];
	private String[] randomNonsense = new String[100000];

	public TimesOfSearchMethods() {
		fillRandomWordArray();
		fillRandomNonsenseArray();

		sequentailSearch1000RandomWords();
		sequentialSearch1000RandomNonsense();
		binarySearch1000000RandomWords();
		binarySearch1000000RandomNonsense();
		hashMapSearch1000000RandomWords();
		hashMapSearch1000000RandomNonsense();

	}

	private void hashMapSearch1000000RandomNonsense() {
		int hashMapSearchStartTime = (int) System.currentTimeMillis();
		int index = 0;
		while (index < 10) {
			for (String s : randomNonsense) {
				dictionary.containsHashMapSearch(s);
			}
			index++;
		}
		int hashMapSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Hash Map Search 1,000,000 Random Nonsense: "
				+ (hashMapSearchEndTime - hashMapSearchStartTime));

	}

	private void hashMapSearch1000000RandomWords() {
		int hashMapSearchStartTime = (int) System.currentTimeMillis();
		int index = 0;
		while (index < 10) {
			for (String s : randomWords) {
				dictionary.containsHashMapSearch(s);
			}
			index++;
		}
		int hashMapSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Hash Map Search 1,000,000 Random Words: "
				+ (hashMapSearchEndTime - hashMapSearchStartTime));
	}

	private void binarySearch1000000RandomNonsense() {
		int binarySearchStartTime = (int) System.currentTimeMillis();
		int index = 0;
		while (index < 10) {
			for (String s : randomNonsense) {
				dictionary.containsBinarySearch(s);
			}
			index++;
		}
		int binarySearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Binary Search 1,000,000 Random Nonesense: "
				+ (binarySearchEndTime - binarySearchStartTime));
	}

	private void binarySearch1000000RandomWords() {
		int binarySearchStartTime = (int) System.currentTimeMillis();
		int index = 0;
		while (index < 10) {
			for (String s : randomWords) {
				dictionary.containsBinarySearch(s);
			}
			index++;
		}
		int binarySearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Binary Search 1,000,000 Random Words: "
				+ (binarySearchEndTime - binarySearchStartTime));
	}

	public void sequentialSearch1000RandomNonsense() {
		int sequentialSearchStartTime = (int) System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			dictionary.containsSequentialSearch(randomNonsense[i]);
		}
		int sequentialSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Sequentail Search 1,000 Random Nonesense: "
				+ (sequentialSearchEndTime - sequentialSearchStartTime));

	}

	public void sequentailSearch1000RandomWords() {
		int sequentialSearchStartTime = (int) System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			dictionary.containsSequentialSearch(randomWords[i]);
		}
		int sequentialSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Sequential Search 1,000 Random Words: "
				+ (sequentialSearchEndTime - sequentialSearchStartTime));
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

	public void fillRandomNonsenseArray() {
		Random random = new Random();
		String[] words = dictionary.getWordArray();
		String temporaryWord;
		String reverse;
		int number;
		int index = 0;
		while (index < 100000) {
			number = random.nextInt(113808);

			temporaryWord = words[number];

			reverse = "";
			for (int i = temporaryWord.length() - 1; i >= 0; i--) {
				reverse += temporaryWord.charAt(i);
			}

			while (dictionary.containsHashMapSearch(reverse)) {
				reverse += temporaryWord.charAt(0);

			}
			randomNonsense[index] = reverse;
			index++;
		}
	}

	public static void main(String[] args) {
	 new TimesOfSearchMethods();
	}

}
