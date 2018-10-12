package com.grofers.helper;

import java.net.HttpURLConnection;
import java.net.URL;

public class ValidateBrokenLinkHelper {

	public static String isLinkBroken(String strUrl) {
		try
		{
			URL url = new URL(strUrl);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			//connection.setConnectTimeout(5000);
			connection.connect();

			String response = connection.getResponseMessage();

			connection.disconnect();

			return response;
		} catch (Exception exp)
		{
			return exp.getMessage();
		}
	}

}
