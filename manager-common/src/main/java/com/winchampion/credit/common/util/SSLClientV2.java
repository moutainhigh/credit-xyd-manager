package com.winchampion.credit.common.util;

/**
 * Created by jiyi on 14/7/8.
 */

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

//用于进行Https请求的HttpClient
public class SSLClientV2 {

	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static PoolingHttpClientConnectionManager cm = null;
	private static SSLConnectionSocketFactory sslsf = null;
	private static SSLContextBuilder builder = null;

	static {
		try {
			builder = new SSLContextBuilder();
			// 全部信任 不做身份鉴定
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(),	new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2", "TLSv1.1"},
					null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register(HTTP, new PlainConnectionSocketFactory())
					.register(HTTPS, sslsf).build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(200);// max connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CloseableHttpClient getHttpClient() throws Exception {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).setConnectionManager(cm)
				.setConnectionManagerShared(true).build();
		return httpClient;
	}
	
     //绕过证书
	public static CloseableHttpClient wrapClient() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					
				}
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(
					ctx, NoopHostnameVerifier.INSTANCE);
			CloseableHttpClient httpclient = HttpClients.custom()
					.setSSLSocketFactory(ssf).build();
			return httpclient;
			} catch (Exception e) {
				return HttpClients.createDefault();
			}

	}
}
