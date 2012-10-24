package ickovitz.dictionary;

import junit.framework.Assert;

import org.junit.Test;

public class DictionaryComparisonTest {

	@Test
	public void TestDictionaryComparisonSequentialSearchTrue() {
		DictionaryComparison dc = new DictionaryComparison();

		Assert.assertTrue(dc.containsSequentialSearch("word"));

	}

	@Test
	public void TestDictionaryComparisonSequentialSearchFalse() {
		DictionaryComparison dc = new DictionaryComparison();

		Assert.assertFalse(dc.containsSequentialSearch("abcd"));

	}

	@Test
	public void TestDictionaryComparisonBinarySearchTrue() {
		DictionaryComparison dc = new DictionaryComparison();

		Assert.assertTrue(dc.containsBinarySearch("words"));

	}

	@Test
	public void TestDictionaryComparisonBinarySearchFalse() {
		DictionaryComparison dc = new DictionaryComparison();

		Assert.assertFalse(dc.containsBinarySearch("abcde"));

	}

	@Test
	public void TestDictionaryComparisonHashMapSearchTrue() {
		DictionaryComparison dc = new DictionaryComparison();

		Assert.assertTrue(dc.containsHashMapSearch("wording"));

	}

	@Test
	public void TestDictionaryComparisonHashMapSearchFalse() {
		DictionaryComparison dc = new DictionaryComparison();

		Assert.assertFalse(dc.containsHashMapSearch("abcdef"));

	}
}
