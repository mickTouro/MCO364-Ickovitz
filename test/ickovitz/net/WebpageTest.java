package ickovitz.net;

import junit.framework.Assert;

import org.junit.Test;

public class WebpageTest {

	private Webpage page;

	@Test
	public void testExtractLinks() {

		givenWebpage();

		whenExtractLinks();

		thenContainsLinks();

	}

	private void thenContainsLinks() {
		Assert.assertEquals("http://www.touro.edu", page.getLinks().get(0)
				.toString());
		Assert.assertEquals("http://www.touro.edu/link2", page.getLinks()
				.get(1).toString());
		Assert.assertEquals("http://www.touro.edu/link3/f.pdf", page.getLinks()
				.get(2).toString());

	}

	private void whenExtractLinks() {
		page.extractLinks();

	}

	private void givenWebpage() {
		String html = "<a href=\"http://www.touro.edu\">";
		html += "<a class=\"title\" href=\"http://www.touro.edu/link2\">";
		html += "<A CLASS=\"MAIN\" HREF=\"http://www.touro.edu/link3/f.pdf\">";

		page = new Webpage("http://www.touro.edu");
		page.setHtml(html);

	}
}
