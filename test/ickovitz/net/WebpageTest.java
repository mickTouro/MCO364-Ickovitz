package ickovitz.net;

import org.junit.Test;

public class WebpageTest {

	@Test
	public void testExtractLinks(){
		String html = "<a href=\"http://www.touro.edu\"";
		Webpage page = new Webpage("http://www.touro.edu");
		page.setHtml(html);
		
		System.out.println("Link 1: " + page.getLinks().toString());
		
		html = "<a class=\"title\" href=\"http://www.touro.edu/link2\">";
		page.setHtml(html);

		System.out.println("Link 2: " + page.getLinks().toString());
		
		html = "<A CLASS=\"MAIN\" HREF=\"http://www.touro.edu/link3/f.pdf\">";
		page.setHtml(html);
		
		System.out.println("Link 3: " + page.getLinks().toString());
		
	}
}
