package com.example.NewsApp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class PageContent {
	private static String readAll(Reader rd) throws IOException {
		var sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static void getContent() {

		try {

			var u = new URL("https://meduza.io/api/w5/screens/news");

			var conn = u.openConnection();

			var content = new GZIPInputStream(conn.getInputStream());

			var br = new BufferedReader(new InputStreamReader(content));

			var jsonText = readAll(br);
			
			System.out.println(jsonText);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
