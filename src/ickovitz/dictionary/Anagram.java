package ickovitz.dictionary;

import java.util.Arrays;

public class Anagram {

	private char[] characters1;
	private char[] characters2;

	public boolean anagramComparison(String word1, String word2) {
		if (word1.length() != word2.length()) {
			return false;
		}
		characters1 = word1.toCharArray();
		characters2 = word2.toCharArray();

		Arrays.sort(characters1);
		Arrays.sort(characters2);

		return (Arrays.equals(characters1, characters2));
	}

}
