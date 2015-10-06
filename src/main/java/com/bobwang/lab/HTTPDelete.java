package com.bobwang.lab;

import java.io.IOException;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
 
@NotThreadSafe
class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";
 
    public String getMethod() {
        return METHOD_NAME;
    }
 
    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }
 
    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }
 
    public HttpDeleteWithBody() {
        super();
    }
}
 
public class HTTPDelete {
    public static void main(String[] args) throws ClientProtocolException, IOException {
 
        CloseableHttpClient httpclient = HttpClients.createDefault();
 
        String url = "http://10.52.106.22:8080/api/v2/settings/gir-recording";
 
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
        StringEntity input = new StringEntity("John Dow", ContentType.APPLICATION_JSON);
         
        httpDelete.setEntity(input);  
 
        System.out.println("****REQUEST***************************************");
        System.out.println(url);
        Header requestHeaders[] = httpDelete.getAllHeaders();
        for (Header h : requestHeaders) {
            System.out.println(h.getName() + ": " + h.getValue());
        }
 
        CloseableHttpResponse response = httpclient.execute(httpDelete);
 
        System.out.println("****RESPONSE***************************************");
        System.out.println("----status:---------------------");
        System.out.println(response.getStatusLine());
        System.out.println("----header:---------------------");
        Header responseHeaders[] = response.getAllHeaders();
        for (Header h : responseHeaders) {
            System.out.println(h.getName() + ": " + h.getValue());
        }
        System.out.println("----content:---------------------");
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}