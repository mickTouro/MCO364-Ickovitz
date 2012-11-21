package ickovitz.dictionary;

import java.util.ArrayList;
import java.util.Arrays;

public class AnagramFinder {
	
	DictionaryComparison dictionary;
	Anagram anagram;
	ArrayList<String> anagramList = new ArrayList<String>();
	
	public AnagramFinder(){
		dictionary = new DictionaryComparison();
		anagram = new Anagram();
	}
	
	public void findAnagrams(String word){
		String[] words = dictionary.getWordArray();
		int i = 0;
		for (String s: words){
			if (anagram.anagramComparison(word, s)){
				anagramList.add(s);
				i++;
			}
		}
	}
	
	public boolean hasAnagram(String word){
		String[] words = dictionary.getWordArray();
		for (String s: words){
			if (anagram.anagramComparison(word, s) && s.compareTo(word) != 0){
				return true;
			}
		}
		return false;
	}

	public void printAnagrams(){
		System.out.println(anagramList.toString());
	}
}
