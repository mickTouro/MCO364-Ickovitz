package ickovitz.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class DictionaryComparison {

	private java.util.List<String> words;
	private String[] wordArray = new String[113809];
	private HashMap<String, String> map = new HashMap<String, String>();

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

			map.put(s, s);

			index++;
		}

	}

	public boolean containsSequentialSearch(String word) {
		for (String s : words) {
			if (word.equals(s.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean containsBinarySearch(String word) {
		int result = Arrays.binarySearch(wordArray, word.toLowerCase());

		if (result > 0)
			return true;
		else
			return false;

	}
	
	public boolean containsHashMapSearch(String word){
		return map.containsKey(word.toLowerCase());
	}
	
	public HashMap getMap(){
		return map;
	}
	
	public String[] getWordArray(){
		return wordArray;
	}
}
