package ickovitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.commons.io.IOUtils;


public class GoogleDownload {
	
	public static void main(String args[]) throws UnknownHostException, IOException{
		
/*		final String requestString = "GET /index.html\n\n";
		
		Socket s = new Socket("www.google.com", 80);
		OutputStream out = s.getOutputStream();
		out.write(requestString.getBytes());
		out.flush();
		
		InputStream in = s.getInputStream();
		String webpage = IOUtils.toString(in);
		
		s.close();
		
		System.out.println(webpage);*/
		
		URL url = new URL("http://www.google.com");
		System.out.println(url.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		InputStream in = connection.getInputStream();
		String webpage = IOUtils.toString(in);
		in.close();
		
		System.out.println(webpage);
	}
}
