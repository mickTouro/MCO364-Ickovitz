package ickovitz.dictionary;

import junit.framework.Assert;

import org.junit.Test;

public class AnagramTest {

	@Test
	public void anagramTestWords_race_care(){
		Anagram anagram = new Anagram();
		
		Assert.assertTrue(anagram.anagramComparison("race", "care"));
	}
	
	@Test 
	public void anagramTestWords_car_race(){
		Anagram anagram = new Anagram();
		Assert.assertFalse(anagram.anagramComparison("car", "race"));
	}
	
	@Test 
	public void anagramTestWords_agree_eager(){
		Anagram anagram = new Anagram();
		Assert.assertTrue(anagram.anagramComparison("eager", "agree"));
	}
}
