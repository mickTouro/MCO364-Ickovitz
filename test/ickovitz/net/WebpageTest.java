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

	@Test
	public void testRemoveTags() {

		givenWebpage();

		whenRemoveTags();

		thenTagsRemoved();
	}

	private void thenTagsRemoved() {
		Assert.assertEquals("Click here for touro.Here's another link.",
				page.getTaglessHtml());

	}

	private void whenRemoveTags() {
		page.removeTags();

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
		String html = "<a href=\"http://www.touro.edu\">Click here for touro.</a>";
		html += "<a class=\"title\" href=\"http://www.touro.edu/link2\">";
		html += "<A CLASS=\"MAIN\" HREF=\"http://www.touro.edu/link3/f.pdf\">Here's another link.</A>";

		page = new Webpage("http://www.touro.edu");
		page.setHtml(html);

	}
}
