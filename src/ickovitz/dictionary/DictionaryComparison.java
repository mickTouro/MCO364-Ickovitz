package ickovitz.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DictionaryComparison {

	private ArrayList<String> words = new ArrayList<String>();

	public DictionaryComparison() {
		this.fillWordArray("G:\\workspace1\\MCO364-Ickovitz\\word_list_moby_crossword.flat.txt");

	}
	
	public DictionaryComparison(String fileName){
		this.fillWordArray(fileName);
	}

	public void fillWordArray(String fileName) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			String word = reader.readLine();
			while (word != null) {
				words.add(word);
				word = reader.readLine();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public boolean isWord(String word) {
		boolean bool = false;
		for (int index = 0; index < words.size(); index++) {
			if (words.get(index).equals(word)) {
				bool = true;
				break;
			}
		}
		return bool;
	}
}
