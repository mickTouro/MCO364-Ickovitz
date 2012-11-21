package ickovitz.dictionary;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.Assert;

public class AnagramFinderTest {
	
	@Test
	public void testAnagramFinderHasAnagramMethod_True(){
		AnagramFinder anagramFinder = new AnagramFinder();
		Assert.assertTrue(anagramFinder.hasAnagram("act"));
	}

	@Test
	public void testAnagramFinderHasAnagramMethod_False(){
		AnagramFinder anagramFinder = new AnagramFinder();
		Assert.assertFalse(anagramFinder.hasAnagram("hack"));
	}
	
	@Test
	public void testAnagramFinderFindAnagramMethods(){
		AnagramFinder anagramFinder = new AnagramFinder();
		anagramFinder.findAnagrams("agree");
		anagramFinder.printAnagrams();
	}
}
