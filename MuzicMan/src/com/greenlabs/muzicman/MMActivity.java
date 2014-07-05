package com.greenlabs.muzicman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.greenlab.muzicman.jsonreader.JsonReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MMActivity extends Activity implements OnItemClickListener{

	private MySimpleAdapter adapter;
   private JsonReader jreader;
//private MConnetion con;
//	private String str;
  
   private ArrayList<Album> album = new ArrayList<Album>();
  
  
  JSONParser parser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_mm);
        
        ListView lv=(ListView)findViewById(R.id.list);
        adapter = new MySimpleAdapter(album, MMActivity.this);
        lv.setAdapter(adapter);
        MyTask2 mt2 = new MyTask2();
    	mt2.execute();
        lv.setOnItemClickListener(this);
      
       
    }
    //..........button functions..................
    public void getData(View view) {
    	
    	MyAsyncTask mat = new MyAsyncTask();
    	mat.execute(new String[] { "http://bugthemusiceater.blogspot.in/" });
    }
    
    public void getData2(View view) {
    //	MyTask2 mt2 = new MyTask2();
    	//mt2.execute();
    	
    }
    //--------------------------------------------------
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		Context context = getApplicationContext();
//		
//		CharSequence text = "Hello toast!"+position;
//		int duration = Toast.LENGTH_LONG;
//
//		Toast toast = Toast.makeText(context, text, duration);
//		toast.show();
		Album al_new = new Album();
		al_new=album.get(position);
		Intent i = new Intent(MMActivity.this, MainActivity.class);
		i.putExtra("albumf",al_new );
		startActivity(i);
		
	}
	private class TaskLauncher extends AsyncTask<Integer, Void, Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			Album Lalbum= new Album();
			Lalbum=album.get(params[0]);
			
			
			return null;
		}
		
	}
    private class MyAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... urls) {
			String response = "";
		      for (String url : urls) {
		        DefaultHttpClient client = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet(url);
		        try {
		          HttpResponse execute = client.execute(httpGet);
		          InputStream content = execute.getEntity().getContent();

		          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
		          String s = "";
		          while ((s = buffer.readLine()) != null) {
		            response += s;
		          }

		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		      String data1= response;
		      
		      //---------------------------------my logic---------
		      String[] data2=data1.split("<figure>");
		      data1=data2[1];
		      String[] data3=data1.split("</figure>");
		      data1=data3[0];
	        String filename = "song.json";
	    	
	    	String string =data1;
	    	FileOutputStream outputStream;

	    	try {
	    	  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
	    	  outputStream.write(string.getBytes());
	    	  outputStream.close();
	    	} catch (Exception e) {
	    	  e.printStackTrace();
	    	}
		      //-------------------------------------------
		      return data1;
			
			//---------------------------------------
		
			
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

			
		}
    private class MyTask2 extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPostExecute(Void result) {
			adapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			//---------open the file................
			try {
				FileInputStream fin = openFileInput("song.json");
				
				int c;
				String temp="";
				while((c=fin.read())!=-1){
				temp=temp+Character.toString((char)c);
				}
				
				Object obj = parser.parse(temp);
				
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray msg = (JSONArray) jsonObject.get("albums");
				Iterator<JSONObject> iterator = msg.iterator();
				while (iterator.hasNext()) {
					    Album al=new Album();
					jsonObject=iterator.next();
					Long albumId=(Long)jsonObject.get("albumId");
					al.setId(albumId);
					String albumName= (String)jsonObject.get("albumName");
					al.setAlbumNname(albumName);
					String albumArt= (String) jsonObject.get("albumArt");
					al.setAlbumArt(albumArt);
					JSONArray sng = (JSONArray)jsonObject.get("songs");
					Iterator<JSONObject> it=sng.iterator();
					Song sg= null;
					ArrayList<Song> alsong= new ArrayList<Song>();
					while(it.hasNext()){
						sg=new Song();
						jsonObject=it.next();
						String songName=(String)jsonObject.get("songName");
						sg.setSongName(songName);
						String songLink = (String)jsonObject.get("songLink");
						sg.setSongLink(songLink);
						alsong.add(sg);
					}
					al.setSong(alsong);
					album.add(al);
					//System.out.println(al.getAlbumName());
				}
		 
		
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
			 
		}
    }
    
}



