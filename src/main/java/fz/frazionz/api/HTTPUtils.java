package fz.frazionz.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.src.Config;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.lwjgl.Sys;

import javax.imageio.ImageIO;

public class HTTPUtils {

	public static HTTPReply sendGet(String endpoint) {
		return sendGet(endpoint, null);
	}
	
	public static HTTPReply sendGet(String endpoint, List<NameValuePair> params)
	{
		try {
			if(params != null) {
				endpoint += "?" + URLEncodedUtils.format(params, "UTF-8");
			}
			HttpGet httpGet = new HttpGet(endpoint);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse httpResponse = httpClient.execute(httpGet);
			return new HTTPReply(httpResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return new HTTPReply(null);
		}
	}
	
	public static HTTPReply sendPost(String endpoint)
	{
		return sendPost(endpoint, null);
	}
	
	public static HTTPReply sendPost(String endpoint, List<NameValuePair> params)
	{
		try {
			
			HttpPost httpPost = new HttpPost(endpoint);
			
			if(params != null)
			{
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			}
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			return new HTTPReply(httpResponse);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new HTTPReply(null);
		}
	}
	
	
	public static void sendPostAsync(String endpoint, List<NameValuePair> params)
	{
		new Thread() {
			@Override
			public void run()
			{
				sendPost(endpoint, params);
			}
		}.start();
	}
	
	public static boolean downloadImage(String endpoint, File path, String name, String sha1) {
		try {
			File img = new File(path, name);
			if(img.exists()) {
				String imgSha1 = SHA1Utils.calcSHA1(img);
				if(imgSha1.equalsIgnoreCase(sha1)) {
					return true;
				}
			}
			HttpURLConnection httpurlconnection = null;
			try
			{
				httpurlconnection = (HttpURLConnection)(new URL(endpoint)).openConnection(Minecraft.getMinecraft().getProxy());
				httpurlconnection.setDoInput(true);
				httpurlconnection.setDoOutput(false);
				httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
				httpurlconnection.connect();

				if (httpurlconnection.getResponseCode() / 100 != 2)
				{
					if (httpurlconnection.getErrorStream() != null)
					{
						Config.readAll(httpurlconnection.getErrorStream());
					}

					return false;
				}

				BufferedImage bufferedimage;

				if (path != null)
				{
					FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), img);
					bufferedimage = ImageIO.read(img);
				}
				else
				{
					bufferedimage = TextureUtil.readBufferedImage(httpurlconnection.getInputStream());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
			finally
			{
				if (httpurlconnection != null)
				{
					httpurlconnection.disconnect();
				}
			}
			return true;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
