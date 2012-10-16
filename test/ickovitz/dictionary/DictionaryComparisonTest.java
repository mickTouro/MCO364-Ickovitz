package ickovitz.dictionary;

import junit.framework.Assert;

import org.junit.Test;

public class DictionaryComparisonTest {

	@Test
	public void TestDictionaryComparison(){
		DictionaryComparison dc = new DictionaryComparison();
		
		Assert.assertTrue(dc.isWord("vodka"));
		Assert.assertFalse(dc.isWord("abcd"));
	
	}
}
