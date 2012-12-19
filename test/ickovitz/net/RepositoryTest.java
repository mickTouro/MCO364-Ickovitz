package ickovitz.net;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import codeobsessed.MD5.DemoMD5;

public class RepositoryTest {

	@Test
	public void testSave() {
		try {
			Repository repo = new Repository("C:\\downloadedSites\\");
			Webpage page = new Webpage("http://www.touro.edu");
			page.setHtml("Here is some sample html code.");
			repo.save(page);
			File file = new File("C:\\downloadedSites\\"
					+ DemoMD5.MD5("http://www.touro.edu") + ".txt");

			Assert.assertTrue(file.exists());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testIsCachedTRUE() {
		try {
			Repository repo = new Repository("C:\\downloadedSites/");
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"C:\\downloadedSites\\"
							+ DemoMD5.MD5("http://www.hello.edu") + ".txt"));
			out.write("Hello! here is some sample text");
			out.close();

			Assert.assertTrue(repo.isCached(new URL("http://www.hello.edu")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testIsCachedFALSE() {
		try {
			Repository repo = new Repository("C:\\downloadedSites/");
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"C:\\downloadedSites\\"
							+ DemoMD5.MD5("http://wwww.hello.edu") + ".txt"));
			out.write("Hello! here is some sample text");
			out.close();

			Assert.assertFalse(repo.isCached(new URL("http://www.Hello.edu")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDelete() {
		try {
			Repository repo = new Repository("C:\\downloadedSites/");

			BufferedWriter out = new BufferedWriter(new FileWriter(
					"C:\\downloadedSites\\"
							+ DemoMD5.MD5("http://wwww.hello.edu") + ".txt"));
			out.write("Hello! here is some sample text");
			out.close();
			repo.deleteCache();

			Assert.assertEquals(0, repo.getDirectory().list().length);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
