package com.greenlabs.muzicman.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Connetion {
	public String xml;
	
public Connetion() {
	// TODO Auto-generated constructor stub
}
public String connect(){
	
    try {
        URL url = new URL("http://bugthemusiceater.blogspot.in/");

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        //BufferedWriter writer = new BufferedWriter(new FileWriter("d:/data.html"));

        String line;
        String data1=null;
        while ((line = reader.readLine()) != null) {
            
            data1=data1+line;
//            writer.write(line);
//            writer.newLine();
        }
String[] data2=data1.split("<figure>");
data1=data2[1];
String[] data3=data1.split("</figure>");
data1=data3[0];
xml=data1;
        reader.close();
       // writer.close();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }  catch (IOException e) {
        e.printStackTrace();
    }
	return xml;
	
	
}
}
