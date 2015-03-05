package com.sto.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sto.utils.MD5Utils;

public class HttpUtils {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, Map<String, Object> param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			// url = url + param.get("method").toString();
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());

			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				out.print(entry.getKey() + "," + entry.getValue());
				sb.append("&").append(entry.getKey()).append("=")
						.append(entry.getValue());
			}
			out.print(sb.toString().substring(0));
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
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
		return result;
	}

	/* 测试 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = formart.format(new Date());
		// 定义一个map集合，将method和timestamp参数放进map中
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("method", "country");
		param.put("timestamp", timestamp);
		// param.put("lev", 1);

		// app_key:stoapp01
		 param.put("app_key", "stoapp01");

		// app_secret:21232f297a57a5a743894a0e4a801fc3
		// param.put("app_secret", "21232f297a57a5a743894a0e4a801fc3");

		// 定义一个map集合，将incTimestamp和lev(根据不同API可选参数)参数放进map中(是为了将这两个参数转换成json字符串)
		
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("incTimestamp", null);
		param1.put("lev", null);
		String data = new ObjectMapper().writeValueAsString(param1);

		// 将转好的json字符串放进param中
		param.put("data", data);

		String sign1 = "21232f297a57a5a743894a0e4a801fc3" + "app_key"
				+ param.get("app_key") + "data" + data + "method" + param.get("method")
				+ "timestamp" + param.get("timestamp")
				+ "21232f297a57a5a743894a0e4a801fc3";

		String sign = MD5Utils.md5(sign1).toUpperCase();

		param.put("sign", sign);

		// 发送 POST 请求 http://192.168.8.5:8085/bsc/get
		String sr = HttpUtils.sendPost(
				"http://localhost:8080/sto/get", param);

		readJson2Map(sr);

	}

	/**
	 * 解析服务端发来的json字符串
	 * 
	 * @param json
	 */
	@SuppressWarnings("unchecked")
	public static void readJson2Map(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			// 将json字符串转成map结合解析出来，并打印(这里以解析成map为例)
			Map<String, Map<String, Object>> maps = objectMapper.readValue(
					json, Map.class);
			System.out.println(maps.size());
			Set<String> key = maps.keySet();
			Iterator<String> iter = key.iterator();
			while (iter.hasNext()) {
				String field = iter.next();
				System.out.println(field + ":" + maps.get(field));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}