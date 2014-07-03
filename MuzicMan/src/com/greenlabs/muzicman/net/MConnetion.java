package com.greenlabs.muzicman.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MConnetion {
	
	
public MConnetion() {
	
}
public String connect(){
	  String data1 = "";
     String url="http://bugthemusiceater.blogspot.in/";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
          HttpResponse execute = client.execute(httpGet);
          InputStream content = execute.getEntity().getContent();

          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
          String s = "";
          while ((s = buffer.readLine()) != null) {
            data1 += s;
          }

        } catch (Exception e) {
          e.printStackTrace();
        }
      
        
        
        //.........logic to extracting the required file...........
String[] data2=data1.split("<figure>");
data1=data2[1];
String[] data3=data1.split("</figure>");
data1=data3[0];


	return data1;
	
	
}
}
