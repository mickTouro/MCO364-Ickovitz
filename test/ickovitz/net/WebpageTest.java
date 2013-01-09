package ickovitz.net;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class WebpageTest {

	private Webpage page;

	@Before
	public void preTest() {
		page = null;
	}

	@Test
	public void testExtractLinks() throws MalformedURLException {

		givenWebpage();

		whenExtractLinks();

		thenContainsLinks();

	}

	@Test
	public void testRemoveTags() throws MalformedURLException {

		givenWebpage();

		whenRemoveTags();

		thenTagsRemoved();
	}

	@Test
	public void testFindHomePage() throws MalformedURLException {

		givenWebpage();

		whenFindHomePage();

		thenHomePageCorrect();
	}

	private void thenHomePageCorrect() {
		Assert.assertEquals("http://www.touro.edu", page.getHomePage()
				.toString());

	}

	private void whenFindHomePage() {
		page.findHomePage();

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

	private void givenWebpage() throws MalformedURLException {
		String html = "<a href=\"http://www.touro.edu\">Click here for touro.</a>";
		html += "<a class=\"title\" href=\"http://www.touro.edu/link2\">";
		html += "<A CLASS=\"MAIN\" HREF=\"http://www.touro.edu/link3/f.pdf\">Here's another link.</A>";

		page = new Webpage(new URL("http://www.touro.edu/onlineCourses"));

		page.setHtml(html);

	}
}
