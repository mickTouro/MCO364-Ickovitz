package ickovitz.net;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.junit.Test;

import codeobsessed.MD5.DemoMD5;

public class RepositoryTest {

	private Repository repo;
	private Webpage page;

	@Test
	public void testSave() throws IOException, NoSuchAlgorithmException {

		givenRepository();

		whenSave();

		thenFileExists();

	}
	
	@Test
	public void testDelete() throws IOException, NoSuchAlgorithmException {

		givenRepository();

		whenSave();
		whenDeleteRepository();

		thenRepositoryIsEmpty();

	}
	
	@Test
	public void testIsCached() throws IOException, NoSuchAlgorithmException {

		givenRepository();

		whenSave();

		thenIsCached();

	}
	
	@Test
	public void testEmptyRepoIsNotCached() throws IOException, NoSuchAlgorithmException {

		givenRepository();

		whenDeleteRepository();

		thenIsNotCached();

	}
	
	@Test 
	public void testNonEmptyRepoIsNotCached() throws IOException, NoSuchAlgorithmException{
		
		givenRepository();
		
		whenDeleteRepository();
		whenSave();
		
		thenIsNotCached();
	}

	private void thenFileExists() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		File file = new File("C:\\downloadedSites\\"
				+ DemoMD5.MD5("http://www.touro.edu") + ".txt");

		Assert.assertTrue(file.exists());
	}

	private void whenSave() throws NoSuchAlgorithmException, IOException {
		page = new Webpage("http://www.touro.edu");
		page.setHtml("Here is some sample html code.");
		repo.save(page);

	}

	public void givenRepository() throws IOException {

		repo = new Repository("C:\\downloadedSites\\");

	}

	private void thenIsCached() throws NoSuchAlgorithmException,
			MalformedURLException, IOException {
		Assert.assertTrue(repo.isCached(new URL("http://www.touro.edu")));
	}

	private void thenIsNotCached() throws NoSuchAlgorithmException,
			MalformedURLException, IOException {
		Assert.assertFalse(repo.isCached(new URL("http://www.touro1.edu")));

	}

	private void thenRepositoryIsEmpty() {
		Assert.assertEquals(0, repo.getDirectory().list().length);

	}

	private void whenDeleteRepository() throws IOException {
		repo.deleteCache();

	}

}
