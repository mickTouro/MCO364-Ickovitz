package ickovitz.net;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codeobsessed.MD5.DemoMD5;

public class RepositoryTest {

	private Repository repo;
	private Webpage page;

	@Before
	@After
	public void preAndPostTest() {
		repo = null;
		try {
			FileUtils.cleanDirectory(new File("tmp/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchFilesArray() throws IOException, NoSuchAlgorithmException{
		givenRepository();
		
		whenSave();
		whenSearchFilesUsingArray();
		
		thenFilesFound();
	}
	

	@Test
	public void testSearchFiles() throws IOException, NoSuchAlgorithmException {
		givenRepository();

		whenSave();
		whenSearchFiles();

		thenFilesFound();
	}
	

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
	public void testEmptyRepoIsNotCached() throws IOException,
			NoSuchAlgorithmException {

		givenRepository();

		whenDeleteRepository();

		thenIsNotCached();

	}

	@Test
	public void testNonEmptyRepoIsNotCached() throws IOException,
			NoSuchAlgorithmException {

		givenRepository();

		whenDeleteRepository();
		whenSave();

		thenIsNotCached();
	}

	@Test
	public void testRetrieve() throws IOException, NoSuchAlgorithmException {

		givenRepository();

		whenSaveMyHtml();

		thenHtmlRetrieved();

	}

	private void whenSearchFilesUsingArray() throws IOException {
		repo.searchFiles("sample", "Here", "is");
		
	}

	private void thenFilesFound() throws IOException, NoSuchAlgorithmException {
		ArrayList<String> array = new ArrayList<String>();
		array.add(DemoMD5.MD5("http://www.touro.edu") + ".txt");
		Assert.assertEquals(array, repo.searchFiles("sample"));

	}

	private void whenSearchFiles() throws IOException {
		repo.searchFiles("sample");

	}

	private void thenHtmlRetrieved() throws NoSuchAlgorithmException,
			MalformedURLException, IOException {
		Assert.assertEquals("Here is some sample html code.",
				repo.retrieve(new URL("http://www.myPage.edu")));

	}

	private void whenSaveMyHtml() throws NoSuchAlgorithmException, IOException {
		page = new Webpage("http://www.myPage.edu");
		page.setHtml("Here is some sample html code.");
		repo.save(page);

	}

	private void thenFileExists() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		File file = new File("tmp/" + DemoMD5.MD5("http://www.touro.edu")
				+ ".txt");

		Assert.assertTrue(file.exists());
	}

	private void whenSave() throws NoSuchAlgorithmException, IOException {
		page = new Webpage("http://www.touro.edu");
		page.setHtml("Here is some sample html code.");
		repo.save(page);

	}

	public void givenRepository() throws IOException {

		repo = new Repository("tmp");

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
