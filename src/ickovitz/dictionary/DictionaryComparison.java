package ickovitz.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class DictionaryComparison {

	private ArrayList<String> words = new ArrayList<String>();
	private String[] wordArray = new String[113809];

	public DictionaryComparison() {
		this.fillWordArrays("word_list_moby_crossword.flat.txt");

	}

	public DictionaryComparison(String fileName) {
		this.fillWordArrays(fileName);
	}

	public void fillWordArrays(String fileName) {

		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			words = IOUtils.readLines(reader);
			
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		int index = 0;
		for (String s : words) {
			
			wordArray[index] = s;
			index++;
		}

	}

	public boolean containsSequentialSearch(String word) {
		for (String s : words) {
			if (word.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsBinarySearch(String word) {
		int result = Arrays.binarySearch(wordArray, word);

		if (result != -1)
			return true;
		else
			return false;

	}
}
