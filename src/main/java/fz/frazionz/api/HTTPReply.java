package fz.frazionz.api;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class HTTPReply {

	private String body;
	private int statusCode;
	
	public HTTPReply(HttpResponse resp) {
		if(resp == null) {
			body = "";
			statusCode = -1;
		}
		else {
			statusCode = resp.getStatusLine().getStatusCode();
			try {
				body = EntityUtils.toString(resp.getEntity());
			}
			catch (Exception e) {
				body = "Error";
			}
		}
	}
	
	public String getBody() {
		return body;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
}
