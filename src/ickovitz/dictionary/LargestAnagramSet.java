package ickovitz.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.xml.internal.serializer.utils.AttList;

public class LargestAnagramSet {

	DictionaryComparison dictionary;
	HashMap<String, ArrayList<String>> hashTable;

	public LargestAnagramSet() {
		dictionary = new DictionaryComparison();
		hashTable = new HashMap<String, ArrayList<String>>();
	}

	public void generateKeysAndValues() {

		char[] charArray;
		String key;

		for (String s : dictionary.getWordArray()) {

			charArray = s.toCharArray();
			Arrays.sort(charArray);
			key = Arrays.toString(charArray);

			if (!hashTable.containsKey(key)) {
				ArrayList<String> value = new ArrayList<String>();
				value.add(s);
				hashTable.put(key, value);
			}
			else{
				hashTable.get(key).add(s);
			}
		}
	}
	
	public void findMostValuesPerKey(){
		Collection<ArrayList<String>> list = hashTable.values();
		int size;
		int largest = 0;
		
		for(ArrayList<String> s: list){
			size = s.size();
			if(size > largest){
				largest = size;
			}
		}
		System.out.println("The largest group is : " + largest);
	}
	
	public ArrayList<ArrayList<String>> getLargestGroup(){
		Collection<ArrayList<String>> list = hashTable.values();
		ArrayList<ArrayList<String>> listOfLists= new ArrayList<ArrayList<String>>();
		int size;
		int largest = 0;		
		
		for(ArrayList<String> s: list){
			size = s.size();
			if(size == largest){
				listOfLists.add(s);
			}
			if(size > largest){
				listOfLists.clear();
				largest = size;
				listOfLists.add(s);
			}
			
		}
		return listOfLists;
	}
	
	public static void main(String args[]){
		LargestAnagramSet las = new LargestAnagramSet();
		las.generateKeysAndValues();
		las.findMostValuesPerKey();
		System.out.println(las.getLargestGroup().toString());
	}

}

