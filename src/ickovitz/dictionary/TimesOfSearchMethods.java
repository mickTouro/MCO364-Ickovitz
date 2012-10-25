package ickovitz.dictionary;

import java.util.Random;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.lang3.RandomStringUtils;

import sun.util.calendar.BaseCalendar.Date;

public class TimesOfSearchMethods {

	private DictionaryComparison dictionary = new DictionaryComparison();
	private String[] randomWords = new String[100000];
	private String[] randomNonsense = new String[1000000];

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
		for (String s : randomNonsense) {
			dictionary.containsHashMapSearch(s);
		}
		int hashMapSearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Hash Map Search 1,000,000 Random Nonsense: "
				+ (hashMapSearchEndTime - hashMapSearchStartTime));

	}

	private void hashMapSearch1000000RandomWords() {
		int index = 0;
		int hashMapSearchStartTime = (int) System.currentTimeMillis();
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

		for (String s : randomNonsense) {
			dictionary.containsBinarySearch(s);
		}

		int binarySearchEndTime = (int) System.currentTimeMillis();
		System.out.println("Binary Search 1,000,000 Random Nonesense: "
				+ (binarySearchEndTime - binarySearchStartTime));
	}

	private void binarySearch1000000RandomWords() {
		int index = 0;
		int binarySearchStartTime = (int) System.currentTimeMillis();
	
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

		RandomStringUtils randomStringGenerator = new RandomStringUtils();

		for (int index = 0; index < 1000000; index++) {
			randomNonsense[index] = RandomStringUtils
					.randomAlphabetic(random.nextInt(4) + 4);
		}
	}

	public static void main(String[] args) {
		new TimesOfSearchMethods();
	}

}
