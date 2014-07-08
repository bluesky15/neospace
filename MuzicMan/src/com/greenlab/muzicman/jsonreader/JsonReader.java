package com.greenlab.muzicman.jsonreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.greenlabs.muzicman.*;


public class JsonReader {
	ArrayList<Album> albumNew = new ArrayList<Album>();
	public ArrayList<Album> jread(String str) throws FileNotFoundException, IOException{
		 Album al;
		
		 
		JSONParser parser = new JSONParser();
	 
		try {
	 
			Object obj = parser.parse(str);
	
			JSONObject jsonObject = (JSONObject) obj;
	 
			
			//Long albumNumber = (Long) jsonObject.get("albumNo");
			
	 
			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("albums");
			Iterator<JSONObject> iterator = msg.iterator();
			while (iterator.hasNext()) {
				jsonObject=iterator.next();
				al=new Album();
				Long albumId=(Long)jsonObject.get("albumId");
				al.setId(albumId);
				String albumName= (String)jsonObject.get("albumName");
				al.setAlbumNname(albumName);
				String albumArt= (String) jsonObject.get("albumArt");
				al.setAlbumArt(albumArt);
				JSONArray sng = (JSONArray)jsonObject.get("songs");
				Iterator<JSONObject> it=sng.iterator();
				Song sg=null;
				 ArrayList<Song> alsong= new ArrayList<Song>();
				while(it.hasNext()){
					jsonObject=it.next();
					String songName=(String)jsonObject.get("songName");
					sg.setSongName(songName);
					String songLink = (String)jsonObject.get("songLink");
					sg.setSongLink(songLink);
					alsong.add(sg);
				}
				al.setSong(alsong);
				albumNew.add(al);
				//System.out.println(al.getAlbumName());
			}
	 
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumNew;
	 
	     }
	}



