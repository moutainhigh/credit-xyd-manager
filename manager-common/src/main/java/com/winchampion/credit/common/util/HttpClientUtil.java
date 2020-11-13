package com.winchampion.credit.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

	private static Logger logger=LoggerFactory.getLogger(HttpClientUtil.class);
	private static int connTimeout = 120000; // 连接等待超时时间，毫秒
	private static int soTimeout = 120000; // 响应等待超时时间，毫秒

	public HttpClientUtil() {
	}

	 public static String post(String url, Map<String, String> map) {
			
			/*CloseableHttpClient httpClient = null;
			HttpPost httpPost = null;
			String result = null;
			try {
				if(url.startsWith("https")) {
					httpClient = SSLClientV2.wrapClient();
				}else {
					httpClient = SSLClientV2.getHttpClient();
				}
				httpPost = new HttpPost(url);
				
				System.out.println(map);
				// 设置参数
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for(String key : map.keySet()) {
					if(map.get(key) != null) {
						list.add(new BasicNameValuePair(key, map.get(key)));
					}
				
				}
				
				if (list.size() > 0) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
							"UTF-8");
					httpPost.setEntity(entity);
				}
				HttpResponse response = httpClient.execute(httpPost);			
				if (response != null) {
					HttpEntity resEntity = response.getEntity();
					if (resEntity != null) {
						result = EntityUtils.toString(resEntity, "UTF-8");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			*/
			return post(url, map, "UTF-8");
		}

	 public static String post(String url, Map<String, String> map, String charset) {
			
			CloseableHttpClient httpClient = null;
			HttpPost httpPost = null;
			String result = null;
			try {
//				httpClient = SSLClientV2.wrapClient();
				if(url.startsWith("https")) {
					httpClient = SSLClientV2.wrapClient();
				}else {
					httpClient = SSLClientV2.getHttpClient();
				}
				httpPost = new HttpPost(url);
				//设置超时时间
				RequestConfig requestConfig = RequestConfig.custom()  
//	                    .setConnectionRequestTimeout(connTimeout)   //从连接池中获取连接的超时时间  
                        //与服务器连接超时时间：httpclient会创建一个异步线程用以创建socket连接，此处设置该socket的连接超时时间  
	                    .setConnectTimeout(connTimeout)
	                  //socket读数据超时时间：从服务器获取响应数据的超时时间  
	                    .setSocketTimeout(soTimeout).build();
				httpPost.setConfig(requestConfig);
				
				System.out.println(map);
				// 设置参数
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for(String key : map.keySet()) {
					if(map.get(key) != null) {
						list.add(new BasicNameValuePair(key, map.get(key)));
					}
				}
				
				if (list.size() > 0) {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
							charset);
					httpPost.setEntity(entity);
				}
				HttpResponse response = httpClient.execute(httpPost);			
				if (response != null) {
					HttpEntity resEntity = response.getEntity();
					if (resEntity != null) {
						result = EntityUtils.toString(resEntity, charset);
					}
				}
			} catch (Exception ex) {
//				ex.printStackTrace();
				logger.error(ex.getMessage(), ex);
			}
			
			return result;
		}
	 
	 
	public String doPostSLLV2(String url,String json, String charset) {
			
  		CloseableHttpClient httpClient = null;
  		HttpPost httpPost = null;
  		String result = null;
  		try {
  			if(url.startsWith("https")) {
				httpClient = SSLClientV2.wrapClient();
			}else {
				httpClient = SSLClientV2.getHttpClient();
			}
  			httpPost = new HttpPost(url);
  			
  			StringEntity entity = new StringEntity(json,charset);
			entity.setContentType("application/json");
			entity.setContentEncoding(charset);
			httpPost.setEntity(entity);	
			HttpResponse response = httpClient.execute(httpPost);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
  		} catch (Exception ex) {
  			ex.printStackTrace();
  		}
  		
  		return result;
  	}

	
	public static String httpPost(String url, byte[] nbytes, String encoding, String contentType) throws Exception {
		URL u = null;
		HttpURLConnection con = null;
		if (encoding == null || encoding.trim().equals("")) {
			encoding = "GBK";
		}
		// 构建请求参数
		logger.debug("send_url:" + url);
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(connTimeout);
			con.setReadTimeout(soTimeout);
			if (contentType == null) {
				con.setRequestProperty("Content-Type",
						"application/json;charset=UTF-8");
			} else {
				con.setRequestProperty("Content-Type", contentType
						+ ";charset=UTF-8");
			}
//			con.setRequestProperty("Content-Type", "application/json; charset=" + encoding);
			OutputStream out = new DataOutputStream(con.getOutputStream());
			out.write(nbytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return buffer.toString();
	}
}
