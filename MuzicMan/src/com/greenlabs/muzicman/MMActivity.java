package com.greenlabs.muzicman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MMActivity extends Activity implements OnItemClickListener{

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mm, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		//case
		//case
		case R.id.refresh_list:
			MyAsyncTask mat = new MyAsyncTask();
	    	mat.execute(new String[] { "http://bugthemusiceater.blogspot.in/" });
		
	    	return true;
		case R.id.load_images:
          ImageLoder il = new ImageLoder();
          il.execute();
			return true;
			default:
				return super.onMenuItemSelected(featureId, item);
		}
		
	}

	//-----------------------
	private MySimpleAdapter adapter;
   private ArrayList<Album> album = new ArrayList<Album>();
  
  
   
   //-----------------------------------------
  JSONParser parser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_mm);
        
        ListView lv=(ListView)findViewById(R.id.list);
        
        
        InitialTask mt2 = new InitialTask();
    	mt2.execute();
    	adapter = new MySimpleAdapter(album, MMActivity.this);
    	lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
      
       
    }
    //..........button functions..................
    public void getData(View view) {
//    	ImageLoder iloader = new ImageLoder();
//		iloader.execute();
    	
    	
    }
    
    public void getData2(View view) {
    //............
    	
    }
    //--------------------------------------------------
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		

		Album al_new = new Album();
		al_new=album.get(position);
		Intent i = new Intent(MMActivity.this, SongActivity.class);
		i.putExtra("albumf",al_new );
		startActivity(i);
		
	}
	//..............................Image loading ................//
	private class ImageLoder extends AsyncTask<Void , Void, Void>{

		@Override
		protected void onPostExecute(Void result) {
			Context context = getApplicationContext();
			CharSequence text = "download complete";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show(); 
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... params) {
			
				for(int i=1;i<=album.size();i++){
					Album al2=new Album();
					al2= album.get(i-1);
					 try {
						    HttpURLConnection connection = (HttpURLConnection) new URL(al2.getAlbumArt()).openConnection();
						    connection.setDoInput(true);
						    connection.connect();
						    InputStream is= connection.getInputStream();
						    Bitmap bitmap= BitmapFactory.decodeStream(is);
						      
//						    ContextWrapper cw = new ContextWrapper(getApplicationContext());
					         // path to /data/data/yourapp/app_data/imageDir
					        //File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
					        // Create imageDir
					        

					        FileOutputStream outputStream = null;
					        outputStream = openFileOutput(i+".png", Context.MODE_PRIVATE);		

					           

					       // Use the compress method on the BitMap object to write image to the OutputStream
					            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
					            outputStream.close();
					    	
						        is.close();
						    
						   } catch (MalformedURLException e) {
						    // TODO Auto-generated catch block
						    e.printStackTrace();
						   } catch (IOException e) {
						    // TODO Auto-generated catch block
						    e.printStackTrace();
						   }
					
				}
				
			 
			 return null;
		}
		
	}
	//--------------------------Reserved...-
	private class TaskLauncher extends AsyncTask<Integer, Void, Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			
			
			
			return null;
		}
		
	}
	//................................getting data from ----bug..feed.............
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
		      
		      //---------------------------------my logic to extract the data---------
		      String[] data2=data1.split("<figure>");
		      data1=data2[1];
		      String[] data3=data1.split("</figure>");
		      data1=data3[0];
		      //...................................................
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
			Intent i = new Intent(MMActivity.this,MMActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); 
			startActivity(i);
			super.onPostExecute(result);
		}

			
		}
    //....................creating the data objects.............................
    
    // Reading from the internal file ...........................................
    private class InitialTask extends AsyncTask<Void, Void, Void>{
    	ProgressDialog progDailog = new ProgressDialog(MMActivity.this);
		@Override
		protected void onPreExecute() {
			
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			
			adapter.notifyDataSetChanged();
			progDailog.dismiss();
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
				@SuppressWarnings("unchecked")
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



