package yc.code.dict.wechat.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * HttpMethod
 *
 * <p>没有使用Httplient或者Resttemplate，
 * <p>直接使用Java原生代码开发的，好处就是能减少依赖，坏处就是原生的可能不如框架来的舒服？
 * <p>并且没有使用util包，为了防止和源项目撞车
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class HttpMethod {

	/**
	 * Set RequestPropert
	 */
	private static URLConnection getUrlConnection(String url) throws IOException {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		return conn;
	}

	/**
	 * Get
	 */
	public static String doGet(String url, HashMap<String, String> params) {
		StringBuffer param = new StringBuffer();
		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
		}

		StringBuilder result = new StringBuilder();
		BufferedReader in = null;
		try {
			url += param;
			URLConnection connection = getUrlConnection(url);
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * Post
	 */
	public static String doPost(String url, HashMap<String, String> params) {
		StringBuffer param = new StringBuffer();
		if (params != null) {
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
		}

		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			url += param;
			URLConnection conn = getUrlConnection(url);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * Post(Json)
	 */
	public static String doPost(String urlStr, String data) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Accept", "application/json");
		httpURLConnection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
		InputStream inputStream = httpURLConnection.getInputStream();
		return toString(inputStream);
	}

	/**
	 * Charset Format
	 */
	private static String toString(InputStream is) {
		try {
			ByteArrayOutputStream boa = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[1024];

			while ((len = is.read(buffer)) != -1) {
				boa.write(buffer, 0, len);
			}
			is.close();
			boa.close();
			byte[] result = boa.toByteArray();
			String temp = new String(result);
			if (temp.contains("utf-8")) {
				return new String(result, StandardCharsets.UTF_8);
			} else if (temp.contains("gb2312")) {
				return new String(result, "gb2312");
			} else {
				return new String(result, StandardCharsets.UTF_8);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
