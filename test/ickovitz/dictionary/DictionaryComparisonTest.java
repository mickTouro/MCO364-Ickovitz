package ickovitz.dictionary;

import junit.framework.Assert;

import org.junit.Test;

public class DictionaryComparisonTest {

	@Test
	public void TestDictionaryComparison(){
		DictionaryComparison dc = new DictionaryComparison();
		
		Assert.assertTrue(dc.containsBinarySearch("word"));
		Assert.assertFalse(dc.containsBinarySearch("abcd"));
	
	}
}
