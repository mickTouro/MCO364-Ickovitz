package ickovitz.net;


import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class PageDownloaderThreadTest {
	
	@Test
	public void testFindLinks1() throws IOException, NoSuchAlgorithmException{
		FileUtils.cleanDirectory(new File("C:\\downloadedSites"));
		PageDownloaderThread touroDownloader1 = new PageDownloaderThread("http://touro.edu");
		PageDownloaderThread touroDownloader2 = new PageDownloaderThread("http://touro.edu");
		touroDownloader1.downloadSiteText();
		touroDownloader1.findLinks();		
		touroDownloader2.downloadSiteText();
		touroDownloader2.findLinks();
		Assert.assertNotSame(touroDownloader1.getName(), touroDownloader2.getLinksList());
	}
	

	Pattern pattern = Pattern.compile("<a.*?href=\"(.+?)\"",
			Pattern.CASE_INSENSITIVE);
	
	@Test
	public void testAnchorPattern1() {
				
		String text = "Here is some text without an a href";
		Matcher matcher = pattern.matcher(text);
		Assert.assertFalse(matcher.find());
		
	}
	
	@Test
	public void testAnchorPattern2() {
				
		String text = "<h1 />";
		Matcher matcher = pattern.matcher(text);
		Assert.assertFalse(matcher.find());
		
	}
	
	@Test
	public void testAnchorPattern3() {
		
		String text = "<a href>";
		Matcher matcher = pattern.matcher(text);
		Assert.assertFalse(matcher.find());
		
	}
	
	@Test
	public void testAnchorPattern4() {
		
		String text = "<a href=\"\">";
		Matcher matcher = pattern.matcher(text);
		Assert.assertFalse(matcher.find());
		
	}
	
	@Test
	public void testAnchorPattern5() {
		
		String text = "<a href=\"mySite\">";
		Matcher matcher = pattern.matcher(text);
		Assert.assertTrue(matcher.find());
		
	}
	
	@Test
	public void testAnchorPattern6() {
		
		String text = "<a href=\"http:\\\\www.touro.edu\">";
		Matcher matcher = pattern.matcher(text);
		Assert.assertTrue(matcher.find());
		
	}
	
	@Test
	public void testAnchorPattern7() {
		
		String text = "<A class=\"MenuLink\" href=\"http:\\\\www.touro.edu\">";
		Matcher matcher = pattern.matcher(text);
		Assert.assertTrue(matcher.find());
		
	}
}
